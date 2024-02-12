package pl.koneckimarcin.triathlontrainingmanagement.strava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StravaController {

    @Autowired
    private StravaService stravaService;

    @PreAuthorize("hasAnyAuthority('ATHLETE', 'COACH')")
    @GetMapping("/strava")
    public void getActivityById(@RequestParam Long id) {

        System.out.println(stravaService.getActivityById(id));
    }

    @PreAuthorize("hasAnyAuthority('ATHLETE', 'COACH')")
    @GetMapping("/strava/all")
    public void getAllActivities() {

        ActivityClientDto[] allActivities = stravaService.getAllActivities();
        for(ActivityClientDto activity : allActivities){
            System.out.println(activity);
        }
    }
    @PreAuthorize("hasAuthority('ATHLETE')")
    @PostMapping("/strava/synchronize")
    public void synchronizeActivitiesForAthlete(@RequestParam Long athleteId) {

        stravaService.synchronizeActivitiesForAthlete(athleteId);
    }
}
