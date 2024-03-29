package pl.koneckimarcin.triathlontrainingmanagement.athlete.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.dto.Athlete;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.dto.AthleteResponseDto;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.service.AthleteService;

import java.util.List;
import java.util.Set;

@RestController
public class AthleteController implements AthleteOperations {

    @Autowired
    AthleteService athleteService;

    public AthleteResponseDto getById(Long id) {

        return athleteService.getById(id);
    }

    @Override
    public List<AthleteResponseDto> getByLastname(String lastname) {

        return athleteService.getByLastname(lastname);
    }

    @Override
    public Set<Athlete> getAthletesByCoachId(Long id) {

        return athleteService.getAthletesByCoachId(id);
    }

    public Athlete addNew(@Valid Athlete athlete) {

        return athleteService.addNew(athlete);
    }

    public void deleteById(Long id) {

        athleteService.deleteById(id);
    }
}
