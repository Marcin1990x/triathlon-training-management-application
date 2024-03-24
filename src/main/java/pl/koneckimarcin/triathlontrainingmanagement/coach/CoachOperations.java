package pl.koneckimarcin.triathlontrainingmanagement.coach;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.triathlontrainingmanagement.coach.dto.Coach;
import pl.koneckimarcin.triathlontrainingmanagement.coach.dto.CoachResponseDto;

public interface CoachOperations {

    @GetMapping("coaches/{id}")
    //@PreAuthorize("hasAuthority('COACH') AND @authenticatedUserService.hasValidCoachId(#id)")
    public CoachResponseDto getById(@PathVariable Long id); //allow athlete to see his coach
    @PreAuthorize("hasAnyAuthority('ADMIN', 'NEW')")
    @PostMapping("coaches")
    public Coach addNew(@RequestBody Coach coach);

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("coaches/{id}")
    public void deleteById(@PathVariable Long id);

    @PreAuthorize("hasAuthority('COACH') AND @authenticatedUserService.hasValidId(#coachId)")
    @PutMapping("coaches/{coachId}/athletes/{athleteId}/add")
    public Coach addAthleteToCoach(@PathVariable Long coachId, @PathVariable Long athleteId);

    @PreAuthorize("hasAuthority('COACH') AND @authenticatedUserService.hasValidId(#coachId)")
    @PutMapping("coaches/{coachId}/athletes/{athleteId}/remove")
    public Coach removeAthleteFromCoach(@PathVariable Long coachId, @PathVariable Long athleteId);
}
