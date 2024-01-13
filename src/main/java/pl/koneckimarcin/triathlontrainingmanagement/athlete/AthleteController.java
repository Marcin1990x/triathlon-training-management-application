package pl.koneckimarcin.triathlontrainingmanagement.athlete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class AthleteController implements AthleteOperations {

    @Autowired
    AthleteService athleteService;

    public Athlete getById(Long id) {

        return athleteService.getById(id);
    }

    @Override
    public Set<Athlete> getAthletesByCoachId(Long id) {

        return athleteService.getAthletesByCoachId(id);
    }

    public Athlete addNew(Athlete athlete) {

        return athleteService.addNew(athlete);
    }

    public void deleteById(Long id) {

        athleteService.deleteById(id);
    }
}