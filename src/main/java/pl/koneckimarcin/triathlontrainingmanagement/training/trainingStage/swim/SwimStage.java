package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.swim;

import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.Stage;

public class SwimStage extends Stage {

    public SwimStage() {
    }

    public SwimStage(int paceInSeconds) {
        this.paceInSeconds = paceInSeconds;
    }
    public SwimStage(long distanceInMeters, long timeInSeconds, int sequence, int heartRate,
                     String description, int paceInSeconds, int repeat) {
        super(distanceInMeters, timeInSeconds, sequence, heartRate, description, repeat);
        this.paceInSeconds = paceInSeconds;
    }

    private int paceInSeconds;

    public SwimStageEntity mapToSwimStageEntity() {

        SwimStageEntity swimStageEntity = new SwimStageEntity();

        swimStageEntity.setId(this.getId());
        swimStageEntity.setDistanceInMeters(this.getDistanceInMeters());
        swimStageEntity.setTimeInSeconds(this.getTimeInSeconds());
        swimStageEntity.setSequence(this.getSequence());
        swimStageEntity.setHeartRate(this.getHeartRate());
        swimStageEntity.setDescription(this.getDescription());
        swimStageEntity.setPaceInSeconds(this.getPaceInSeconds());
        swimStageEntity.setRepeat(this.getRepeat());

        return swimStageEntity;
    }

    public static SwimStage fromSwimStageEntity(SwimStageEntity swimStageEntity) {

        SwimStage swimStage = new SwimStage();

        swimStage.setId(swimStageEntity.getId());
        swimStage.setDistanceInMeters(swimStageEntity.getDistanceInMeters());
        swimStage.setTimeInSeconds(swimStageEntity.getTimeInSeconds());
        swimStage.setSequence(swimStageEntity.getSequence());
        swimStage.setHeartRate(swimStageEntity.getHeartRate());
        swimStage.setDescription(swimStageEntity.getDescription());
        swimStage.setPaceInSeconds(swimStageEntity.getPaceInSeconds());
        swimStage.setRepeat(swimStageEntity.getRepeat());

        return swimStage;
    }

    public int getPaceInSeconds() {
        return paceInSeconds;
    }

    public void setPaceInSeconds(int paceInSeconds) {
        this.paceInSeconds = paceInSeconds;
    }

    @Override
    public String toString() {
        return super.toString() +  "SwimStage{" +
                "paceInSeconds=" + paceInSeconds +
                '}';
    }
}
