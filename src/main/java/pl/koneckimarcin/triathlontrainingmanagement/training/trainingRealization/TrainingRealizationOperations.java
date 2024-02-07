package pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface TrainingRealizationOperations {

    @PreAuthorize("hasAuthority('ATHLETE') AND @authenticatedUserService.hasValidId(#id)")
    @GetMapping("athletes/{id}/training-realizations")
    public List<TrainingRealization> getTrainingRealizationsByAthleteId(@PathVariable Long id);

    @PreAuthorize("hasAuthority('ATHLETE') AND @authenticatedUserService.hasTrainingRealizationInItsResources(#id)")
    @DeleteMapping("training-realizations/{id}")
    public void deleteById(@PathVariable Long id);

    @PreAuthorize("hasAuthority('ATHLETE') AND @authenticatedUserService.hasValidId(#id)")
    @PostMapping("athletes/{id}/training-realizations")
    public TrainingRealization addNewTrainingRealizationToAthlete
            (@PathVariable Long id, @Valid @RequestBody TrainingRealization trainingRealization);
}
