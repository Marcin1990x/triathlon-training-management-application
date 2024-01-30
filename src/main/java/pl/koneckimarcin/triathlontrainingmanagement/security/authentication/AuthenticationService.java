package pl.koneckimarcin.triathlontrainingmanagement.security.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public String authenticateUserAndGetToken(Authentication authentication) {

        if (authentication.isAuthenticated()) {
            return "User with username: '" + authentication.getName() + "' successfully authenticated.";
        } else {
            return null;
        }
    }
}
