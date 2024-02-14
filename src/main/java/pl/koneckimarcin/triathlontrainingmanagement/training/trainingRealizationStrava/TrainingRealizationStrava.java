package pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealizationStrava;

import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.constant.TrainingType;

import java.sql.Date;

public class TrainingRealizationStrava {

    private Long id;

    private Long stravaId;

    private Long stravaAthleteId;

    private Long athleteId;

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

    public TrainingRealizationStravaEntity mapToTrainingRealizationStravaEntity() {

        TrainingRealizationStravaEntity stravaEntity = new TrainingRealizationStravaEntity();

        stravaEntity.setId(this.id);
        stravaEntity.setAthleteId(this.athleteId);
        stravaEntity.setName(this.name);
        stravaEntity.setDistanceInMeters(this.distanceInMeters);
        stravaEntity.setTimeInSeconds(this.timeInSeconds);
        stravaEntity.setType(this.type);
        stravaEntity.setRealizationDate(this.realizationDate);
        stravaEntity.setAverageWatts(this.averageWatts);
        stravaEntity.setMaxWatts(this.maxWatts);
        stravaEntity.setAverageHeartrate(this.averageHeartrate);
        stravaEntity.setMaxHeartrate(this.maxHeartrate);
        stravaEntity.setRealizationDescription(this.realizationDescription);
        stravaEntity.setFeelings(this.feelings);
        stravaEntity.setRpeLevel(this.rpeLevel);

        return stravaEntity;
    }

    public static TrainingRealizationStrava fromTrainingRealizationStravaEntity
            (TrainingRealizationStravaEntity stravaEntity) {

        TrainingRealizationStrava realizationStrava = new TrainingRealizationStrava();

        realizationStrava.setId(stravaEntity.getId());
        realizationStrava.setAthleteId(stravaEntity.getAthleteId());
        realizationStrava.setName(stravaEntity.getName());
        realizationStrava.setDistanceInMeters(stravaEntity.getDistanceInMeters());
        realizationStrava.setTimeInSeconds(stravaEntity.getTimeInSeconds());
        realizationStrava.setType(stravaEntity.getType());
        realizationStrava.setRealizationDate(stravaEntity.getRealizationDate());
        realizationStrava.setAverageWatts(stravaEntity.getAverageWatts());
        realizationStrava.setMaxWatts(stravaEntity.getMaxWatts());
        realizationStrava.setAverageHeartrate(stravaEntity.getAverageHeartrate());
        realizationStrava.setMaxHeartrate(stravaEntity.getMaxHeartrate());
        realizationStrava.setRealizationDescription(stravaEntity.getRealizationDescription());
        realizationStrava.setFeelings(stravaEntity.getFeelings());
        realizationStrava.setRpeLevel(stravaEntity.getRpeLevel());

        return realizationStrava;
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

    public Long getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(Long athleteId) {
        this.athleteId = athleteId;
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
