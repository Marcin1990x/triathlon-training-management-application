package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.weight;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.StageEntity;

@Entity
@Table(name = "weight_stage")
@DiscriminatorValue("WEIGHT")
public class WeightStageEntity extends StageEntity {


}
