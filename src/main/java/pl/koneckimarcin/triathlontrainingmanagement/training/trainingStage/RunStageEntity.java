package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "run_stage")
@DiscriminatorValue("RUN")
public class RunStageEntity extends StageEntity {

    //run stage specifid fields
    private int paceInSecondsPerKm;
}
