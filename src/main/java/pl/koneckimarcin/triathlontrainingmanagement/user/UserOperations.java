package pl.koneckimarcin.triathlontrainingmanagement.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

public interface UserOperations {

    @GetMapping("/users")
    public List<User> getAllUsers();

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long userId);

    @PutMapping("/users/{userId}/coaches/{coachId}/add")
    public User addCoachToUser(@PathVariable Long userId, @PathVariable Long coachId);

    @PutMapping("/users/{userId}/athletes/{athleteId}/add")
    public User addAthleteToUser(@PathVariable Long userId, @PathVariable Long athleteId);
}

