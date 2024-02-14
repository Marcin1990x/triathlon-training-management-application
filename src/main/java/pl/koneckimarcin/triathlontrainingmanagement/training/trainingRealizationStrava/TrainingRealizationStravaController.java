package pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealizationStrava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrainingRealizationStravaController implements TrainingRealizationStravaOperations {

    @Autowired
    private TrainingRealizationStravaService stravaService;

    public List<TrainingRealizationStrava> getTrainingRealizationsByAthleteId(Long id) {

        return stravaService.getTrainingRealizationsByAthleteId(id);
    }

    public void deleteById(Long id) {

        stravaService.deleteById(id);
    }
}
