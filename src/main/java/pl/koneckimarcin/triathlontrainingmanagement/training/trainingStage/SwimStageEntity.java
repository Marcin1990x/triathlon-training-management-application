package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "swim_stage")
@DiscriminatorValue("SWIM")
public class SwimStageEntity extends StageEntity {
}
