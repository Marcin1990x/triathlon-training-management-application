package pl.koneckimarcin.triathlontrainingmanagement.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.triathlontrainingmanagement.security.authentication.AuthenticatedUserService;
import pl.koneckimarcin.triathlontrainingmanagement.user.dto.User;
import pl.koneckimarcin.triathlontrainingmanagement.user.dto.UserStravaDto;

import java.util.List;

@RestController
public class UserController implements UserOperations {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    @Override
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public User getUserById(Long id) {

        return userService.getUserById(id);
    }

    @Override
    public User addCoachToUser(Long userId, Long coachId) {

        return userService.addCoachToUser(userId, coachId);
    }

    @Override
    public User addAthleteToUser(Long userId, Long athleteId) {

        return userService.addAthleteToUser(userId, athleteId);
    }

    @Override
    public UserStravaDto refreshAccessTokenForUser(Long id) {

        return userService.refreshAccessTokenForUser(id);
    }
}
