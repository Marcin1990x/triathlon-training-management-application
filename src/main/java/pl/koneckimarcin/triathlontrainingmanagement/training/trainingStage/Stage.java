package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Stage {

    @JsonIgnore
    private long id;

    private long distanceInMeters;

    private long timeInSeconds;

    private int sequence;

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

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
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

    @Override
    public String toString() {
        return "Stage{" +
                "id=" + id +
                ", distanceInMeters=" + distanceInMeters +
                ", timeInSeconds=" + timeInSeconds +
                ", heartRate=" + heartRate +
                ", description='" + description + '\'' +
                '}';
    }
}
