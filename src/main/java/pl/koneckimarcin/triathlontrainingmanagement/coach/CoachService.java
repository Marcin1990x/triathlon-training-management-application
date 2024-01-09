package pl.koneckimarcin.triathlontrainingmanagement.coach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.triathlontrainingmanagement.exception.BadRequestNonValidFieldsException;
import pl.koneckimarcin.triathlontrainingmanagement.exception.ResourceNotFoundException;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.TrainingPlan;

import java.util.List;
import java.util.Optional;

@Service
public class CoachService {

    @Autowired
    private CoachRepository coachRepository;

    public boolean checkIfIsNotNull(Long id) {
        Optional<CoachEntity> coachEntity = coachRepository.findById(id);
        if (coachEntity.isPresent()) {
            return true;
        }
        return false;
    }

    public Coach findById(Long id) {

        Optional<CoachEntity> coachEntity = coachRepository.findById(id);

        if (coachEntity.isPresent()) {
            return Coach.fromCoachEntity(coachEntity.get());
        } else {
            throw new ResourceNotFoundException("Coach", "id", String.valueOf(id));
        }
    }

    public Coach addNew(Coach coach) {
        if (!isFirstOrLastNameNullOrEmpty(coach.getFirstName(), coach.getLastName())) {

            CoachEntity coachEntity = coach.mapToCoachEntity();
            CoachEntity savedCoachEntity = coachRepository.save(coachEntity);
            return Coach.fromCoachEntity(savedCoachEntity);
        } else {
            throw new BadRequestNonValidFieldsException(List.of("firstname", "lastname"));
        }
    }

    private boolean isFirstOrLastNameNullOrEmpty(String firstName, String lastName) {

        if (firstName == null || lastName == null) {
            return true;
        } else {
            return firstName.equals("") || lastName.equals("");
        }
    }

    public void deleteById(Long id) {
        if (checkIfIsNotNull(id)) {
            coachRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Coach", "id", String.valueOf(id));
        }
    }
    public Coach addNewTrainingPlan(Long coachId, TrainingPlan trainingPlan) {

        Coach coachToUpdate;

        if(checkIfIsNotNull(coachId)) {
            Optional<CoachEntity> coachEntity = coachRepository.findById(coachId);
            coachToUpdate = Coach.fromCoachEntity(coachEntity.get());
            coachToUpdate.getTrainingPlans().add(trainingPlan);

            coachRepository.save(coachToUpdate.mapToCoachEntity());
        } else {
            throw new ResourceNotFoundException("Coach", "id", String.valueOf(coachId));
        }
        return coachToUpdate;
    }
}
