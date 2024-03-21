package pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Set;

public interface TrainingPlanOperations {

    @PreAuthorize("(hasAuthority('ATHLETE') AND @authenticatedUserService.hasValidAthleteId(#id)) " +
            "OR (hasAuthority('COACH') AND @authenticatedUserService.hasAssignedAthlete(#id))")
    @GetMapping("athletes/{id}/training-plans")
    public List<TrainingPlan> getTrainingPlansByAthleteId(@PathVariable Long id);

    @PreAuthorize("hasAuthority('COACH') AND @authenticatedUserService.hasValidCoachId(#id)")
    @GetMapping("coaches/{id}/training-plans")
    public Set<TrainingPlan> getTrainingPlansByCoachId(@PathVariable Long id);

    @PreAuthorize("hasAuthority('COACH') AND @authenticatedUserService.hasTrainingPlanInItsResources(#id)")
    @GetMapping("training-plans/{id}")
    public TrainingPlan getTrainingPlanById(@PathVariable Long id);

    @PreAuthorize("hasAuthority('COACH') AND @authenticatedUserService.hasTrainingPlanInItsResources(#id)")
    @DeleteMapping("training-plans/{id}")
    public void deleteById(@PathVariable Long id);

    @PreAuthorize("hasAuthority('COACH') AND @authenticatedUserService.hasValidCoachId(#id)")
    @PostMapping("coaches/{id}/training-plans")
    public TrainingPlan addNewTrainingPlan(@PathVariable Long id, @RequestBody TrainingPlan trainingPlan);

    @PreAuthorize("hasAuthority('COACH') AND @authenticatedUserService.hasAssignedAthlete(#athleteId)" +
            "AND @authenticatedUserService.hasTrainingPlanInItsResources(#trainingPlanId)")
    @PostMapping("athletes/{athleteId}/training-plans/{trainingPlanId}")
    public TrainingPlan addTrainingPlanToAthleteWithDate(
            @PathVariable Long athleteId, @PathVariable Long trainingPlanId, @RequestParam Date plannedDate);

    @PreAuthorize("hasAuthority('COACH') AND @authenticatedUserService.hasAssignedAthlete(#athleteId)")
    @PutMapping("athletes/{athleteId}/training-plans/{trainingPlanId}")
    public void removeTrainingPlanFromAthlete(@PathVariable Long athleteId, @PathVariable Long trainingPlanId);
}
