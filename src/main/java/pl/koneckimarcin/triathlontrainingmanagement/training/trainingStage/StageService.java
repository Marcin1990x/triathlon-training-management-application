package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage;

import java.util.List;

public interface StageService<T> {

    public List<T> getStagesForTrainingPlanById(Long id);
    public T addNewStageToTrainingPlan(Long id, T t);

}
