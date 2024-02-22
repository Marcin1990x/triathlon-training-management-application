package pl.koneckimarcin.triathlontrainingmanagement.strava.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;

public interface StravaOperations {

    @PreAuthorize("hasAuthority('ATHLETE')")
    @PostMapping("/strava/refreshAccessToken")
    public void refreshAccessToken();
}
