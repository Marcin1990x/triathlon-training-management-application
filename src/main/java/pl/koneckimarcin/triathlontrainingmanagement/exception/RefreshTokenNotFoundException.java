package pl.koneckimarcin.triathlontrainingmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RefreshTokenNotFoundException extends RuntimeException{

    private Long userId;

    public RefreshTokenNotFoundException(Long userId) {
        super(String.format("Refresh token for user with id: '%d' does not exists", userId));
    }
}
