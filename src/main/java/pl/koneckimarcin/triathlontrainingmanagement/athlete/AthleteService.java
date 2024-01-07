package pl.koneckimarcin.triathlontrainingmanagement.athlete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.triathlontrainingmanagement.exception.BadRequestEmptyFieldsException;
import pl.koneckimarcin.triathlontrainingmanagement.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;

    public boolean checkIfAthleteEntityIsNotNull(long id) {
        Optional<AthleteEntity> athleteEntity = athleteRepository.findById(id);
        if(athleteEntity.isPresent()) {
            return true;
        }
        return false;
    }

    public List<Athlete> getAllAthletes() {

        List<AthleteEntity> athleteEntities = athleteRepository.findAll();
        List<Athlete> athletes = new ArrayList<>();

        for(AthleteEntity athleteEntity : athleteEntities) {
            athletes.add(athleteEntity.mapToAthlete());
        }
        return athletes;
    }
    public AthleteEntity findAthleteEntityById(long id) {

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

    public Athlete addAthlete(Athlete athlete) {

        if(!isFirstOrLastNameEmpty(athlete.getFirstName(), athlete.getLastName())) {
            AthleteEntity athleteEntity = athlete.mapToAthleteEntity();
            return athleteRepository.save(athleteEntity).mapToAthlete();
        } else {
            throw new BadRequestEmptyFieldsException(List.of("firstname", "lastname"));
        }

    }
    private boolean isFirstOrLastNameEmpty(String firstName, String lastName) {

        return firstName.equals("") || lastName.equals("");
    }

    public void deleteAthleteEntityById(long id) {

        if(checkIfAthleteEntityIsNotNull(id)) {
            athleteRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("AthleteEntity", "id", String.valueOf(id));
        }
    }
}
