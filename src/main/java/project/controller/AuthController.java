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
import project.exception.InvalidJwtAuthenticationException;
import project.repository.UserRepository;
import project.security.jwt.JwtTokenProvider;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
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

//            if (user.getActivationCode() != null) {
//                return new ResponseEntity<>("User is not activated!", HttpStatus.NOT_MODIFIED);
//            }

            String token = jwtTokenProvider.createToken(user, false, user.getRoles());
            String tokenRefresh = jwtTokenProvider.createToken(user, true, user.getRoles());

            // let's send the token back to the user
            return ResponseEntity.ok(createModel(name, token, tokenRefresh));
        } catch (UsernameNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshRequest request) {
        String refreshToken = request.getRefreshToken();
        String name = request.getUsername();

        if (refreshToken != null) {
            try {
                if (jwtTokenProvider.validateToken(refreshToken)) {
                    User user = userRepository.findUserByUserName(name)
                            .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
                    String newAccessToken = jwtTokenProvider.createToken(user, false, user.getRoles());
                    String newRefreshToken = jwtTokenProvider.createToken(user, true, user.getRoles());
                    Authentication auth = jwtTokenProvider.getAuthentication(newAccessToken);
                    if (auth != null) {
                        SecurityContextHolder.getContext().setAuthentication(auth);

                        // let's send the token back to the user
                        return ResponseEntity.ok(createModel(name, newAccessToken, newRefreshToken));
                    }
                }
            }
            catch (InvalidJwtAuthenticationException | UsernameNotFoundException e) {
                System.out.println(e.getMessage());
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    private Map<Object, Object> createModel(String name, String token, String tokenRefresh) {
        Map<Object, Object> model = new HashMap<>();
        model.put("userName", name);
        model.put("token", token);
        model.put("tokenRefresh", tokenRefresh);
        return model;
    }
}
