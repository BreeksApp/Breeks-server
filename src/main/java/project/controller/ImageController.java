package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.controller.utils.UserDetermination;
import project.entity.Image;
import project.entity.User;
import project.exception.NotAddedToDatabase;
import project.security.jwt.JwtTokenProvider;
import project.service.CustomUserDetailsService;
import project.service.ImageService;

import java.sql.Date;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/addImage")
    public ResponseEntity<?> addImage(@RequestHeader("Authorization") String bearerToken,
                                      @RequestBody Image image) {
        try {
            User user = UserDetermination.determineUser(bearerToken, jwtTokenProvider, userDetailsService);
            if (user != null) {
                image.setUser(user);
                imageService.addImage(image);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (NotAddedToDatabase exception) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/deleteImage/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable("id") int id) {
        boolean deleted = imageService.deleteImage(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/editImage/{timeInMs}")
    public ResponseEntity<?> editImage(@RequestHeader("Authorization") String bearerToken,
                                       @PathVariable("timeInMs") long timeInMs,
                                       @RequestBody Image image) {
        User user = UserDetermination.determineUser(bearerToken, jwtTokenProvider, userDetailsService);
        if (user != null) {
            boolean updated = imageService.editImage(new Date(timeInMs), user, image);
            return updated
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getImage/{timeInMs}")
    public ResponseEntity<Image> getImage(@RequestHeader("Authorization") String bearerToken,
                                          @PathVariable("timeInMs") long timeInMs) {
        User user = UserDetermination.determineUser(bearerToken, jwtTokenProvider, userDetailsService);
        if (user != null) {
            Image image = imageService.findImage(new Date(timeInMs), user);
            if (image != null) return new ResponseEntity<>(image, HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
