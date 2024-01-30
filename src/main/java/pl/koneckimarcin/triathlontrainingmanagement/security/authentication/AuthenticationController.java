package pl.koneckimarcin.triathlontrainingmanagement.security.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.triathlontrainingmanagement.user.UserRepository;

@RestController
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public String authenticateUserAndGetToken(Authentication authentication) {

        return authenticationService.authenticateUserAndGetToken(authentication);
    }
}
