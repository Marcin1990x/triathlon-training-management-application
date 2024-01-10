package pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.triathlontrainingmanagement.exception.ResourceNotFoundException;

import java.util.Optional;

@Service
public class TrainingPlanService {

    @Autowired
    TrainingPlanRepository trainingPlanRepository;

    public boolean checkIfIsNotNull(Long id) {
        Optional<TrainingPlanEntity> trainingPlanEntity = trainingPlanRepository.findById(id);
        if (trainingPlanEntity.isPresent()) {
            return true;
        }
        return false;
    }

    public void deleteById(Long id) {

        if(checkIfIsNotNull(id)){
            trainingPlanRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("TrainingPlan", "id", String.valueOf(id));
        }
    }
}
