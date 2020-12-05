package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.controller.utils.UserDetermination;
import project.entity.TimetableElement;
import project.entity.User;
import project.exception.NotAddedToDatabase;
import project.security.jwt.JwtTokenProvider;
import project.service.CustomUserDetailsService;
import project.service.TimetableElementService;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("/timetableElement")
public class TimetableElementController {

    @Autowired
    private TimetableElementService timetableElementService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/addTimetableElement")
    public ResponseEntity<TimetableElement> addTimetableElement(@RequestHeader("Authorization") String bearerToken,
                                                 @RequestBody TimetableElement timetableElement) {
        try {
            User user = UserDetermination.determineUser(bearerToken, jwtTokenProvider, userDetailsService);
            if (user != null) {
                timetableElement.setUser(user);
                timetableElementService.addElement(timetableElement);
                timetableElement.setUser(null);
                return new ResponseEntity<TimetableElement>(timetableElement, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (NotAddedToDatabase exception) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/deleteTimetableElement/{id}")
    public ResponseEntity<?> deleteTimetableElement(@RequestHeader("Authorization") String bearerToken,
                                                    @PathVariable(name = "id") Integer elementId) {
        User user = UserDetermination.determineUser(bearerToken, jwtTokenProvider, userDetailsService);
        if (user != null) {
            final boolean deleted = timetableElementService.deleteElement(elementId, user);
            return deleted
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/editTimetableElement/{id}")
    public ResponseEntity<TimetableElement> editTimetableElement(@RequestHeader("Authorization") String bearerToken,
                                                  @PathVariable("id") Integer elementId,
                                                  @RequestBody TimetableElement timetableElement) {
        User user = UserDetermination.determineUser(bearerToken, jwtTokenProvider, userDetailsService);
        if (user != null) {
            final boolean updated = timetableElementService.editElement(elementId, user, timetableElement);
            return updated
                    ? new ResponseEntity<TimetableElement>(timetableElement, HttpStatus.OK)
                    : new ResponseEntity<TimetableElement>(timetableElement, HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/listOfTimetableElements")
    public ResponseEntity<List<TimetableElement>> getAllTimetableElements() {
        List<TimetableElement> list = timetableElementService.listOfElements();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/listOfTimetableElements/{timeInMs}")
    public ResponseEntity<List<TimetableElement>> getTimetableElementsForDay(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable("timeInMs") long timeInMs) {
        User user = UserDetermination.determineUser(bearerToken, jwtTokenProvider, userDetailsService);
        if (user != null) {
            List<TimetableElement> list = timetableElementService.listOfElements(new Date(timeInMs), user);
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private List<TimetableElement> getTimetableElementsForDay(User user, long timeInMs) {
        return timetableElementService.listOfElements(new Date(timeInMs), user);
    }
}
