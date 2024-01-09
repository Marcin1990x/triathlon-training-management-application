package pl.koneckimarcin.triathlontrainingmanagement.athlete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/athletes")
public class AthleteController {

    @Autowired
    AthleteService athleteService;


    @GetMapping("{id}")
    public Athlete getById(@PathVariable Long id) {

        return athleteService.findById(id);
    }

    @PostMapping
    public Athlete addNew(@RequestBody Athlete athlete) {

        return athleteService.addNew(athlete);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {

        athleteService.deleteById(id);
    }
}
