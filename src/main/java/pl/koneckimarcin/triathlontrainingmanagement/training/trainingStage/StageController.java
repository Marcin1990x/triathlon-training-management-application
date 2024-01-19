package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface StageController<T> {
    @GetMapping("/training-plans/{id}/stages")
    public List<T> getStagesForTrainingPlanById(@PathVariable Long id);
    @PostMapping("training-plans/{id}/stages")
    public T addNewStageToTrainingPlan(@PathVariable Long id, @RequestBody T t);
}
