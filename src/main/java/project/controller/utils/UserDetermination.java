package project.controller.utils;

import project.entity.User;
import project.security.jwt.JwtTokenProvider;
import project.service.CustomUserDetailsService;

public class UserDetermination {

    public static User determineUser(String bearerToken, JwtTokenProvider jwtTokenProvider,
                                     CustomUserDetailsService userDetailsService) {
        String token = jwtTokenProvider.resolveToken(bearerToken);
        if (token != null) {
            int userId = jwtTokenProvider.getUserId(token);
            return userDetailsService.loadUserById(userId);
        }
        return null;
    }
}
