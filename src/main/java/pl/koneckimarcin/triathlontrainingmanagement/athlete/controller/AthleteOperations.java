package pl.koneckimarcin.triathlontrainingmanagement.athlete.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.dto.Athlete;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.dto.AthleteResponseDto;

import java.util.List;
import java.util.Set;

public interface AthleteOperations {

    @PreAuthorize("hasAuthority('ATHLETE') AND @authenticatedUserService.hasValidId(#id)")
    @GetMapping("athletes/{id}")
    public Athlete getById(@PathVariable Long id);

    @PreAuthorize("hasAuthority('COACH')")
    @GetMapping("athletes")
    public List<AthleteResponseDto> getByLastname(@RequestParam String lastname);

    @PreAuthorize("hasAuthority('COACH') AND @authenticatedUserService.hasValidCoachId(#id)")
    @GetMapping("coaches/{id}/athletes")
    public Set<Athlete> getAthletesByCoachId(@PathVariable Long id);

    @PreAuthorize("hasAnyAuthority('ADMIN', 'NEW')")
    @PostMapping("athletes")
    public Athlete addNew(@RequestBody Athlete athlete);

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("athletes/{id}")
    public void deleteById(@PathVariable Long id);
}
