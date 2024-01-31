package pl.koneckimarcin.triathlontrainingmanagement.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteEntity;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteRepository;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteService;
import pl.koneckimarcin.triathlontrainingmanagement.coach.CoachEntity;
import pl.koneckimarcin.triathlontrainingmanagement.coach.CoachRepository;
import pl.koneckimarcin.triathlontrainingmanagement.coach.CoachService;
import pl.koneckimarcin.triathlontrainingmanagement.exception.IsAlreadyAssigned;
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

        if (!checkIfIsNotNull(userId)) {
            throw new ResourceNotFoundException("User", "id", String.valueOf(userId));
        }
        if (!coachService.checkIfIsNotNull(coachId)) {
            throw new ResourceNotFoundException("Coach", "id", String.valueOf(coachId));
        }
        UserEntity userToUpdate = userRepository.findById(userId).get();
        if (userToUpdate.hasAssignedCoach()) { // todo: solve - what if coach is already assigned
            throw new IsAlreadyAssigned("User", String.valueOf(userId), "Coach",
                    String.valueOf(userToUpdate.getCoachEntity().getId()));
        }
        CoachEntity coach = coachRepository.findById(coachId).get();

        userToUpdate.setCoachEntity(coach);

        return User.fromUserEntity(userRepository.save(userToUpdate));
    }

    public User addAthleteToUser(Long userId, Long athleteId) {

        if (!checkIfIsNotNull(userId)) {
            throw new ResourceNotFoundException("User", "id", String.valueOf(userId));
        }
        if (!athleteService.checkIfIsNotNull(athleteId)) {
            throw new ResourceNotFoundException("Athlete", "id", String.valueOf(athleteId));
        }
        UserEntity userToUpdate = userRepository.findById(userId).get();
        if (userToUpdate.hasAssignedAthlete()) { // todo: solve - what if coach is already assigned
            throw new IsAlreadyAssigned("User", String.valueOf(userId), "Athlete",
                    String.valueOf(userToUpdate.getAthleteEntity().getId()));
        }
        AthleteEntity athlete = athleteRepository.findById(athleteId).get();

        userToUpdate.setAthleteEntity(athlete);

        return User.fromUserEntity(userRepository.save(userToUpdate));
    }
}
