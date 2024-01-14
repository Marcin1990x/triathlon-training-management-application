package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class GenericStageController<T> implements StageOperations {

    @Autowired
    private GenericStageService<T> service;

    @Override
    public List<T> getStagesForTrainingPlanById(Long id) {

        return service.getStagesForTrainingPlanById(id);
    }
}
