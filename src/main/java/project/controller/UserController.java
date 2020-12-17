package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.entity.User;
import project.exception.NotAddedToDatabase;
import project.service.CustomUserDetailsService;
import project.service.mail.MailSender;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/registration")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try {
            userDetailsService.createUser(user);
            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        }
        catch (MailSendException e) {
            userDetailsService.deleteUser(user.getId());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (NotAddedToDatabase e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        catch (SecurityException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_MODIFIED);
        }
    }

    @GetMapping("/activate/{code}")
    public ResponseEntity<?> activate(@PathVariable(name = "code") String code) {
        boolean activated = userDetailsService.activateUser(code);
        if (activated) {
            return new ResponseEntity<>("User successfully activated!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Bad activation code!", HttpStatus.NOT_FOUND);
    }
}
