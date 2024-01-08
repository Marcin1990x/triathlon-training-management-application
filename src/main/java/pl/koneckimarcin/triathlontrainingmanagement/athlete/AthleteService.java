package pl.koneckimarcin.triathlontrainingmanagement.athlete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.triathlontrainingmanagement.EntityService;
import pl.koneckimarcin.triathlontrainingmanagement.exception.BadRequestNonValidFieldsException;
import pl.koneckimarcin.triathlontrainingmanagement.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AthleteService implements EntityService<AthleteEntity, Athlete> {

    @Autowired
    private AthleteRepository athleteRepository;

    public boolean checkIfIsNotNull(long id) {
        Optional<AthleteEntity> athleteEntity = athleteRepository.findById(id);
        if(athleteEntity.isPresent()) {
            return true;
        }
        return false;
    }
    public List<Athlete> getAll() {

        List<AthleteEntity> athleteEntities = athleteRepository.findAll();
        List<Athlete> athletes = new ArrayList<>();

        for(AthleteEntity athleteEntity : athleteEntities) {
            athletes.add(athleteEntity.mapToAthlete());
        }
        return athletes;
    }
    public AthleteEntity findById(long id) {

        Optional<AthleteEntity> athleteEntity = athleteRepository.findById(id);

        if(athleteEntity.isPresent()) {
            return athleteEntity.get();
        } else {
            throw new ResourceNotFoundException("AthleteEntity", "id", String.valueOf(id));
        }
    }

    public Athlete findAthleteByLastName(String lastName) {

        AthleteEntity athleteEntity = athleteRepository.findByLastName(lastName);
        if(athleteEntity != null) {
            return athleteEntity.mapToAthlete();
        } else {
            throw new ResourceNotFoundException("Athlete", "lastname", lastName);
        }
    }

    public Athlete addNew(Athlete athlete) {

        if(!isFirstOrLastNameNullOrEmpty(athlete.getFirstName(), athlete.getLastName())) {
            AthleteEntity athleteEntity = athlete.mapToAthleteEntity();
            return athleteRepository.save(athleteEntity).mapToAthlete();
        } else {
            throw new BadRequestNonValidFieldsException(List.of("firstname", "lastname"));
        }
    }
    private boolean isFirstOrLastNameNullOrEmpty(String firstName, String lastName) {

        if(firstName == null || lastName == null) {
            return true;
        } else {
            return firstName.equals("") || lastName.equals("");
        }
    }

    public void deleteById(long id) {

        if(checkIfIsNotNull(id)) {
            athleteRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("AthleteEntity", "id", String.valueOf(id));
        }
    }
}
