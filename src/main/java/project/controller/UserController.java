package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.entity.User;
import project.exception.NotAddedToDatabase;
import project.service.CustomUserDetailsService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/registration")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try {
            userDetailsService.createUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (NotAddedToDatabase e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") int id) {
        final boolean deleted = userDetailsService.deleteUser(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/editUser/{id}")
    public ResponseEntity<?> editUser(@PathVariable(name = "id") int id,
                                              @RequestBody User user) {
        final boolean updated = userDetailsService.editUser(id, user);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/listOfUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> list = userDetailsService.listOfUsers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}