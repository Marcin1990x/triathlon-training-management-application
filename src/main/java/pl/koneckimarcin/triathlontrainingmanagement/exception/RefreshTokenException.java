package pl.koneckimarcin.triathlontrainingmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RefreshTokenException extends RuntimeException {

    private HttpStatusCode httpStatusCode;
    private String errorMessage;

    public RefreshTokenException(HttpStatusCode httpStatusCode, String errorMessage) {
        super(String.format("Error while token refresh operation. Http status: %d, Error: %s",
                httpStatusCode.value(), errorMessage));
        this.httpStatusCode = httpStatusCode;
        this.errorMessage = errorMessage;
    }
}
