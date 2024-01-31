package pl.koneckimarcin.triathlontrainingmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IsAlreadyAssigned extends RuntimeException {

    private String resourceNameToAssign;
    private String resourceIdToAssign;
    private String resourceNameAssignTo;
    private String resourceIdAssignTo;

    public IsAlreadyAssigned(String resourceNameToAssign, String resourceIdToAssign, String resourceNameAssignTo, String resourceIdAssignTo) {
        super(String.format("%s with id: %s is already assigned to %s with id: %s", resourceNameToAssign,
                resourceIdToAssign, resourceNameAssignTo ,resourceIdAssignTo));
        this.resourceNameToAssign = resourceNameToAssign;
        this.resourceIdToAssign = resourceIdToAssign;
        this.resourceNameAssignTo = resourceNameAssignTo;
        this.resourceIdAssignTo = resourceIdAssignTo;
    }
}
