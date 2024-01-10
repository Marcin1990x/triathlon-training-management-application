package pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("training-plans")
public class TrainingPlanController {

    @Autowired
    private TrainingPlanService trainingPlanService;

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {

        trainingPlanService.deleteById(id);
    }
}
