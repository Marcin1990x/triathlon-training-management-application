package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.bike;

import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.Stage;

public class BikeStage extends Stage {

    private int power;

    public BikeStageEntity mapToStageEntity() {

        BikeStageEntity bikeStageEntity = new BikeStageEntity();

        bikeStageEntity.setId(this.getId());
        bikeStageEntity.setSequence(this.getSequence());
        bikeStageEntity.setDistanceInMeters(this.getDistanceInMeters());
        bikeStageEntity.setTimeInSeconds(this.getTimeInSeconds());
        bikeStageEntity.setHeartRate(this.getHeartRate());
        bikeStageEntity.setDescription(this.getDescription());
        bikeStageEntity.setPower(this.getPower());

        return bikeStageEntity;
    }

    public static BikeStage fromBikeStageEntity(BikeStageEntity bikeStageEntity) {

        BikeStage bikeStage = new BikeStage();

        bikeStage.setId(bikeStageEntity.getId());
        bikeStage.setSequence(bikeStageEntity.getSequence());
        bikeStage.setDistanceInMeters(bikeStageEntity.getDistanceInMeters());
        bikeStage.setTimeInSeconds(bikeStageEntity.getTimeInSeconds());
        bikeStage.setHeartRate(bikeStageEntity.getHeartRate());
        bikeStage.setDescription(bikeStageEntity.getDescription());
        bikeStage.setPower(bikeStageEntity.getPower());

        return bikeStage;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}