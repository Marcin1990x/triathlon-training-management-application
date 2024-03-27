package pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.dto.TrainingRealization;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.dto.TrainingRealizationRequest;

import java.util.List;

public interface TrainingRealizationOperations {

    @PreAuthorize("(hasAuthority('ATHLETE') AND @authenticatedUserService.hasValidAthleteId(#id)) " +
            "OR (hasAuthority('COACH') AND @authenticatedUserService.hasAssignedAthlete(#id))")
    @GetMapping("athletes/{id}/training-realizations")
    public List<TrainingRealization> getTrainingRealizationsByAthleteId(@PathVariable Long id);

    @PreAuthorize("hasAuthority('ATHLETE') AND @authenticatedUserService.hasTrainingRealizationInItsResources(#id)")
    @DeleteMapping("training-realizations/{id}")
    public void deleteById(@PathVariable Long id);

    @PreAuthorize("hasAuthority('ATHLETE')")
    @PutMapping("athletes/{id}/training-realizations")
    public Integer synchronizeActivitiesForAthlete(@PathVariable Long id);

    @PreAuthorize("hasAuthority('ATHLETE') AND @authenticatedUserService.hasTrainingRealizationInItsResources(#id)")
    @PutMapping("training-realizations/{id}")
    public TrainingRealization updateTrainingRealizationById(@PathVariable Long id,
            @RequestBody TrainingRealizationRequest request);

    @PreAuthorize("(hasAuthority('ATHLETE') AND @authenticatedUserService.hasValidAthleteId(#id))")
    @PostMapping("athletes/{id}/training-realizations")
    public TrainingRealization addNewTrainingRealizationForAthlete
            (@PathVariable Long id, @RequestBody TrainingRealization trainingRealization);
}
