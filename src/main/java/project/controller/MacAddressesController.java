package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.controller.utils.UserDetermination;
import project.entity.MacAddresses;
import project.entity.User;
import project.exception.NotAddedToDatabase;
import project.security.jwt.JwtTokenProvider;
import project.service.CustomUserDetailsService;
import project.service.MacAddressesService;

@RestController
@RequestMapping("/session")
public class MacAddressesController {

    @Autowired
    private MacAddressesService macAddressesService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping("/findAddress")
    public ResponseEntity<?> findAddress(@RequestBody MacAddresses address) {
        if (macAddressesService.existsByAddress(address.getAddress())) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/addAddress")
    public ResponseEntity<MacAddresses> addAddress(@RequestHeader("Authorization") String bearerToken,
                                                   @RequestBody MacAddresses address) {
        try {
            User user = UserDetermination.determineUser(bearerToken, jwtTokenProvider, userDetailsService);
            if (user != null) {
                address.setUser(user);
                macAddressesService.addAddress(address);
                return new ResponseEntity<>(address, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        catch (NotAddedToDatabase exception) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/deleteAddress")
    public ResponseEntity<?> deleteAddress(@RequestBody MacAddresses address) {
        if (macAddressesService.deleteAddress(address.getAddress())) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
