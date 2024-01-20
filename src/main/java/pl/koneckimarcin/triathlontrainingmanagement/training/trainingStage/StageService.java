package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage;

import java.util.List;

public interface StageService {

    public List<Stage> getStagesForTrainingPlanById(Long id);

    public Stage addNewStageToTrainingPlan(Long id, Stage stage);

}
