package pl.koneckimarcin.triathlontrainingmanagement.athlete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;

    public List<Athlete> getAllAthletes() {

        List<AthleteEntity> athleteEntities = athleteRepository.findAll();
        List<Athlete> athletes = new ArrayList<>();

        for(AthleteEntity athleteEntity : athleteEntities) {
            athletes.add(athleteEntity.mapToAthlete());
        }
        return athletes;
    }
    public Athlete findAthleteById(long id) {

        Optional<AthleteEntity> athleteEntity = athleteRepository.findById(id);

        if(athleteEntity.isPresent()) {
            return athleteEntity.get().mapToAthlete();
        } else {
            return null;
            // todo: exception
        }
    }
    public Athlete addAthlete(Athlete athlete) {

        if(!isFirstOrLastNameEmpty(athlete.getFirstName(), athlete.getLastName())) {
            AthleteEntity athleteEntity = athlete.mapToAthleteEntity();
            return athleteRepository.save(athleteEntity).mapToAthlete();
        } else {
            return null;
            // todo: exception
        }

    }
    private boolean isFirstOrLastNameEmpty(String firstName, String lastName) {

        return firstName.equals("") || lastName.equals("");
    }
}
