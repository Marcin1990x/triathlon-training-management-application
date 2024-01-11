package pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping()
public class TrainingPlanController {

    @Autowired
    private TrainingPlanService trainingPlanService;

    @DeleteMapping("training-plans/{id}")
    public void deleteById(@PathVariable Long id) {

        trainingPlanService.deleteById(id);
    }

    @PostMapping("coaches/{id}/training-plans")
    public TrainingPlan addNewTrainingPlan(@PathVariable Long id, @Valid @RequestBody TrainingPlan trainingPlan) {

        return trainingPlanService.addNewTrainingPlanToCoach(id, trainingPlan);
    }

    @PutMapping("athletes/{athleteId}/training-plans/{trainingPlanId}")
    public void addTrainingPlanToAthlete(
            @PathVariable Long athleteId, @PathVariable Long trainingPlanId, @RequestParam Date plannedDate) {

        trainingPlanService.addTrainingPlanToAthlete(athleteId, trainingPlanId, plannedDate);
    }
}
