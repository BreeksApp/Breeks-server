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
    public ResponseEntity<?> addTimetableElement(@RequestHeader("Authorization") String bearerToken,
                                                 @RequestBody TimetableElement timetableElement) {
        try {
            User user = UserDetermination.determineUser(bearerToken, jwtTokenProvider, userDetailsService);
            if (user != null) {
                timetableElement.setUser(user);
                timetableElementService.addElement(timetableElement);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (NotAddedToDatabase exception) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/deleteTimetableElement/{id}")
    public ResponseEntity<?> deleteTimetableElement(@PathVariable(name = "id") int id) {
        final boolean deleted = timetableElementService.deleteElement(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/editTimetableElement/{id}")
    public ResponseEntity<?> editTimetableElement(@PathVariable(name = "id") int id,
                                              @RequestBody TimetableElement timetableElement) {
        final boolean updated = timetableElementService.editElement(id, timetableElement);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/listOfTimetableElements")
    public ResponseEntity<List<TimetableElement>> getAllTimetableElements() {
        List<TimetableElement> list = timetableElementService.listOfElements();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
