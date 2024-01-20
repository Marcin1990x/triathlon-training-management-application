package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.swim;

import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.Stage;

public class SwimStage extends Stage {

    private int paceInSeconds;

    public SwimStageEntity mapToSwimStageEntity() {

        SwimStageEntity swimStageEntity = new SwimStageEntity();

        swimStageEntity.setId(this.getId());
        swimStageEntity.setDistanceInMeters(this.getDistanceInMeters());
        swimStageEntity.setTimeInSeconds(this.getTimeInSeconds());
        swimStageEntity.setHeartRate(this.getHeartRate());
        swimStageEntity.setSequence(this.getSequence());
        swimStageEntity.setDescription(this.getDescription());
        swimStageEntity.setPaceInSeconds(this.getPaceInSeconds());

        return swimStageEntity;
    }

    public static SwimStage fromSwimStageEntity(SwimStageEntity swimStageEntity) {

        SwimStage swimStage = new SwimStage();

        swimStage.setId(swimStageEntity.getId());
        swimStage.setDistanceInMeters(swimStageEntity.getDistanceInMeters());
        swimStage.setTimeInSeconds(swimStageEntity.getTimeInSeconds());
        swimStage.setHeartRate(swimStageEntity.getHeartRate());
        swimStage.setSequence(swimStageEntity.getSequence());
        swimStage.setDescription(swimStageEntity.getDescription());
        swimStage.setPaceInSeconds(swimStageEntity.getPaceInSeconds());

        return swimStage;
    }

    public int getPaceInSeconds() {
        return paceInSeconds;
    }

    public void setPaceInSeconds(int paceInSeconds) {
        this.paceInSeconds = paceInSeconds;
    }
}
