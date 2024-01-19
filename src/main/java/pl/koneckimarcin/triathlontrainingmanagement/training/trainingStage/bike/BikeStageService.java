package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.bike;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.triathlontrainingmanagement.exception.ResourceNotFoundException;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanEntity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanRepository;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanService;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.StageService;

import java.util.List;
import java.util.Optional;

@Service
public class BikeStageService implements StageService<BikeStage> {

    @Autowired
    private TrainingPlanRepository trainingPlanRepository;
    @Autowired
    private TrainingPlanService trainingPlanService;

    @Override
    public List<BikeStage> getStagesForTrainingPlanById(Long id) {

        Optional<TrainingPlanEntity> trainingPlanEntity = trainingPlanRepository.findById(id);

        if (trainingPlanService.checkIfIsNotNull(id)) {
            return trainingPlanEntity.get().getStages().stream()
                    .map(stage -> BikeStage.fromBikeStageEntity((BikeStageEntity) stage)).toList();
        } else {
            throw new ResourceNotFoundException("TrainingPlan", "id", String.valueOf(id));
        }
    }

    @Override
    public BikeStage addNewStageToTrainingPlan(Long id, @Valid BikeStage bikeStage) {

        if (trainingPlanService.checkIfIsNotNull(id)) {
            Optional<TrainingPlanEntity> trainingPlan = trainingPlanRepository.findById(id);

            BikeStageEntity bikeStageEntity = bikeStage.mapToStageEntity();
            trainingPlan.get().getStages().add(bikeStageEntity);

            trainingPlanRepository.save(trainingPlan.get());
            return bikeStage;
        } else {
            throw new ResourceNotFoundException("TrainingPlan", "id", String.valueOf(id));
        }
    }
}