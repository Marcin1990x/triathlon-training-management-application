package pl.koneckimarcin.triathlontrainingmanagement.strava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteRepository;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealizationStrava.TrainingRealizationStravaEntity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealizationStrava.TrainingRealizationStravaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class StravaService {

    @Autowired
    private StravaClient stravaClient;

    @Autowired
    private TrainingRealizationStravaRepository realizationStravaRepository;

    @Autowired
    private AthleteRepository athleteRepository;

    public ActivityClientDto getActivityById(Long id) {

        return stravaClient.getActivityByIdFromClient(id);
    }

    public ActivityClientDto[] getAllActivities() {

        return stravaClient.getAllActivities();
    }

    public void synchronizeActivitiesForAthlete(Long athleteId) {

        List<Long> existingIds = retrieveTrainingRealizationsIdsForAthlete(athleteId);
        ActivityClientDto[] activitiesFromStrava = stravaClient.getAllActivities(); // temp: activities from 01/02/2024

        List<ActivityClientDto> nonDuplicatedActivities = checkForDuplicatedIds(existingIds, activitiesFromStrava);

        mapAndSaveToDb(nonDuplicatedActivities, athleteId);
    }

    private void mapAndSaveToDb(List<ActivityClientDto> activities, Long athleteId) {

        List<TrainingRealizationStravaEntity> entitiesList = new ArrayList<>();

        if (activities.size() > 0) {
            for (ActivityClientDto activity : activities) {
                TrainingRealizationStravaEntity realization = activity.mapToRealizationStravaEntity();
                realization.setAthleteId(athleteId);
                entitiesList.add(realization);
            }
            realizationStravaRepository.saveAll(entitiesList);
        }
    }

    private List<ActivityClientDto> checkForDuplicatedIds(List<Long> existingIds, ActivityClientDto[] activities) {

        List<ActivityClientDto> nonDuplicatedActivities = new ArrayList<>();

        for (ActivityClientDto activity : activities) {
            if (!existingIds.contains(activity.getId())) {
                nonDuplicatedActivities.add(activity);
            }
        }
        return nonDuplicatedActivities;
    }

    private List<Long> retrieveTrainingRealizationsIdsForAthlete(Long athleteId) {

        List<TrainingRealizationStravaEntity> realizationsList = realizationStravaRepository.findByAthleteId(athleteId);
        return realizationsList.stream().map(TrainingRealizationStravaEntity::getStravaId).toList();
    }
}
