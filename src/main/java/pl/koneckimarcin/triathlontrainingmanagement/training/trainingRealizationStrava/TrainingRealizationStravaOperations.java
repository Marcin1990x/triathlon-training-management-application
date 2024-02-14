package pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealizationStrava;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TrainingRealizationStravaOperations {

    @PreAuthorize("hasAuthority('ATHLETE') AND @authenticatedUserService.hasValidAthleteId(#id)")
    @GetMapping("athletes/{id}/training-realizations")
    public List<TrainingRealizationStrava> getTrainingRealizationsByAthleteId(@PathVariable Long id);

    @PreAuthorize("hasAuthority('ATHLETE') AND @authenticatedUserService.hasTrainingRealizationInItsResources(#id)")
    @DeleteMapping("training-realizations/{id}")
    public void deleteById(@PathVariable Long id);
}
