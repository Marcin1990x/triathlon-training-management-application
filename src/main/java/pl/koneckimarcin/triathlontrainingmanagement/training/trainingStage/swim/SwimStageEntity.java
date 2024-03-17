package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.swim;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.StageEntity;

@Entity
@Table(name = "swim_stage")
@DiscriminatorValue("SWIM")
public class SwimStageEntity extends StageEntity {

    private int paceInSeconds;

    public int getPaceInSeconds() {
        return paceInSeconds;
    }

    public void setPaceInSeconds(int paceInSeconds) {
        this.paceInSeconds = paceInSeconds;
    }

    public static SwimStageEntity copyStage(SwimStageEntity original) {

        SwimStageEntity copy = new SwimStageEntity();
        copy.setDistanceInMeters(original.getDistanceInMeters());
        copy.setTimeInSeconds(original.getTimeInSeconds());
        copy.setSequence(original.getSequence());
        copy.setHeartRate(original.getHeartRate());
        copy.setDescription(original.getDescription());
        copy.setPaceInSeconds(original.getPaceInSeconds());
        copy.setRepeat(original.getRepeat());

        return copy;
    }
}
