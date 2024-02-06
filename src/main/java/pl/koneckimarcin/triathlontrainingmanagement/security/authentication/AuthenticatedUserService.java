package pl.koneckimarcin.triathlontrainingmanagement.security.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.triathlontrainingmanagement.user.UserEntity;
import pl.koneckimarcin.triathlontrainingmanagement.user.UserRepository;

@Service
public class AuthenticatedUserService {

    @Autowired
    private UserRepository userRepository;

    public boolean hasValidId(Long id) {

        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UserEntity dbUser = userRepository.findByUsername(username).get();

        return dbUser.getId().equals(id);
    }

    public boolean hasItInItsResources(Long id) {

        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

        UserEntity user = userRepository.findByUsername(username).get();

        if(user.hasAssignedAthlete()){
            return user.getAthleteEntity().getTrainingPlans()
                    .stream().anyMatch(tp -> tp.getId().equals(id));
        }
        else {
            return user.getCoachEntity().getTrainingPlans()
                    .stream().anyMatch(tp -> tp.getId().equals(id));
        }
    }

    public boolean hasAssignedAthlete(Long id) {

        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UserEntity dbUser = userRepository.findByUsername(username).get();

        return dbUser.getCoachEntity().getAthletes()
                .stream().anyMatch(athlete -> athlete.getId().equals(id));
    }
}
