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
    public ResponseEntity<BreeksLine> addLine(@RequestHeader("Authorization") String bearerToken,
                                     @RequestBody BreeksLine line) {
        try {
            User user = UserDetermination.determineUser(bearerToken, jwtTokenProvider, userDetailsService);
            if (user != null) {
                line.setUser(user);
                breeksLineService.addLine(line);
                return new ResponseEntity<>(line, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        catch (NotAddedToDatabase exception) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/deleteLine/{id}")
    public ResponseEntity<?> deleteLine(@RequestHeader("Authorization") String bearerToken,
                                        @PathVariable(name = "id") int id) {
        User user = UserDetermination.determineUser(bearerToken, jwtTokenProvider, userDetailsService);
        if (user != null) {
            final boolean deleted = breeksLineService.deleteLine(id, user.getId());
            return deleted
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    @PutMapping("/editLine/{id}")
    public ResponseEntity<?> editLine(@RequestHeader("Authorization") String bearerToken,
                                      @PathVariable(name = "id") Integer id,
                                      @RequestBody BreeksLine line) {
        User user = UserDetermination.determineUser(bearerToken, jwtTokenProvider, userDetailsService);
        if (user != null) {
            final boolean updated = breeksLineService.editLine(id, line, user);
            return updated
                    ? new ResponseEntity<>(line, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/listOfLines/{timeInMs}")
    public ResponseEntity<List<BreeksLine>> getAllLinesInWeek(@RequestHeader("Authorization") String bearerToken,
                                                        @PathVariable(name = "timeInMs") long timeInMs) {
        User user = UserDetermination.determineUser(bearerToken, jwtTokenProvider, userDetailsService);
        if (user != null) {
            List<BreeksLine> list = breeksLineService.listOfLinesInWeek(new Date(timeInMs), user);
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
