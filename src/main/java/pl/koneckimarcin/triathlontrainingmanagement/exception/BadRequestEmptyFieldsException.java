package pl.koneckimarcin.triathlontrainingmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestEmptyFieldsException extends RuntimeException {

    private List<String> emptyFields;

    public BadRequestEmptyFieldsException(List<String> emptyFields) {
        super("This fields can not be empty: " + emptyFields);
        this.emptyFields = emptyFields;
    }
}
