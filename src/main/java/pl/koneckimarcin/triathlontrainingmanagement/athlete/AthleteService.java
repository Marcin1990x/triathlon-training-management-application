package pl.koneckimarcin.triathlontrainingmanagement.athlete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.triathlontrainingmanagement.exception.BadRequestNonValidFieldsException;
import pl.koneckimarcin.triathlontrainingmanagement.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;

    public boolean checkIfIsNotNull(long id) {
        Optional<AthleteEntity> athleteEntity = athleteRepository.findById(id);
        if (athleteEntity.isPresent()) {
            return true;
        }
        return false;
    }

    public Athlete findById(Long id) {

        Optional<AthleteEntity> athleteEntity = athleteRepository.findById(id);

        if (athleteEntity.isPresent()) {
            return Athlete.fromAthleteEntity(athleteEntity.get());
        } else {
            throw new ResourceNotFoundException("Athlete", "id", String.valueOf(id));
        }
    }

    public Athlete addNew(Athlete athlete) {

        if (!isFirstOrLastNameNullOrEmpty(athlete.getFirstName(), athlete.getLastName())) {

            AthleteEntity athleteEntity = athlete.mapToAthleteEntity();
            AthleteEntity savedAthleteEntity = athleteRepository.save(athleteEntity);

            return Athlete.fromAthleteEntity(savedAthleteEntity);
        } else {
            throw new BadRequestNonValidFieldsException(List.of("firstname", "lastname"));
        }
    }

    private boolean isFirstOrLastNameNullOrEmpty(String firstName, String lastName) {

        if (firstName == null || lastName == null) {
            return true;
        } else {
            return firstName.equals("") || lastName.equals("");
        }
    }

    public void deleteById(long id) {

        if (checkIfIsNotNull(id)) {
            athleteRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Athlete", "id", String.valueOf(id));
        }
    }
}
