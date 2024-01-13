package pl.koneckimarcin.triathlontrainingmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongDateException extends RuntimeException {

    private String date;

    public WrongDateException(String date) {
        super("Given date should be now or in the future " + date);
        this.date = date;
    }
}
