package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.weight;

import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.Stage;

public class WeightStage extends Stage {

    public WeightStage() {
    }

    public WeightStage(long distanceInMeters, long timeInSeconds, int sequence, int heartRate,
                       String description, int repeat) {
        super(distanceInMeters, timeInSeconds, sequence, heartRate, description, repeat);
    }

    public WeightStageEntity mapToWeightStageEntity() {

        WeightStageEntity weightStageEntity = new WeightStageEntity();

        weightStageEntity.setId(this.getId());
        weightStageEntity.setDistanceInMeters(this.getDistanceInMeters());
        weightStageEntity.setTimeInSeconds(this.getTimeInSeconds());
        weightStageEntity.setSequence(this.getSequence());
        weightStageEntity.setHeartRate(this.getHeartRate());
        weightStageEntity.setDescription(this.getDescription());
        weightStageEntity.setRepeat(this.getRepeat());

        return weightStageEntity;
    }

    public static WeightStage fromWeightStageEntity(WeightStageEntity weightStageEntity) {

        WeightStage weightStage = new WeightStage();

        weightStage.setId(weightStageEntity.getId());
        weightStage.setDistanceInMeters(weightStageEntity.getDistanceInMeters());
        weightStage.setTimeInSeconds(weightStageEntity.getTimeInSeconds());
        weightStage.setSequence(weightStageEntity.getSequence());
        weightStage.setHeartRate(weightStageEntity.getHeartRate());
        weightStage.setDescription(weightStageEntity.getDescription());
        weightStage.setRepeat(weightStageEntity.getRepeat());

        return weightStage;
    }

    @Override
    public String toString() {
        return super.toString() + "WeightStage{}";
    }
}
