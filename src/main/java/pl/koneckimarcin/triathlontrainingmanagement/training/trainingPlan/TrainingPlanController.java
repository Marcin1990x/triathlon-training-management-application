package pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@RestController
public class TrainingPlanController implements TrainingPlanOperations {

    @Autowired
    private TrainingPlanService trainingPlanService;

    @Override
    public List<TrainingPlan> getTrainingPlansByAthleteId(Long id) {

        return trainingPlanService.getTrainingPlansByAthleteId(id);
    }

    @Override
    public Set<TrainingPlan> getTrainingPlansByCoachId(Long id) {

        return trainingPlanService.getTrainingPlansByCoachId(id);
    }

    public void deleteById(Long id) {

        trainingPlanService.deleteById(id);
    }

    public TrainingPlan addNewTrainingPlan(Long id, TrainingPlan trainingPlan) {

        return trainingPlanService.addNewTrainingPlanToCoach(id, trainingPlan);
    }

    public TrainingPlan addTrainingPlanToAthleteWithDate(Long athleteId, Long trainingPlanId, Date plannedDate) {

        return trainingPlanService.addTrainingPlanToAthleteWithDate(athleteId, trainingPlanId, plannedDate);
    }
}
