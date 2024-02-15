package pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.dto;

import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.constant.TrainingType;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.Feelings;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.TrainingRealizationEntity;

import java.sql.Date;

public class TrainingRealization {

    private Long id;

    private Long stravaId;

    private Long stravaAthleteId;

    private String name;

    private Double distanceInMeters;

    private Integer timeInSeconds;

    private TrainingType type;

    private Date realizationDate;

    private Long averageWatts;

    private Long maxWatts;

    private Long averageHeartrate;

    private Long maxHeartrate;

    private String realizationDescription;

    private Feelings feelings;

    private int rpeLevel;

    public TrainingRealizationEntity mapToTrainingRealizationEntity() {

        TrainingRealizationEntity trainingRealization = new TrainingRealizationEntity();

        trainingRealization.setId(this.id);
        trainingRealization.setName(this.name);
        trainingRealization.setDistanceInMeters(this.distanceInMeters);
        trainingRealization.setTimeInSeconds(this.timeInSeconds);
        trainingRealization.setType(this.type);
        trainingRealization.setRealizationDate(this.realizationDate);
        trainingRealization.setAverageWatts(this.averageWatts);
        trainingRealization.setMaxWatts(this.maxWatts);
        trainingRealization.setAverageHeartrate(this.averageHeartrate);
        trainingRealization.setMaxHeartrate(this.maxHeartrate);
        trainingRealization.setRealizationDescription(this.realizationDescription);
        trainingRealization.setFeelings(this.feelings);
        trainingRealization.setRpeLevel(this.rpeLevel);

        return trainingRealization;
    }

    public static TrainingRealization fromTrainingRealizationEntity
            (TrainingRealizationEntity stravaEntity) {

        TrainingRealization trainingRealization = new TrainingRealization();

        trainingRealization.setId(stravaEntity.getId());
        trainingRealization.setName(stravaEntity.getName());
        trainingRealization.setDistanceInMeters(stravaEntity.getDistanceInMeters());
        trainingRealization.setTimeInSeconds(stravaEntity.getTimeInSeconds());
        trainingRealization.setType(stravaEntity.getType());
        trainingRealization.setRealizationDate(stravaEntity.getRealizationDate());
        trainingRealization.setAverageWatts(stravaEntity.getAverageWatts());
        trainingRealization.setMaxWatts(stravaEntity.getMaxWatts());
        trainingRealization.setAverageHeartrate(stravaEntity.getAverageHeartrate());
        trainingRealization.setMaxHeartrate(stravaEntity.getMaxHeartrate());
        trainingRealization.setRealizationDescription(stravaEntity.getRealizationDescription());
        trainingRealization.setFeelings(stravaEntity.getFeelings());
        trainingRealization.setRpeLevel(stravaEntity.getRpeLevel());

        return trainingRealization;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStravaId() {
        return stravaId;
    }

    public void setStravaId(Long stravaId) {
        this.stravaId = stravaId;
    }

    public Long getStravaAthleteId() {
        return stravaAthleteId;
    }

    public void setStravaAthleteId(Long stravaAthleteId) {
        this.stravaAthleteId = stravaAthleteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDistanceInMeters() {
        return distanceInMeters;
    }

    public void setDistanceInMeters(Double distanceInMeters) {
        this.distanceInMeters = distanceInMeters;
    }

    public Integer getTimeInSeconds() {
        return timeInSeconds;
    }

    public void setTimeInSeconds(Integer timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }

    public TrainingType getType() {
        return type;
    }

    public void setType(TrainingType type) {
        this.type = type;
    }

    public Date getRealizationDate() {
        return realizationDate;
    }

    public void setRealizationDate(Date realizationDate) {
        this.realizationDate = realizationDate;
    }

    public Long getAverageWatts() {
        return averageWatts;
    }

    public void setAverageWatts(Long averageWatts) {
        this.averageWatts = averageWatts;
    }

    public Long getMaxWatts() {
        return maxWatts;
    }

    public void setMaxWatts(Long maxWatts) {
        this.maxWatts = maxWatts;
    }

    public Long getAverageHeartrate() {
        return averageHeartrate;
    }

    public void setAverageHeartrate(Long averageHeartrate) {
        this.averageHeartrate = averageHeartrate;
    }

    public Long getMaxHeartrate() {
        return maxHeartrate;
    }

    public void setMaxHeartrate(Long maxHeartrate) {
        this.maxHeartrate = maxHeartrate;
    }

    public String getRealizationDescription() {
        return realizationDescription;
    }

    public void setRealizationDescription(String realizationDescription) {
        this.realizationDescription = realizationDescription;
    }

    public Feelings getFeelings() {
        return feelings;
    }

    public void setFeelings(Feelings feelings) {
        this.feelings = feelings;
    }

    public int getRpeLevel() {
        return rpeLevel;
    }

    public void setRpeLevel(int rpeLevel) {
        this.rpeLevel = rpeLevel;
    }
}
