package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.weight;

import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.Stage;

public class WeightStage extends Stage {

    public WeightStageEntity mapToWeightStageEntity() {

        WeightStageEntity weightStageEntity = new WeightStageEntity();

        weightStageEntity.setId(this.getId());
        weightStageEntity.setDistanceInMeters(this.getDistanceInMeters());
        weightStageEntity.setTimeInSeconds(this.getTimeInSeconds());
        weightStageEntity.setHeartRate(this.getHeartRate());
        weightStageEntity.setDescription(this.getDescription());

        return weightStageEntity;
    }

    public static WeightStage fromWeightStageEntity(WeightStageEntity weightStageEntity) {

        WeightStage weightStage = new WeightStage();

        weightStage.setId(weightStageEntity.getId());
        weightStage.setDistanceInMeters(weightStageEntity.getDistanceInMeters());
        weightStage.setTimeInSeconds(weightStageEntity.getTimeInSeconds());
        weightStage.setHeartRate(weightStageEntity.getHeartRate());
        weightStage.setDescription(weightStageEntity.getDescription());

        return weightStage;
    }

    @Override
    public String toString() {
        return super.toString() + "WeightStage{}";
    }
}
