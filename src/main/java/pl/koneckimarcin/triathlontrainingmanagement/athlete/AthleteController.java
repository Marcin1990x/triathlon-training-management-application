package pl.koneckimarcin.triathlontrainingmanagement.athlete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/athlete")
public class AthleteController {

    @Autowired
    AthleteService athleteService;

    @GetMapping
    public List<Athlete> getAllAthletes() {

        return athleteService.getAllAthletes();
    }

    @GetMapping("/{id}")
    public AthleteEntity getAthleteEntityById(@PathVariable long id) {

        return athleteService.findAthleteEntityById(id);
    }

    @GetMapping("/lastName")
    public Athlete getAthleteByLastName(@RequestParam String lastName) {

        return athleteService.findAthleteByLastName(lastName);
    }

    @PostMapping
    public Athlete addAthlete(@RequestBody Athlete athlete) {

        return athleteService.addAthlete(athlete);
    }

    @DeleteMapping("/{id}")
    public void deleteAthleteEntityById(@PathVariable long id) {

        athleteService.deleteAthleteEntityById(id);
    }
}
