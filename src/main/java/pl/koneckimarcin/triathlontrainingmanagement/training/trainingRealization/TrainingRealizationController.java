package pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrainingRealizationController implements TrainingRealizationOperations {

    @Autowired
    private TrainingRealizationService trainingRealizationService;

    @Override
    public List<TrainingRealization> getTrainingRealizationsByAthleteId(Long id) {

        return trainingRealizationService.getTrainingRealizationsByAthleteId(id);
    }

    @Override
    public void deleteById(Long id) {

        trainingRealizationService.deleteById(id);

    }

    @Override
    public TrainingRealization addNewTrainingRealizationToAthlete(Long id, TrainingRealization trainingRealization) {

        return trainingRealizationService.addNewTrainingRealizationToAthlete(id, trainingRealization);
    }
}
