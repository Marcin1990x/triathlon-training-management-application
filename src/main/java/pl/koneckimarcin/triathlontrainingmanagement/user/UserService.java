package pl.koneckimarcin.triathlontrainingmanagement.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteEntity;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.repository.AthleteRepository;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.service.AthleteService;
import pl.koneckimarcin.triathlontrainingmanagement.coach.CoachEntity;
import pl.koneckimarcin.triathlontrainingmanagement.coach.CoachRepository;
import pl.koneckimarcin.triathlontrainingmanagement.coach.CoachService;
import pl.koneckimarcin.triathlontrainingmanagement.exception.IsAlreadyAssignedException;
import pl.koneckimarcin.triathlontrainingmanagement.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private CoachService coachService;

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private AthleteRepository athleteRepository;

    public boolean checkIfIsNotNull(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isPresent()) {
            return true;
        }
        return false;
    }

    public User getUserById(Long id) {

        if (checkIfIsNotNull(id)) {
            return User.fromUserEntity(userRepository.findById(id).get());
        } else {
            throw new ResourceNotFoundException("User", "id", String.valueOf(id));
        }
    }

    public List<User> getAllUsers() {

        List<UserEntity> allUsers = userRepository.findAll();

        if (allUsers.size() > 0) {
            return allUsers.stream().map(User::fromUserEntity).collect(Collectors.toList());
        } else {
            throw new ResourceNotFoundException("User", "id", "User list empty.");
        }
    }

    public User addCoachToUser(Long userId, Long coachId) {

        UserEntity userToUpdate = userRepository.findById(userId).get();
        CoachEntity coach = coachRepository.findById(coachId).get();

        addCoachToUserCheckForExceptions(userToUpdate, coach, userId, coachId);

        coach.setAssignedToUser(true);
        coachRepository.save(coach);
        userToUpdate.setCoachEntity(coach);

        return User.fromUserEntity(userRepository.save(userToUpdate));
    }

    private void addCoachToUserCheckForExceptions(UserEntity user, CoachEntity coach, Long userId, Long coachId) {

        if (!checkIfIsNotNull(userId)) {
            throw new ResourceNotFoundException("User", "id", String.valueOf(userId));
        }
        if (!coachService.checkIfIsNotNull(coachId)) {
            throw new ResourceNotFoundException("Coach", "id", String.valueOf(coachId));
        }
        if (user.hasAssignedCoach()) {
            throw new IsAlreadyAssignedException("User", String.valueOf(userId));
        }
        if (coach.isAssignedToUser()) {
            throw new IsAlreadyAssignedException("Coach", String.valueOf(coachId));
        }
    }

    public User addAthleteToUser(Long userId, Long athleteId) {

        UserEntity userToUpdate = userRepository.findById(userId).get();
        AthleteEntity athlete = athleteRepository.findById(athleteId).get();

        addAthleteToUserCheckForExceptions(userToUpdate, athlete, userId, athleteId);

        athlete.setAssignedToUser(true);
        athleteRepository.save(athlete);
        userToUpdate.setAthleteEntity(athlete);

        return User.fromUserEntity(userRepository.save(userToUpdate));
    }

    private void addAthleteToUserCheckForExceptions(UserEntity user, AthleteEntity athlete, Long userId, Long athleteId) {

        if (!checkIfIsNotNull(userId)) {
            throw new ResourceNotFoundException("User", "id", String.valueOf(userId));
        }
        if (!athleteService.checkIfIsNotNull(athleteId)) {
            throw new ResourceNotFoundException("Athlete", "id", String.valueOf(athleteId));
        }
        if (user.hasAssignedAthlete()) {
            throw new IsAlreadyAssignedException("User", String.valueOf(userId));
        }
        if (athlete.isAssignedToUser()) {
            throw new IsAlreadyAssignedException("Athlete", String.valueOf(athleteId));
        }
    }
}


