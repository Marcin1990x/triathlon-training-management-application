package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "bike_stage")
@DiscriminatorValue("BIKE")
public class BikeStageEntity extends StageEntity {

    //bike stage specific fields
    private int power;

}
