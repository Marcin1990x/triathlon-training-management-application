package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage;

public class Stage {

    private long id;

    private long distanceInMeters;

    private long timeInSeconds;

    private int sequence;

    private int heartRate;

    private String description;

    private int repeat;

    public Stage() {
    }

    public Stage(long distanceInMeters, long timeInSeconds, int sequence,
                 int heartRate, String description, int repeat) {
        this.distanceInMeters = distanceInMeters;
        this.timeInSeconds = timeInSeconds;
        this.sequence = sequence;
        this.heartRate = heartRate;
        this.description = description;
        this.repeat = repeat;
    }

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

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    @Override
    public String toString() {
        return "Stage{" +
                "id=" + id +
                ", distanceInMeters=" + distanceInMeters +
                ", timeInSeconds=" + timeInSeconds +
                ", sequence=" + sequence +
                ", heartRate=" + heartRate +
                ", description='" + description + '\'' +
                ", repeat=" + repeat +
                '}';
    }
}
