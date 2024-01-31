package pl.koneckimarcin.triathlontrainingmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IsAlreadyAssignedException extends RuntimeException {

    private String resourceNameToAssign;
    private String resourceIdToAssign;

    public IsAlreadyAssignedException(String resourceNameToAssign, String resourceIdToAssign) {
        super(String.format("%s with id: %s is already assigned.", resourceNameToAssign, resourceIdToAssign));
        this.resourceNameToAssign = resourceNameToAssign;
        this.resourceIdToAssign = resourceIdToAssign;
    }
}
