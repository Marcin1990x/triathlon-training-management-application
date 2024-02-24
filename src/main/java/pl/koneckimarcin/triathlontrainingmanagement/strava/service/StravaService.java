package pl.koneckimarcin.triathlontrainingmanagement.strava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.triathlontrainingmanagement.exception.RefreshTokenNotFoundException;
import pl.koneckimarcin.triathlontrainingmanagement.strava.StravaClient;
import pl.koneckimarcin.triathlontrainingmanagement.user.UserEntity;
import pl.koneckimarcin.triathlontrainingmanagement.user.UserRepository;

@Service
public class StravaService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StravaClient stravaClient;

    public void refreshAccessToken() {

        String userRefreshToken = getRefreshTokenForUser();

        String accessToken = stravaClient.refreshAccessToken(userRefreshToken);

        updateUserWithNewToken(accessToken);
    }

    private void updateUserWithNewToken(String accessToken) {

        UserEntity loggedUser = retrieveLoggedUser();
        loggedUser.setStravaAccessToken(accessToken);
        userRepository.save(loggedUser);
    }

    private String getRefreshTokenForUser() {

        String refreshToken = retrieveLoggedUser().getStravaRefreshToken();

        if (refreshToken == null) {
            throw new RefreshTokenNotFoundException();
        }
        return refreshToken;
    }

    private UserEntity retrieveLoggedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return userRepository.findByUsername(username).get();
    }
}
