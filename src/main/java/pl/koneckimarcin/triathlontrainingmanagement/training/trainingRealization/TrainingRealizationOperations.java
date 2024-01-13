package pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface TrainingRealizationOperations {

    @GetMapping("athletes/{id}/training-realizations")
    public List<TrainingRealization> getTrainingRealizationsByAthleteId(@PathVariable Long id);

    @DeleteMapping("training-realizations/{id}")
    public void deleteById(@PathVariable Long id);

    @PostMapping("athletes/{id}/training-realizations")
    public TrainingRealization addNewTrainingRealizationToAthlete
            (@PathVariable Long id, @Valid @RequestBody TrainingRealization trainingRealization);
}
