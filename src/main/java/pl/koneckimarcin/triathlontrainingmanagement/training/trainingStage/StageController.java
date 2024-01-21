package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
}
