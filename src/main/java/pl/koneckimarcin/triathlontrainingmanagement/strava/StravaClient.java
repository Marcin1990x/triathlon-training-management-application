package pl.koneckimarcin.triathlontrainingmanagement.strava;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import pl.koneckimarcin.triathlontrainingmanagement.strava.dto.ActivityClientDto;
import pl.koneckimarcin.triathlontrainingmanagement.strava.dto.RefreshTokenResponseDto;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class StravaClient {

    private RestTemplate rest = new RestTemplate();
    private final String STRAVA_URL = "https://www.strava.com/api/v3/";
    private final String STRAVA_URL_REFRESH = "https://www.strava.com/oauth/token";

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

        return rest.getForObject(STRAVA_URL +
                        "athlete/activities?access_token={accessToken}&after={after}&per_page=100",
                ActivityClientDto[].class, accessToken, after.toEpochSecond());
    }

    public String refreshAccessToken(String refreshToken) {

        HttpEntity<MultiValueMap<String, String>> requestBody = createRequestBody(refreshToken);

        ResponseEntity<String> response = rest.exchange(
                STRAVA_URL_REFRESH,
                HttpMethod.POST,
                requestBody,
                String.class
        );
        if (response.getStatusCode() == HttpStatus.OK) {
            return retrieveTokenFromResponse(response.getBody());
        } else {
            throw new RuntimeException(); // todo: throw new custom exception
        }
    }

    private String retrieveTokenFromResponse(String tokenString) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            RefreshTokenResponseDto tokenResponse = mapper
                    .readValue(tokenString, RefreshTokenResponseDto.class);
            return tokenResponse.getAccess_token();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e); // todo: deal with it
        }
    }

    private HttpEntity<MultiValueMap<String, String>> createRequestBody(String refreshToken) {

        String clientId = StravaPropertyReader.getValue("strava_client_id");
        String clientSecret = StravaPropertyReader.getValue("strava_client_secret");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("client_id", clientId);
        parameters.add("client_secret", clientSecret);
        parameters.add("refresh_token", refreshToken);
        parameters.add("grant_type", "refresh_token");

        return new HttpEntity<>(parameters, headers);
    }
}
