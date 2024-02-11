package pl.koneckimarcin.triathlontrainingmanagement.strava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealizationStrava.TrainingRealizationStravaEntity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealizationStrava.TrainingRealizationStravaRepository;

@Service
public class StravaService {

    @Autowired
    private StravaClient stravaClient;

    @Autowired
    private TrainingRealizationStravaRepository realizationStravaRepository;

    public ActivityClientDto getActivityById(Long id) {

        return stravaClient.getActivityByIdFromClient(id);
    }

    public ActivityClientDto[] getAllActivities() {

        return stravaClient.getAllActivities();
    }

    public void synchronizeActivitiesForAthlete() {

        //retrieve existing strava activities id's for loggedIn athlete by it's strava id
        //if new add to db
        ActivityClientDto activity = stravaClient.getActivityByIdFromClient(10732991983L);// for test hardcoded stravaId
        mapToTrainingRealizationStravaAndSaveToDb(activity);
    }

    private void mapToTrainingRealizationStravaAndSaveToDb(ActivityClientDto activity) {

        //don't forget to set athleteId
        TrainingRealizationStravaEntity trainingRealizationStrava = activity.mapToRealizationStravaEntity();
        trainingRealizationStrava.setAthleteId(2L); // for test

        realizationStravaRepository.save(trainingRealizationStrava);
    }
}
