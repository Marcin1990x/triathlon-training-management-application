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

    public static BikeStageEntity copyStage(BikeStageEntity original) {

        BikeStageEntity copy = new BikeStageEntity();
        copy.setDistanceInMeters(original.getDistanceInMeters());
        copy.setTimeInSeconds(original.getTimeInSeconds());
        copy.setSequence(original.getSequence());
        copy.setHeartRate(original.getHeartRate());
        copy.setDescription(original.getDescription());
        copy.setPower(original.getPower());
        copy.setRepeat(original.getRepeat());

        return copy;
    }
}
