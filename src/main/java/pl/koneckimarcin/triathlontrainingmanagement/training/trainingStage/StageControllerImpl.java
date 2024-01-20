package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StageControllerImpl implements StageController {

    @Autowired
    private StageServiceImpl service;

    @Override
    public List<Stage> getStagesForTrainingPlanById(Long id) {

        return service.getStagesForTrainingPlanById(id);
    }

    @Override
    public Stage addNewStageToTrainingPlan(Long id, Stage stage) {
        return null;
    }

}
