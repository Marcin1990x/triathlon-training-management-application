package pl.koneckimarcin.triathlontrainingmanagement.security.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.triathlontrainingmanagement.user.UserEntity;
import pl.koneckimarcin.triathlontrainingmanagement.user.UserRepository;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    public AuthenticationResponseDto authenticateUserAndGetToken(Authentication authentication) {

        if (authentication.isAuthenticated()) {
            return createResponse(authentication.getName());
        } else {
            return null;
        }
    }

    private AuthenticationResponseDto createResponse(String username) {

        UserEntity authenticatedUser = userRepository.findByUsername(username).get();

        return new AuthenticationResponseDto(authenticatedUser.getId(),
                authenticatedUser.getAthleteEntity().getId());
    }

}
