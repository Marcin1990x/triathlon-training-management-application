package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage;

import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.bike.BikeStage;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.run.RunStage;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.swim.SwimStage;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.weight.WeightStage;

import java.util.List;

public interface StageController {
    @GetMapping("/training-plans/{id}/stages")
    public List<Stage> getStagesForTrainingPlanById(@PathVariable Long id);

    @PostMapping("/training-plans/{id}/stages=bike")
    public Stage addNewBikeStageToTrainingPlan(@PathVariable Long id, @RequestBody BikeStage bikeStage);

    @PostMapping("/training-plans/{id}/stages=run")
    public Stage addNewRunStageToTrainingPlan(@PathVariable Long id, @RequestBody RunStage runStage);

    @PostMapping("/training-plans/{id}/stages=swim")
    public Stage addNewSwimStageToTrainingPlan(@PathVariable Long id, @RequestBody SwimStage swimStage);

    @PostMapping("/training-plans/{id}/stages=weight")
    public Stage addNewWeightStageToTrainingPlan(@PathVariable Long id, @RequestBody WeightStage weightStage);

    @DeleteMapping("/stages/{id}")
    public void deleteStageById(@PathVariable Long id);

    @DeleteMapping("/training-plans/{id}/stages")
    public void deleteAllStagesFromTrainingPlanById(@PathVariable Long id);

    @PutMapping("/stages")
    public void swapStagesSequence(@RequestParam Long firstStageId, @RequestParam Long secondStageId);
}
