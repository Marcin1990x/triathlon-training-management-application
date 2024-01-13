package pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteEntity;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteRepository;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteService;
import pl.koneckimarcin.triathlontrainingmanagement.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainingRealizationService {

    @Autowired
    private TrainingRealizationRepository trainingRealizationRepository;

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private AthleteRepository athleteRepository;

    public boolean checkIfIsNotNull(Long id) {
        Optional<TrainingRealizationEntity> trainingRealizationEntity = trainingRealizationRepository.findById(id);
        if (trainingRealizationEntity.isPresent()) {
            return true;
        }
        return false;
    }

    public List<TrainingRealization> getTrainingRealizationsByAthleteId(Long id) {

        if (athleteService.checkIfIsNotNull(id)) {
            return athleteRepository.findById(id).get().getTrainingRealization()
                    .stream().map(TrainingRealization::fromTrainingRealizationEntity).collect(Collectors.toList());
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

    public TrainingRealization addNewTrainingRealizationToAthlete(Long id, @Valid TrainingRealization trainingRealization) {

        if (athleteService.checkIfIsNotNull(id)) {
            Optional<AthleteEntity> athleteEntity = athleteRepository.findById(id);

            List<TrainingRealizationEntity> trainingRealizations = athleteEntity.get().getTrainingRealization();
            trainingRealizations.add(trainingRealization.mapToTrainingRealizationEntity());

            athleteRepository.save(athleteEntity.get());
            return trainingRealization;
        } else {
            throw new ResourceNotFoundException("Athlete", "id", String.valueOf(id));
        }
    }
}
