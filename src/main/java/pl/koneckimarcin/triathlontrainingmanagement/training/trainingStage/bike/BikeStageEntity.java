package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.bike;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.StageEntity;

@Entity
@DiscriminatorValue("BIKE")
public class BikeStageEntity extends StageEntity {

    private int power;

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
