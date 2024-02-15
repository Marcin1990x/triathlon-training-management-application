package pl.koneckimarcin.triathlontrainingmanagement.strava.dto;

import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.constant.TrainingType;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.TrainingRealizationEntity;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ActivityClientDto {

    private Long id;
    private AthleteClientDto athlete;
    private String name;
    private Double distance;
    private Integer moving_time;
    private String type;
    private String start_date;
    private Long average_watts;
    private Long max_watts;
    private Long average_heartrate;
    private Long max_heartrate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AthleteClientDto getAthlete() {
        return athlete;
    }

    public void setAthlete(AthleteClientDto athlete) {
        this.athlete = athlete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getMoving_time() {
        return moving_time;
    }

    public void setMoving_time(Integer moving_time) {
        this.moving_time = moving_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public Long getAverage_watts() {
        return average_watts;
    }

    public void setAverage_watts(Long average_watts) {
        this.average_watts = average_watts;
    }

    public Long getMax_watts() {
        return max_watts;
    }

    public void setMax_watts(Long max_watts) {
        this.max_watts = max_watts;
    }

    public Long getAverage_heartrate() {
        return average_heartrate;
    }

    public void setAverage_heartrate(Long average_heartrate) {
        this.average_heartrate = average_heartrate;
    }

    public Long getMax_heartrate() {
        return max_heartrate;
    }

    public void setMax_heartrate(Long max_heartrate) {
        this.max_heartrate = max_heartrate;
    }

    @Override
    public String toString() {
        return "ActivityClientDto{" +
                "id=" + id +
                ", athlete=" + athlete +
                ", name='" + name + '\'' +
                ", distance=" + distance +
                ", moving_time=" + moving_time +
                ", type='" + type + '\'' +
                ", start_date='" + start_date + '\'' +
                ", average_watts=" + average_watts +
                ", max_watts=" + max_watts +
                ", average_heartrate=" + average_heartrate +
                ", max_heartrate=" + max_heartrate +
                '}';
    }

    public TrainingRealizationEntity mapToRealizationEntity() {

        TrainingRealizationEntity trainingRealization = new TrainingRealizationEntity();

        trainingRealization.setStravaId(this.getId());
        trainingRealization.setStravaAthleteId(this.getAthlete().getId());
        trainingRealization.setName(this.getName());
        trainingRealization.setDistanceInMeters(this.getDistance());
        trainingRealization.setTimeInSeconds(this.getMoving_time());
        trainingRealization.setType(setTypeFromStrava(this.type));
        trainingRealization.setRealizationDate(setDateFromStrava(this.start_date));
        trainingRealization.setAverageWatts(this.getAverage_watts());
        trainingRealization.setMaxWatts(this.getMax_watts());
        trainingRealization.setAverageHeartrate(this.getAverage_heartrate());
        trainingRealization.setMaxHeartrate(this.getMax_heartrate());

        return trainingRealization;
    }

    private Date setDateFromStrava(String dateToConvert) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date sqlDate = null;
        try {
            java.util.Date date = dateFormat.parse(dateToConvert);
            sqlDate = new Date(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sqlDate;
    }

    private TrainingType setTypeFromStrava(String stravaType) {

        switch (stravaType) {
            case "Ride" -> {
                return TrainingType.BIKE;
            }
            case "Run" -> {
                return TrainingType.RUN;
            }
            case "WeightTraining" -> {
                return TrainingType.WEIGHT;
            }
            case "Swim" -> {
                return TrainingType.SWIM;
            }
        }
        return TrainingType.UNKNOWN; // todo: throw exception instead of unknown
    }
}
