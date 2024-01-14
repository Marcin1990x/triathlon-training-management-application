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
}
