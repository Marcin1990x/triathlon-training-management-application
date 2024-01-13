package pl.koneckimarcin.triathlontrainingmanagement.coach;

import org.springframework.web.bind.annotation.*;

public interface CoachOperations {

    @GetMapping("coaches/{id}")
    public Coach getById(@PathVariable Long id);

    @PostMapping("coaches")
    public Coach addNew(@RequestBody Coach coach);

    @DeleteMapping("coaches/{id}")
    public void deleteById(@PathVariable Long id);

    @PutMapping("coaches/{coachId}/athletes/{athleteId}/add")
    public Coach addAthleteToCoach(@PathVariable Long coachId, @PathVariable Long athleteId);

    @PutMapping("coaches/{coachId}/athletes/{athleteId}/remove")
    public Coach removeAthleteFromCoach(@PathVariable Long coachId, @PathVariable Long athleteId);
}
