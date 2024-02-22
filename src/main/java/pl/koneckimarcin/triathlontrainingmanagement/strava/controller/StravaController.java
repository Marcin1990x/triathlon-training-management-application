package pl.koneckimarcin.triathlontrainingmanagement.strava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.triathlontrainingmanagement.strava.service.StravaService;

@RestController
public class StravaController implements StravaOperations{

    @Autowired
    private StravaService stravaService;

    @Override
    public void refreshAccessToken() {

        stravaService.refreshAccessToken();
    }
}
