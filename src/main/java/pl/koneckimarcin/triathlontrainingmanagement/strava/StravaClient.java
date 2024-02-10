package pl.koneckimarcin.triathlontrainingmanagement.strava;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StravaClient {

    private RestTemplate rest = new RestTemplate();
    private final String STRAVA_URL = "https://www.strava.com/api/v3/";

    public ActivityClientDto getActivityByIdFromClient(Long activityId) {

        return callGetActivityById(activityId);
    }

    private ActivityClientDto callGetActivityById(Long activityId) {

        String accessToken = StravaPropertyReader.getValue("strava_access_token");

        return rest.getForObject(STRAVA_URL + "activities/{activityId}/?access_token={accessToken}",
                ActivityClientDto.class, activityId, accessToken);
    }
}
