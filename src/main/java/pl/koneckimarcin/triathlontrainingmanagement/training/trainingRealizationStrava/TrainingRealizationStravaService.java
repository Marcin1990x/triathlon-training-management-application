package pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealizationStrava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteRepository;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteService;
import pl.koneckimarcin.triathlontrainingmanagement.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainingRealizationStravaService {

    @Autowired
    private TrainingRealizationStravaRepository stravaRepository;

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private AthleteRepository athleteRepository;

    public boolean checkIfIsNotNull(Long id) {
        Optional<TrainingRealizationStravaEntity> trainingRealizationStravaEntity = stravaRepository.findById(id);
        if (trainingRealizationStravaEntity.isPresent()) {
            return true;
        }
        return false;
    }

    public List<TrainingRealizationStrava> getTrainingRealizationsByAthleteId(Long id) {

        if (athleteService.checkIfIsNotNull(id)) {
            return athleteRepository.findById(id).get().getTrainingRealizations()
                    .stream().map(TrainingRealizationStrava::fromTrainingRealizationStravaEntity)
                    .collect(Collectors.toList());
        } else {
            throw new ResourceNotFoundException("Athlete", "id", String.valueOf(id));
        }
    }

    public void deleteById(Long id) {

        if (checkIfIsNotNull(id)) {
            stravaRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("TrainingRealization", "id", String.valueOf(id));
        }
    }
}
