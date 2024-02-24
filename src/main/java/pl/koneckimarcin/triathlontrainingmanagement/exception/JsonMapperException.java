package pl.koneckimarcin.triathlontrainingmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class JsonMapperException extends RuntimeException {

    public JsonMapperException() {
        super("Error while mapping refresh token post response to RefreshTokenResponseDto.class");
    }
}
