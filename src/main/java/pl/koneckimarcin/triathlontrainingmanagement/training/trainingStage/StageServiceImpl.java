package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.triathlontrainingmanagement.exception.IncompatibleTrainingTypeException;
import pl.koneckimarcin.triathlontrainingmanagement.exception.ResourceNotFoundException;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanEntity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanRepository;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanService;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.constant.TrainingType;
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
    private StageRepository stageRepository;

    @Autowired
    private TrainingPlanRepository trainingPlanRepository;

    @Autowired
    private TrainingPlanService trainingPlanService;

    @Override
    public boolean checkIfIsNotNull(Long id) {
        Optional<StageEntity> stageEntity = stageRepository.findById(id);
        if (stageEntity.isPresent()) {
            return true;
        }
        return false;
    }

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
    public Stage addNewBikeStageToTrainingPlan(Long id, @Valid BikeStage bikeStage) {

        return addStageDependingOnType(id, bikeStage);
    }

    @Override
    public Stage addNewRunStageToTrainingPlan(Long id, @Valid RunStage runStage) {

        return addStageDependingOnType(id, runStage);
    }

    @Override
    public Stage addNewSwimStageToTrainingPlan(Long id, SwimStage swimStage) {

        return addStageDependingOnType(id, swimStage);
    }

    @Override
    public Stage addNewWeightStageToTrainingPlan(Long id, WeightStage weightStage) {

        return addStageDependingOnType(id, weightStage);
    }

    @Override
    public void deleteStageById(Long id) {

        if (checkIfIsNotNull(id)) {
            stageRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Stage", "id", String.valueOf(id));
        }
    }

    @Override
    public void deleteAllStagesFromTrainingPlanById(Long id) {

        Optional<TrainingPlanEntity> trainingPlanEntity = trainingPlanRepository.findById(id);

        if(trainingPlanEntity.isPresent()){

            trainingPlanEntity.get().getStages().clear();
            trainingPlanRepository.save(trainingPlanEntity.get());
        } else {
            throw new ResourceNotFoundException("TrainingPlan", "id", String.valueOf(id));
        }
    }

    @Override
    public void swapStagesSequence(Long firstStageId, Long secondStageId) {

        swapSequenceAndSave(firstStageId, secondStageId);
    }

    private void swapSequenceAndSave(Long firstStageId, Long secondStageId) {

        Optional <StageEntity> firstStage = stageRepository.findById(firstStageId);
        Optional <StageEntity> secondStage = stageRepository.findById(secondStageId);

        if(firstStage.isPresent()){
            if(secondStage.isPresent()) {
                int tempSequence = firstStage.get().getSequence();
                firstStage.get().setSequence(secondStage.get().getSequence());
                secondStage.get().setSequence(tempSequence);

                stageRepository.save(firstStage.get());
                stageRepository.save(secondStage.get());
            } else {
                throw new ResourceNotFoundException("Stage", "id", String.valueOf(secondStageId));
            }
        } else {
            throw new ResourceNotFoundException("Stage", "id", String.valueOf(firstStageId));
        }
    }
    private Stage addStageDependingOnType(Long id, Stage stage) {

        Optional<TrainingPlanEntity> trainingPlanEntity = trainingPlanRepository.findById(id);

        if (trainingPlanEntity.isPresent()) {

            TrainingType trainingType = trainingPlanEntity.get().getTrainingType();
            List<StageEntity> stages = trainingPlanEntity.get().getStages();

            if (stage instanceof BikeStage) {
                if (trainingType == TrainingType.BIKE) {
                    stages.add(((BikeStage) stage).mapToBikeStageEntity());
                } else {
                    throw new IncompatibleTrainingTypeException(stage, TrainingType.BIKE);
                }
            }
            if (stage instanceof RunStage) {
                if (trainingType == TrainingType.RUN) {
                    stages.add(((RunStage) stage).mapToRunStageEntity());
                } else {
                    throw new IncompatibleTrainingTypeException(stage, TrainingType.RUN);
                }
            }
            if (stage instanceof SwimStage) {
                if (trainingType == TrainingType.SWIM) {
                    stages.add(((SwimStage) stage).mapToSwimStageEntity());
                } else {
                    throw new IncompatibleTrainingTypeException(stage, TrainingType.SWIM);
                }
            }
            if (stage instanceof WeightStage) {
                if (trainingType == TrainingType.WEIGHT) {
                    stages.add(((WeightStage) stage).mapToWeightStageEntity());
                } else {
                    throw new IncompatibleTrainingTypeException(stage, TrainingType.WEIGHT);
                }
            }
            trainingPlanRepository.save(trainingPlanEntity.get());
            return stage;
        } else {
            throw new ResourceNotFoundException("TrainingPlan", "id", String.valueOf(id));
        }
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
