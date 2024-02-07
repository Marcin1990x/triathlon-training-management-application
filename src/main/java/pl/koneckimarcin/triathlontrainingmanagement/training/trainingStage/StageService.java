package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage;

import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.bike.BikeStage;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.run.RunStage;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.swim.SwimStage;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.weight.WeightStage;

import java.util.List;

public interface StageService {

    public boolean checkIfIsNotNull(Long id);

    public List<Stage> getStagesForTrainingPlanById(Long id);

    public Stage addNewBikeStageToTrainingPlan(Long id, BikeStage bikeStage);

    public Stage addNewRunStageToTrainingPlan(Long id, RunStage runStage);

    public Stage addNewSwimStageToTrainingPlan(Long id, SwimStage swimStage);

    public Stage addNewWeightStageToTrainingPlan(Long id, WeightStage weightStage);

    public void deleteStageById(Long id);

    public void deleteAllStagesFromTrainingPlanById(Long id);

    public void swapStagesSequence(Long planId, Long firstStageId, Long secondStageId);
}
