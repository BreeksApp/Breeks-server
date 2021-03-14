package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.controller.utils.UserDetermination;
import project.entity.SessionKey;
import project.entity.User;
import project.exception.NotAddedToDatabase;
import project.security.jwt.JwtTokenProvider;
import project.service.CustomUserDetailsService;
import project.service.SessionKeyService;

@RestController
@RequestMapping("/session")
public class SessionKeyController {

    @Autowired
    private SessionKeyService sessionKeyService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/generateKey")
    public ResponseEntity<SessionKey> generateKey(@RequestHeader("Authorization") String bearerToken,
                                                 @RequestBody SessionKey key) {
        try {
            User user = UserDetermination.determineUser(bearerToken, jwtTokenProvider, userDetailsService);
            if (user != null) {
                if (key != null && sessionKeyService.existsByKey(key.getKey())) {
                    return new ResponseEntity<>(key, HttpStatus.OK);
                }

                key = sessionKeyService.generateKey(user);

                return new ResponseEntity<>(key, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        catch (NotAddedToDatabase exception) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/deleteKey")
    public ResponseEntity<?> deleteKey(@RequestBody SessionKey key) {
        if (sessionKeyService.deleteKey(key.getKey())) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
