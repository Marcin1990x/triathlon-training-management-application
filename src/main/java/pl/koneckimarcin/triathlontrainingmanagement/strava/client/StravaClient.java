package pl.koneckimarcin.triathlontrainingmanagement.strava.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import pl.koneckimarcin.triathlontrainingmanagement.exception.RefreshTokenException;
import pl.koneckimarcin.triathlontrainingmanagement.strava.dto.ActivityClientDto;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class StravaClient {

    @Autowired
    private StravaClientService stravaClientService;

    private RestTemplate rest = new RestTemplate();
    private final String STRAVA_URL = "https://www.strava.com/api/v3/";
    private final String STRAVA_URL_REFRESH = "https://www.strava.com/oauth/token";

    public ActivityClientDto[] getAllActivities(String accessToken) {

        return callGetAllActivities(accessToken);
    }

    private ActivityClientDto[] callGetAllActivities(String accessToken) {

        //activities after
        ZonedDateTime after = ZonedDateTime.of(
                2024, 2, 1, 0, 0, 0, 0,
                ZoneId.of("Europe/Warsaw"));

        //String accessToken = StravaPropertyReader.getValue("strava_access_token");

        return rest.getForObject(STRAVA_URL +
                        "athlete/activities?access_token={accessToken}&after={after}&per_page=100",
                ActivityClientDto[].class, accessToken, after.toEpochSecond());
    }

    public String refreshAccessToken(String refreshToken) {

        HttpEntity<MultiValueMap<String, String>> requestBody = stravaClientService.createRequestBody(refreshToken);

        ResponseEntity<String> response = rest.exchange(
                STRAVA_URL_REFRESH,
                HttpMethod.POST,
                requestBody,
                String.class
        );
        if (response.getStatusCode() == HttpStatus.OK) {
            return stravaClientService.retrieveTokenFromResponse(response.getBody());
        } else {
            throw new RefreshTokenException(response.getStatusCode(), "Test");
        }
    }
}
