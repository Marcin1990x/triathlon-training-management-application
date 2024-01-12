package pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

public interface TrainingPlanOperations {

    @GetMapping("athletes/{id}/training-plans")
    public List<TrainingPlan> getTrainingPlansByAthleteId(@PathVariable Long id);


    @DeleteMapping("training-plans/{id}")
    public void deleteById(@PathVariable Long id);

    @PostMapping("coaches/{id}/training-plans")
    public TrainingPlan addNewTrainingPlan(@PathVariable Long id, @Valid @RequestBody TrainingPlan trainingPlan);

    @PostMapping("athletes/{athleteId}/training-plans/{trainingPlanId}")
    public TrainingPlan addTrainingPlanToAthleteWithDate(
            @PathVariable Long athleteId, @PathVariable Long trainingPlanId, @RequestParam Date plannedDate);
}
