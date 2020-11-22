package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.controller.utils.UserDetermination;
import project.entity.BreeksLine;
import project.entity.User;
import project.exception.NotAddedToDatabase;
import project.security.jwt.JwtTokenProvider;
import project.service.BreeksLineService;
import project.service.CustomUserDetailsService;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/breeks")
public class BreeksLineController {

    @Autowired
    private BreeksLineService breeksLineService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/addLine")
    public ResponseEntity<?> addLine(@RequestHeader("Authorization") String bearerToken,
                                     @RequestBody BreeksLine line) {
        try {
            User user = UserDetermination.determineUser(bearerToken, jwtTokenProvider, userDetailsService);
            if (user != null) {
                line.setUser(user);
                breeksLineService.addLine(line);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (NotAddedToDatabase exception) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/deleteLine/{id}")
    public ResponseEntity<?> deleteLine(@PathVariable(name = "id") int id) {
        final boolean deleted = breeksLineService.deleteLine(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/deleteLine/{timeInMs}/{description}")
    public ResponseEntity<?> deleteLine(@RequestHeader("Authorization") String bearerToken,
                                        @PathVariable(name = "timeInMs") long timeInMs,
                                        @PathVariable(name = "description") String description) {
        User user = UserDetermination.determineUser(bearerToken, jwtTokenProvider, userDetailsService);
        if (user != null) {
            final boolean deleted = breeksLineService.deleteLine(new Date(timeInMs), description, user);
            return deleted
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/editLine/{timeInMs}/{description}")
    public ResponseEntity<?> editLine(@RequestHeader("Authorization") String bearerToken,
                                      @PathVariable(name = "timeInMs") long timeInMs,
                                      @PathVariable(name = "description") String description,
                                      @RequestBody BreeksLine line) {
        User user = UserDetermination.determineUser(bearerToken, jwtTokenProvider, userDetailsService);
        if (user != null) {
            final boolean updated = breeksLineService.editLine(new Date(timeInMs), description, user, line);
            return updated
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/listOfLines/{timeInMs}")
    public ResponseEntity<List<BreeksLine>> getAllLinesInWeek(@RequestHeader("Authorization") String bearerToken,
                                                        @PathVariable(name = "timeInMs") long timeInMs) {
        User user = UserDetermination.determineUser(bearerToken, jwtTokenProvider, userDetailsService);
        if (user != null) {
            List<BreeksLine> list = breeksLineService.listOfLinesInWeek(new Date(timeInMs), user);
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
