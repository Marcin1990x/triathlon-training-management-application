package pl.koneckimarcin.triathlontrainingmanagement.athlete;

import org.springframework.web.bind.annotation.*;

import java.util.Set;

public interface AthleteOperations {

    @GetMapping("athletes/{id}")
    public Athlete getById(@PathVariable Long id);

    @GetMapping("coaches/{id}/athletes")
    public Set<Athlete> getAthletesByCoachId(@PathVariable Long id);

    @PostMapping("athletes")
    public Athlete addNew(@RequestBody Athlete athlete);

    @DeleteMapping("athletes/{id}")
    public void deleteById(@PathVariable Long id);
}
