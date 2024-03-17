package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.weight;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.StageEntity;

@Entity
@Table(name = "weight_stage")
@DiscriminatorValue("WEIGHT")
public class WeightStageEntity extends StageEntity {

    public static WeightStageEntity copyStage(WeightStageEntity original) {

        WeightStageEntity copy = new WeightStageEntity();
        copy.setDistanceInMeters(original.getDistanceInMeters());
        copy.setTimeInSeconds(original.getTimeInSeconds());
        copy.setSequence(original.getSequence());
        copy.setHeartRate(original.getHeartRate());
        copy.setDescription(original.getDescription());
        copy.setRepeat(original.getRepeat());

        return copy;
    }


}
