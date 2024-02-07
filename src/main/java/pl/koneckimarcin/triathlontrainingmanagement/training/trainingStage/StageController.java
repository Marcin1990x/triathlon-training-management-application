package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.bike.BikeStage;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.run.RunStage;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.swim.SwimStage;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.weight.WeightStage;

import java.util.List;

@RestController
public class StageController implements StageOperations {

    @Autowired
    private StageServiceImpl service;

    @Override
    public List<Stage> getStagesForTrainingPlanById(Long id) {

        return service.getStagesForTrainingPlanById(id);
    }

    @Override
    public Stage addNewBikeStageToTrainingPlan(Long id, BikeStage bikeStage) {

        return service.addNewBikeStageToTrainingPlan(id, bikeStage);
    }

    @Override
    public Stage addNewRunStageToTrainingPlan(Long id, RunStage runStage) {

        return service.addNewRunStageToTrainingPlan(id, runStage);
    }

    @Override
    public Stage addNewSwimStageToTrainingPlan(Long id, SwimStage swimStage) {

        return service.addNewSwimStageToTrainingPlan(id, swimStage);
    }

    @Override
    public Stage addNewWeightStageToTrainingPlan(Long id, WeightStage weightStage) {

        return service.addNewWeightStageToTrainingPlan(id, weightStage);
    }

    @Override
    public void deleteStageById(Long id) {

        service.deleteStageById(id);
    }

    @Override
    public void deleteAllStagesFromTrainingPlanById(Long id) {

        service.deleteAllStagesFromTrainingPlanById(id);
    }

    @Override
    public void swapStagesSequence(Long id, Long firstStageId, Long secondStageId) {

        service.swapStagesSequence(id, firstStageId, secondStageId);
    }

}
