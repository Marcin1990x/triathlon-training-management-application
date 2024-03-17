package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.run;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.StageEntity;

@Entity
@Table(name = "run_stage")
@DiscriminatorValue("RUN")
public class RunStageEntity extends StageEntity {

    private int paceInSecondsPerKm;

    public int getPaceInSecondsPerKm() {
        return paceInSecondsPerKm;
    }

    public void setPaceInSecondsPerKm(int paceInSecondsPerKm) {
        this.paceInSecondsPerKm = paceInSecondsPerKm;
    }

    public static RunStageEntity copyStage(RunStageEntity original) {

        RunStageEntity copy = new RunStageEntity();
        copy.setDistanceInMeters(original.getDistanceInMeters());
        copy.setTimeInSeconds(original.getTimeInSeconds());
        copy.setSequence(original.getSequence());
        copy.setHeartRate(original.getHeartRate());
        copy.setDescription(original.getDescription());
        copy.setPaceInSecondsPerKm(original.getPaceInSecondsPerKm());
        copy.setRepeat(original.getRepeat());

        return copy;
    }
}
