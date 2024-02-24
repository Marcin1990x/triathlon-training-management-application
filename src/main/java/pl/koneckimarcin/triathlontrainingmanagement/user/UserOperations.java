package pl.koneckimarcin.triathlontrainingmanagement.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

public interface UserOperations {

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    public List<User> getAllUsers();

    @PreAuthorize("hasAuthority('ADMIN') OR @authenticatedUserService.hasValidId(#id)")
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long userId);

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/users/{userId}/coaches/{coachId}/add")
    public User addCoachToUser(@PathVariable Long userId, @PathVariable Long coachId);

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/users/{userId}/athletes/{athleteId}/add")
    public User addAthleteToUser(@PathVariable Long userId, @PathVariable Long athleteId);

    @PreAuthorize("hasAuthority('ATHLETE') AND @authenticatedUserService.hasValidId(#id)")
    @PostMapping("/users/{id}/refreshAccessToken")
    public void refreshAccessTokenForUser(@PathVariable Long id);
}

