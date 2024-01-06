package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "weight_stage")
@DiscriminatorValue("WEIGHT")
public class WeightStageEntity extends StageEntity{

    //weight stage specific fields

}
