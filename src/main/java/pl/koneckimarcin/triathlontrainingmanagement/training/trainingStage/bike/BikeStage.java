package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.bike;

import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.Stage;

public class BikeStage extends Stage {

    private int power;

    public BikeStage() {
    }

    public BikeStage(int power) {
        this.power = power;
    }

    public BikeStage(long distanceInMeters, long timeInSeconds, int sequence, int heartRate,
                     String description, int power, int repeat) {
        super(distanceInMeters, timeInSeconds, sequence, heartRate, description, repeat);
        this.power = power;
    }

    public BikeStageEntity mapToBikeStageEntity() {

        BikeStageEntity bikeStageEntity = new BikeStageEntity();

        bikeStageEntity.setId(this.getId());
        bikeStageEntity.setDistanceInMeters(this.getDistanceInMeters());
        bikeStageEntity.setTimeInSeconds(this.getTimeInSeconds());
        bikeStageEntity.setSequence(this.getSequence());
        bikeStageEntity.setHeartRate(this.getHeartRate());
        bikeStageEntity.setDescription(this.getDescription());
        bikeStageEntity.setPower(this.getPower());
        bikeStageEntity.setRepeat(this.getRepeat());

        return bikeStageEntity;
    }

    public static BikeStage fromBikeStageEntity(BikeStageEntity bikeStageEntity) {

        BikeStage bikeStage = new BikeStage();

        bikeStage.setId(bikeStageEntity.getId());
        bikeStage.setDistanceInMeters(bikeStageEntity.getDistanceInMeters());
        bikeStage.setTimeInSeconds(bikeStageEntity.getTimeInSeconds());
        bikeStage.setSequence(bikeStage.getSequence());
        bikeStage.setHeartRate(bikeStageEntity.getHeartRate());
        bikeStage.setDescription(bikeStageEntity.getDescription());
        bikeStage.setPower(bikeStageEntity.getPower());
        bikeStage.setRepeat(bikeStageEntity.getRepeat());

        return bikeStage;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return super.toString() + "BikeStage{" +
                "power=" + power +
                '}';
    }
}
