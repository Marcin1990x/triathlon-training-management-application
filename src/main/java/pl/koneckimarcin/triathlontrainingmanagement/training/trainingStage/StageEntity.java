package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "stage")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "stage_type", discriminatorType = DiscriminatorType.STRING)
public abstract class StageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long distanceInMeters;

    private long timeInSeconds;

    private int heartRate;

    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDistanceInMeters() {
        return distanceInMeters;
    }

    public void setDistanceInMeters(long distanceInMeters) {
        this.distanceInMeters = distanceInMeters;
    }

    public long getTimeInSeconds() {
        return timeInSeconds;
    }

    public void setTimeInSeconds(long timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
