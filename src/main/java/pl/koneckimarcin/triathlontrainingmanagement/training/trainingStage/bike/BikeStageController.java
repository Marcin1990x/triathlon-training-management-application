package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.bike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.StageController;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeStageController implements StageController<BikeStage> {

    @Autowired
    private BikeStageService service;

    @Override
    public List<BikeStage> getStagesForTrainingPlanById(Long id) {

        return service.getStagesForTrainingPlanById(id);
    }
    @Override
    public BikeStage addNewStageToTrainingPlan(Long id, BikeStage bikeStage) {

        return service.addNewStageToTrainingPlan(id, bikeStage);
    }
}
