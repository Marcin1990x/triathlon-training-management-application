package pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan;

import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Set;

public interface TrainingPlanOperations {

    @GetMapping("athletes/{id}/training-plans")
    public List<TrainingPlan> getTrainingPlansByAthleteId(@PathVariable Long id);

    @GetMapping("coaches/{id}/training-plans")
    public Set<TrainingPlan> getTrainingPlansByCoachId(@PathVariable Long id);

    @DeleteMapping("training-plans/{id}")
    public void deleteById(@PathVariable Long id);

    @PostMapping("coaches/{id}/training-plans")
    public TrainingPlan addNewTrainingPlan(@PathVariable Long id, @RequestBody TrainingPlan trainingPlan);

    @PostMapping("athletes/{athleteId}/training-plans/{trainingPlanId}")
    public TrainingPlan addTrainingPlanToAthleteWithDate(
            @PathVariable Long athleteId, @PathVariable Long trainingPlanId, @RequestParam Date plannedDate);
}
