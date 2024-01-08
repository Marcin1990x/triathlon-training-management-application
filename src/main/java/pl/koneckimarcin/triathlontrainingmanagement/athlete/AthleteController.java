package pl.koneckimarcin.triathlontrainingmanagement.athlete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.triathlontrainingmanagement.EntityController;

import java.util.List;

@RestController
@RequestMapping("/athlete")
public class AthleteController implements EntityController <AthleteEntity, Athlete> {

    @Autowired
    AthleteService athleteService;

    public List<Athlete> getAll() {

        return athleteService.getAll();
    }

    public AthleteEntity getById(@PathVariable long id) {

        return athleteService.findById(id);
    }

    @GetMapping("/lastName")
    public Athlete getByLastName(@RequestParam String lastName) {

        return athleteService.findAthleteByLastName(lastName);
    }

    public Athlete addNew(@RequestBody Athlete athlete) {

        return athleteService.addNew(athlete);
    }

    public void deleteById(@PathVariable long id) {

        athleteService.deleteById(id);
    }
}
