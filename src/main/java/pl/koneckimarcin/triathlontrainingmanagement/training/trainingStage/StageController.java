package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface StageController {
    @GetMapping("/training-plans/{id}/stages")
    public List<Stage> getStagesForTrainingPlanById(@PathVariable Long id);
    @PostMapping("/training-plans/{id}/stages")
    public Stage addNewStageToTrainingPlan(@PathVariable Long id, @RequestBody Stage stage);
}
