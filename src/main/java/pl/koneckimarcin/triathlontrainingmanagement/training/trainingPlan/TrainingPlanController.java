package pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class TrainingPlanController implements TrainingPlanOperations{

    @Autowired
    private TrainingPlanService trainingPlanService;

    @Override
    public List<TrainingPlan> getTrainingPlansByAthleteId(Long id) {

        return trainingPlanService.getTrainingPlansByAthleteId(id);
    }

    public void deleteById(@PathVariable Long id) {

        trainingPlanService.deleteById(id);
    }

    public TrainingPlan addNewTrainingPlan(@PathVariable Long id, @Valid @RequestBody TrainingPlan trainingPlan) {

        return trainingPlanService.addNewTrainingPlanToCoach(id, trainingPlan);
    }

    public TrainingPlan addTrainingPlanToAthleteWithDate(
            @PathVariable Long athleteId, @PathVariable Long trainingPlanId, @RequestParam Date plannedDate) {

        return trainingPlanService.addTrainingPlanToAthleteWithDate(athleteId, trainingPlanId, plannedDate);
    }
}
