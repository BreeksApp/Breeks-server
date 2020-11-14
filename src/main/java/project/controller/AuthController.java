package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.controller.requests.AuthRequest;
import project.controller.requests.RefreshRequest;
import project.entity.User;
import project.repository.UserRepository;
import project.security.jwt.JwtTokenProvider;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping({"/auth", "/"})
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody AuthRequest request) {
        try {
            String name = request.getUsername();
            User user = userRepository.findUserByUserName(name)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                throw new BadCredentialsException("Invalid password!");
            }

            String token = jwtTokenProvider.createToken(name, false);
            String tokenRefresh = jwtTokenProvider.createToken(name, true);

            // let's send the token back to the user
            return ResponseEntity.ok(createModel(name, token, tokenRefresh));
        }
        catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password!");
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshRequest request) {
        String refreshToken = request.getRefreshToken();
        String name = request.getUsername();

        if (refreshToken != null && jwtTokenProvider.validateToken(refreshToken)) {
            String newAccessToken = jwtTokenProvider.createToken(name, false);
            String newRefreshToken = jwtTokenProvider.createToken(name, true);
            Authentication auth = jwtTokenProvider.getAuthentication(newAccessToken);
            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

            // let's send the token back to the user
            return ResponseEntity.ok(createModel(name, newAccessToken, newRefreshToken));
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private Map<Object, Object> createModel(String name, String token, String tokenRefresh) {
        Map<Object, Object> model = new HashMap<>();
        model.put("userName", name);
        model.put("token", token);
        model.put("tokenRefresh", tokenRefresh);
        return model;
    }
}