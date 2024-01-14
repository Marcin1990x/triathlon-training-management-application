package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface StageOperations <T> {
    @GetMapping("/training-plans/{id}")
    public List<T> getStagesForTrainingPlanById(@PathVariable Long id);
}
