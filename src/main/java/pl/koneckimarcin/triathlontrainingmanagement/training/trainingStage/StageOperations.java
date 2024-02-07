package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.bike.BikeStage;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.run.RunStage;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.swim.SwimStage;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.weight.WeightStage;

import java.util.List;

public interface StageOperations {

    @PreAuthorize("hasAnyAuthority('COACH', 'ATHLETE') AND @authenticatedUserService.hasTrainingPlanInItsResources(#id)")
    @GetMapping("/training-plans/{id}/stages")
    public List<Stage> getStagesForTrainingPlanById(@PathVariable Long id);

    @PreAuthorize("hasAuthority('COACH') AND @authenticatedUserService.hasTrainingPlanInItsResources(#id)")
    @PostMapping("/training-plans/{id}/stages=bike")
    public Stage addNewBikeStageToTrainingPlan(@PathVariable Long id, @RequestBody BikeStage bikeStage);

    @PreAuthorize("hasAuthority('COACH') AND @authenticatedUserService.hasTrainingPlanInItsResources(#id)")
    @PostMapping("/training-plans/{id}/stages=run")
    public Stage addNewRunStageToTrainingPlan(@PathVariable Long id, @RequestBody RunStage runStage);

    @PreAuthorize("hasAuthority('COACH') AND @authenticatedUserService.hasTrainingPlanInItsResources(#id)")
    @PostMapping("/training-plans/{id}/stages=swim")
    public Stage addNewSwimStageToTrainingPlan(@PathVariable Long id, @RequestBody SwimStage swimStage);

    @PreAuthorize("hasAuthority('COACH') AND @authenticatedUserService.hasTrainingPlanInItsResources(#id)")
    @PostMapping("/training-plans/{id}/stages=weight")
    public Stage addNewWeightStageToTrainingPlan(@PathVariable Long id, @RequestBody WeightStage weightStage);

    @PreAuthorize("hasAuthority('COACH') AND @authenticatedUserService.hasStageInItsResources(#id)")
    @DeleteMapping("/stages/{id}")
    public void deleteStageById(@PathVariable Long id);

    @PreAuthorize("hasAuthority('COACH') AND @authenticatedUserService.hasTrainingPlanInItsResources(#id)")
    @DeleteMapping("/training-plans/{id}/stages")
    public void deleteAllStagesFromTrainingPlanById(@PathVariable Long id);

    @PreAuthorize("hasAuthority('COACH') AND @authenticatedUserService.hasTrainingPlanInItsResources(#id)")
    @PutMapping("/training-plans/{id}/stages")
    public void swapStagesSequence(@PathVariable Long id, @RequestParam Long firstStageId, @RequestParam Long secondStageId);
}
