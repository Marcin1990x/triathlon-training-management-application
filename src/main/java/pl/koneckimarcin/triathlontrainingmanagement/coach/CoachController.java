package pl.koneckimarcin.triathlontrainingmanagement.coach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlan;

@RestController
@RequestMapping("/coaches")
public class CoachController {

    @Autowired
    private CoachService coachService;

    @GetMapping("{id}")
    public Coach getById(@PathVariable Long id) {
        return coachService.findById(id);
    }

    @PostMapping
    public Coach addNew(@RequestBody Coach coach) {

        return coachService.addNew(coach);
    }
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {

        coachService.deleteById(id);
    }
    @PutMapping("{id}/training-plans")
    public Coach addNewTrainingPlan(@PathVariable Long id, @RequestBody TrainingPlan trainingPlan) {

        return coachService.addNewTrainingPlan(id, trainingPlan);
    }
}
