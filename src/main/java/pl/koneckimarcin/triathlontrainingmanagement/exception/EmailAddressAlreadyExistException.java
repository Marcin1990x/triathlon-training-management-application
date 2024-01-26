package pl.koneckimarcin.triathlontrainingmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAddressAlreadyExistException extends RuntimeException {

    private String emailAddress;

    public EmailAddressAlreadyExistException(String emailAddress) {
        super("This email address: '" + emailAddress + "' is already in use. ");
        this.emailAddress = emailAddress;
    }
}