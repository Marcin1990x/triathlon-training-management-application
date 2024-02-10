package pl.koneckimarcin.triathlontrainingmanagement.strava;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class StravaClient {

    private RestTemplate rest = new RestTemplate();
    private final String STRAVA_URL = "https://www.strava.com/api/v3/";

    public ActivityClientDto getActivityByIdFromClient(Long activityId) {

        return callGetActivityById(activityId);
    }

    public ActivityClientDto[] getAllActivities() {

        return callGetAllActivities();
    }

    private ActivityClientDto callGetActivityById(Long activityId) {

        String accessToken = StravaPropertyReader.getValue("strava_access_token");

        return rest.getForObject(STRAVA_URL + "activities/{activityId}/?access_token={accessToken}",
                ActivityClientDto.class, activityId, accessToken);
    }

    private ActivityClientDto[] callGetAllActivities() {

        //activities after
        ZonedDateTime after = ZonedDateTime.of(
                2024, 2, 1, 0, 0, 0, 0,
                ZoneId.of("Europe/Warsaw"));

        String accessToken = StravaPropertyReader.getValue("strava_access_token");

        return rest.getForObject(STRAVA_URL + "athlete/activities?access_token={accessToken}&after={after}",
                ActivityClientDto[].class, accessToken, after.toEpochSecond());
    }
}
