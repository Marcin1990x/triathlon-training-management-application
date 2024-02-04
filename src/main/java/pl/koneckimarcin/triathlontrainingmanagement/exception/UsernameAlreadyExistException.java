package pl.koneckimarcin.triathlontrainingmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UsernameAlreadyExistException extends RuntimeException {

    private String username;

    public UsernameAlreadyExistException(String username) {
        super("This username: '" + username + "' is already in use. ");
        this.username = username;
    }
}