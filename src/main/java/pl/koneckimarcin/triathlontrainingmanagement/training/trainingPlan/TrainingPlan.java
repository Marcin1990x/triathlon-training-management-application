package pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan;

import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.StageEntity;

import java.util.List;

public class TrainingPlan {

    private Long id;

    private String name;

    private TrainingType trainingType;

    private String description;

    private List<StageEntity> stage;

    public TrainingPlanEntity mapToTrainingPlanEntity() {

        TrainingPlanEntity trainingPlanEntity = new TrainingPlanEntity();

        trainingPlanEntity.setId(this.id);
        trainingPlanEntity.setName(this.name);
        trainingPlanEntity.setTrainingType(this.trainingType);
        trainingPlanEntity.setDescription(this.getDescription());
        trainingPlanEntity.setStage(this.getStage()); // todo: not entity

        return trainingPlanEntity;
    }
    public static TrainingPlan fromTrainingPlanEntity(TrainingPlanEntity trainingPlanEntity) {

        TrainingPlan trainingPlan = new TrainingPlan();

        trainingPlan.setId(trainingPlanEntity.getId());
        trainingPlan.setName(trainingPlanEntity.getName());
        trainingPlan.setTrainingType(trainingPlanEntity.getTrainingType());
        trainingPlan.setDescription(trainingPlanEntity.getDescription());
        trainingPlan.setStage(trainingPlanEntity.getStage()); // todo: not entity

        return trainingPlan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TrainingType getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(TrainingType trainingType) {
        this.trainingType = trainingType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<StageEntity> getStage() {
        return stage;
    }

    public void setStage(List<StageEntity> stage) {
        this.stage = stage;
    }
}
