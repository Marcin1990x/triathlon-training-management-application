package pl.koneckimarcin.triathlontrainingmanagement.security;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.triathlontrainingmanagement.exception.EmailAddressAlreadyExistException;
import pl.koneckimarcin.triathlontrainingmanagement.user.User;
import pl.koneckimarcin.triathlontrainingmanagement.user.UserEntity;
import pl.koneckimarcin.triathlontrainingmanagement.user.UserRepository;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<String> registerUser(@Valid User user) {

        ResponseEntity<String> response;

        if(userRepository.findByEmailAddress(user.getEmailAddress()).isEmpty()) {
            response = handleSaveUser(user);
        } else {
            throw new EmailAddressAlreadyExistException(user.getEmailAddress());
        }

        return response;
    }

    private ResponseEntity<String> handleSaveUser(User user) {

        UserEntity savedUserEntity;
        ResponseEntity<String> response = null;

        try {
            savedUserEntity = userRepository.save(user.mapToUserEntity());
            if (savedUserEntity.getId() > 0) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given user details are successfully registered.");
            }
        } catch (Exception exception) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + exception.getMessage());
        }
        return response;
    }
}
