package pl.koneckimarcin.triathlontrainingmanagement.strava.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import pl.koneckimarcin.triathlontrainingmanagement.exception.JsonMapperException;
import pl.koneckimarcin.triathlontrainingmanagement.strava.StravaPropertyReader;
import pl.koneckimarcin.triathlontrainingmanagement.strava.dto.AccessTokenDto;
import pl.koneckimarcin.triathlontrainingmanagement.strava.dto.RefreshTokenResponseDto;

@Service
public class StravaClientService {

    public AccessTokenDto retrieveTokenFromResponse(String responseBody) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            RefreshTokenResponseDto tokenResponse = mapper
                    .readValue(responseBody, RefreshTokenResponseDto.class);
            return new AccessTokenDto(tokenResponse.getAccess_token(), tokenResponse.getExpires_at());
        } catch (JsonProcessingException e) {
            throw new JsonMapperException();
        }
    }

    public HttpEntity<MultiValueMap<String, String>> createRequestBody(String refreshToken) {

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
