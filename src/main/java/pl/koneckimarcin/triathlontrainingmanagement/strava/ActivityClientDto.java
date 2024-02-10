package pl.koneckimarcin.triathlontrainingmanagement.strava;

public class ActivityClientDto {

    private String name;
    private Double distance;
    private Integer moving_time;
    private String type;
    private String start_date;
    private String average_heartrate;
    private Integer calories;

    public String getName() {
        return name;
    }

    public Double getDistance() {
        return distance;
    }

    public Integer getMoving_time() {
        return moving_time;
    }

    public String getType() {
        return type;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getAverage_heartrate() {
        return average_heartrate;
    }

    public Integer getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return "ActivityClientDto{" +
                "name='" + name + '\'' +
                ", distance=" + distance +
                ", moving_time=" + moving_time +
                ", type='" + type + '\'' +
                ", start_date='" + start_date + '\'' +
                ", average_heartrate='" + average_heartrate + '\'' +
                ", calories=" + calories +
                '}';
    }
}
