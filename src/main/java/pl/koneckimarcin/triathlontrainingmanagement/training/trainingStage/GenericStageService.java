package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage;

import org.springframework.beans.factory.annotation.Autowired;
import pl.koneckimarcin.triathlontrainingmanagement.exception.ResourceNotFoundException;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanEntity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class GenericStageService<T> {

    @Autowired
    private TrainingPlanRepository trainingPlanRepository;

    public List<T> getStagesForTrainingPlanById(Long id) {

        Optional<TrainingPlanEntity> trainingPlanEntity = trainingPlanRepository.findById(id);
        if (trainingPlanEntity.isPresent()) {

            return trainingPlanEntity.get().getStages()
                    .stream().map(stage -> (T) stage).collect(Collectors.toList());
        } else {
            throw new ResourceNotFoundException("TrainingPlan", "id", String.valueOf(id));
        }
    }
}
