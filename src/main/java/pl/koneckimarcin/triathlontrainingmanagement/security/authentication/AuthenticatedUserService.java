package pl.koneckimarcin.triathlontrainingmanagement.security.authentication;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlanEntity;
import pl.koneckimarcin.triathlontrainingmanagement.user.UserEntity;
import pl.koneckimarcin.triathlontrainingmanagement.user.UserRepository;

import java.util.Set;

@Service
public class AuthenticatedUserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    public boolean hasValidId(Long id) {

        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UserEntity dbUser = userRepository.findByUsername(username).get();

        return dbUser.getId().equals(id);
    }

    public boolean hasValidAthleteId(Long id) {

        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UserEntity dbUser = userRepository.findByUsername(username).get();

        Long temp = dbUser.getAthleteEntity().getId();

        if(dbUser.getAthleteEntity() != null) {
            return dbUser.getAthleteEntity().getId().equals(id);
        } else return false;
    }
    public boolean hasValidCoachId(Long id) {

        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UserEntity dbUser = userRepository.findByUsername(username).get();

        if(dbUser.getCoachEntity() != null) {
            return dbUser.getCoachEntity().getId().equals(id);
        } else return false;
    }

    public boolean hasTrainingPlanInItsResources(Long id) {

        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

        UserEntity user = userRepository.findByUsername(username).get();

        entityManager.clear();

        if (user.hasAssignedAthlete()) {
            return user.getAthleteEntity().getTrainingPlans()
                    .stream().anyMatch(tp -> tp.getId().equals(id));
        } else {
            return user.getCoachEntity().getTrainingPlans()
                    .stream().anyMatch(tp -> tp.getId().equals(id));
        }
    }

    public boolean hasTrainingRealizationInItsResources(Long id) {

        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

        UserEntity user = userRepository.findByUsername(username).get();
        entityManager.clear();

        return user.getAthleteEntity().getTrainingRealizations()
                .stream().anyMatch(tr -> tr.getId().equals(id));
    }

    public boolean hasAssignedAthlete(Long id) {

        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UserEntity dbUser = userRepository.findByUsername(username).get();

        return dbUser.getCoachEntity().getAthletes()
                .stream().anyMatch(athlete -> athlete.getId().equals(id));
    }

    public boolean hasStageInItsResources(Long id) {

        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UserEntity dbUser = userRepository.findByUsername(username).get();
        entityManager.clear();

        boolean result = false;

        Set<TrainingPlanEntity> plans = dbUser.getCoachEntity().getTrainingPlans();
        for (TrainingPlanEntity plan : plans) {
            result = plan.getStages().stream().anyMatch(s -> s.getId().equals(id));
            if (result) break;
        }
        return result;
    }
    public boolean hasAthleteValidCoachId(Long id) {

        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UserEntity dbUser = userRepository.findByUsername(username).get();

        if(dbUser.getAthleteEntity() == null) {
            return false;
        }
        if(dbUser.getAthleteEntity().getCoachId() == null){
            return false;
        }
        return dbUser.getAthleteEntity().getCoachId().equals(id);
    }
}
