package pl.koneckimarcin.triathlontrainingmanagement.strava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StravaService {

    @Autowired
    private StravaClient stravaClient;

    public ActivityClientDto getActivityById(Long id){

        return stravaClient.getActivityByIdFromClient(id);
    }
}
