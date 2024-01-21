package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.run;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
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
}
