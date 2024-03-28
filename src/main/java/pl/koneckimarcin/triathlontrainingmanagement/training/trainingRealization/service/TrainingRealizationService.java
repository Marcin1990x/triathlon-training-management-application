package pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteEntity;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.repository.AthleteRepository;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.service.AthleteService;
import pl.koneckimarcin.triathlontrainingmanagement.exception.ResourceNotFoundException;
import pl.koneckimarcin.triathlontrainingmanagement.strava.client.StravaClient;
import pl.koneckimarcin.triathlontrainingmanagement.strava.dto.ActivityClientDto;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.TrainingRealizationEntity;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.dto.TrainingRealization;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.dto.TrainingRealizationRequest;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.repository.TrainingRealizationRepository;
import pl.koneckimarcin.triathlontrainingmanagement.user.UserEntity;
import pl.koneckimarcin.triathlontrainingmanagement.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainingRealizationService {

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StravaClient stravaClient;

    @Autowired
    private TrainingRealizationRepository trainingRealizationRepository;

    public boolean checkIfIsNotNull(Long id) {
        Optional<TrainingRealizationEntity> trainingRealizationStravaEntity = trainingRealizationRepository.findById(id);
        if (trainingRealizationStravaEntity.isPresent()) {
            return true;
        }
        return false;
    }

    public List<TrainingRealization> getTrainingRealizationsByAthleteId(Long id) {

        if (athleteService.checkIfIsNotNull(id)) {
            return athleteRepository.findById(id).get().getTrainingRealizations()
                    .stream().map(TrainingRealization::fromTrainingRealizationEntity)
                    .collect(Collectors.toList());
        } else {
            throw new ResourceNotFoundException("Athlete", "id", String.valueOf(id));
        }
    }

    public void deleteById(Long id) {

        if (checkIfIsNotNull(id)) {
            trainingRealizationRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("TrainingRealization", "id", String.valueOf(id));
        }
    }

    public Integer synchronizeActivitiesForAthlete(Long athleteId) {

        String accessToken = getAccessTokenForUser();
        List<Long> existingIds = retrieveTrainingRealizationsIdsForAthlete(athleteId);

        ActivityClientDto[] activitiesFromStrava = stravaClient.getAllActivities(accessToken); // temp: activities from 01/02/2024

        List<ActivityClientDto> nonDuplicatedActivities = checkForDuplicatedIds(existingIds, activitiesFromStrava);

        mapAndSaveToDb(nonDuplicatedActivities, athleteId);

        return nonDuplicatedActivities.size();
    }

    private String getAccessTokenForUser() {

        return retrieveLoggedUser().getStravaAccessToken();
    }

    private void mapAndSaveToDb(List<ActivityClientDto> activities, Long athleteId) {

        List<TrainingRealizationEntity> entitiesList = new ArrayList<>();

        AthleteEntity athlete = athleteRepository.findById(athleteId).get();

        if (activities.size() > 0) {
            for (ActivityClientDto activity : activities) {
                TrainingRealizationEntity realization = activity.mapToRealizationEntity();
                entitiesList.add(realization);
            }
            athlete.getTrainingRealizations().addAll(entitiesList);
            athleteRepository.save(athlete);
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

        return athleteRepository.findById(athleteId).get().getTrainingRealizations()
                .stream().map(TrainingRealizationEntity::getStravaId).toList();
    }

    public TrainingRealization updateTrainingRealizationById(Long id, TrainingRealizationRequest request) {

        if (!checkIfIsNotNull(id)) {
            throw new ResourceNotFoundException("TrainingRealization", "id", String.valueOf(id));
        }
        return updateTrainingRealization(id, request);
    }

    private TrainingRealization updateTrainingRealization(Long id, TrainingRealizationRequest request) {

        TrainingRealizationEntity trainingRealizationToUpdate = trainingRealizationRepository.findById(id).get();
        trainingRealizationToUpdate.setRealizationDescription(request.getRealizationDescription());
        trainingRealizationToUpdate.setRpeLevel(request.getRpeLevel());
        trainingRealizationToUpdate.setFeelings(request.getFeelings());

        TrainingRealizationEntity updated = trainingRealizationRepository.save(trainingRealizationToUpdate);

        return TrainingRealization.fromTrainingRealizationEntity(updated);
    }

    private UserEntity retrieveLoggedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return userRepository.findByUsername(username).get();
    }

    public TrainingRealization addNewTrainingRealizationForAthlete(Long id, TrainingRealization trainingRealization) {

        AthleteEntity athlete;

        if (!athleteRepository.findById(id).isPresent()) {
            throw new ResourceNotFoundException("Athlete", "id", String.valueOf(id));
        }
        athlete = athleteRepository.findById(id).get();

        TrainingRealizationEntity trainingEntity = trainingRealization.mapToTrainingRealizationEntity();

        athlete.getTrainingRealizations().add(trainingEntity);
        athleteRepository.save(athlete);

        return TrainingRealization.fromTrainingRealizationEntity(trainingEntity);
    }
}
