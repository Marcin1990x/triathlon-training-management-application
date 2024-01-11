package pl.koneckimarcin.triathlontrainingmanagement.coach;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanService;

@RestController
@RequestMapping("/coaches")
public class CoachController {

    @Autowired
    private CoachService coachService;

    @Autowired
    private TrainingPlanService trainingPlanService;

    @GetMapping("{id}")
    public Coach getById(@PathVariable Long id) {
        return coachService.findById(id);
    }

    @PostMapping
    public Coach addNew(@Valid @RequestBody Coach coach) {

        return coachService.addNew(coach);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {

        coachService.deleteById(id);
    }
    @PutMapping("/{coachId}/athletes/{athleteId}")
    public Coach addAthleteToCoach(@PathVariable Long coachId, @PathVariable Long athleteId) {

        return coachService.addAthleteToCoach(coachId, athleteId);
    }
}
