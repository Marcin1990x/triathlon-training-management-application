package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.triathlontrainingmanagement.exception.ResourceNotFoundException;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanEntity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanRepository;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanService;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.bike.BikeStage;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.bike.BikeStageEntity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.run.RunStage;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.run.RunStageEntity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.swim.SwimStage;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.swim.SwimStageEntity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.weight.WeightStage;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.weight.WeightStageEntity;

import java.util.List;
import java.util.Optional;

@Service
public class StageServiceImpl implements StageService {

    @Autowired
    private StageRepository repository;

    @Autowired
    private TrainingPlanRepository trainingPlanRepository;

    @Autowired
    private TrainingPlanService trainingPlanService;

    @Override
    public List<Stage> getStagesForTrainingPlanById(Long id) {

        Optional<TrainingPlanEntity> trainingPlanEntity = trainingPlanRepository.findById(id);
        if (trainingPlanService.checkIfIsNotNull(id)) {
            List<StageEntity> stageEntities = trainingPlanEntity.get().getStages();
            if (stageEntities.size() > 0) {
                return getStagesForTrainingType(stageEntities);
            } else return null;
        } else {
            throw new ResourceNotFoundException("TrainingPlan", "id", String.valueOf(id));
        }
    }

    @Override
    public Stage addNewStageToTrainingPlan(Long id, Stage stage) {
        return null;
    }

    private List<Stage> getStagesForTrainingType(List<StageEntity> stageEntities) {

        if (stageEntities.get(0) instanceof RunStageEntity) {
            return stageEntities.stream().map(stageEntity -> RunStage.fromRunStageEntity((RunStageEntity) stageEntity))
                    .map(runStage -> (Stage) runStage).toList();
        }
        if (stageEntities.get(0) instanceof BikeStageEntity) {
            return stageEntities.stream().map(stageEntity -> BikeStage.fromBikeStageEntity((BikeStageEntity) stageEntity))
                    .map(bikeStage -> (Stage) bikeStage).toList();
        }
        if (stageEntities.get(0) instanceof SwimStageEntity) {
            return stageEntities.stream().map(stageEntity -> SwimStage.fromSwimStageEntity((SwimStageEntity) stageEntity))
                    .map(swimStage -> (Stage) swimStage).toList();
        }
        if (stageEntities.get(0) instanceof WeightStageEntity) {
            return stageEntities.stream().map(stageEntity -> WeightStage.fromWeightStageEntity((WeightStageEntity) stageEntity))
                    .map(weightStage -> (Stage) weightStage).toList();
        }
        return null;
    }
}
