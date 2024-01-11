package pl.koneckimarcin.triathlontrainingmanagement.coach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteEntity;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteRepository;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteService;
import pl.koneckimarcin.triathlontrainingmanagement.exception.BadRequestNonValidFieldsException;
import pl.koneckimarcin.triathlontrainingmanagement.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class CoachService {

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private AthleteService athleteService;

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
        if (!isFirstOrLastNameNullOrEmpty(coach.getFirstName(), coach.getLastName())) { // todo: replace with Validation

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
    public Coach addAthleteToCoach(Long coachId, Long athleteId) {

        if(checkIfIsNotNull(coachId) && athleteService.checkIfIsNotNull(athleteId)) {

            AthleteEntity athlete = athleteRepository.findById(athleteId).get();

            CoachEntity coachToUpdate = coachRepository.findById(coachId).get();
            coachToUpdate.getAthletes().add(athlete);

            return Coach.fromCoachEntity(coachRepository.save(coachToUpdate));
        } else {
            if(!checkIfIsNotNull(coachId)) {
                throw new ResourceNotFoundException("Coach", "id", String.valueOf(coachId));
            } else {
                throw new ResourceNotFoundException("Athlete", "id", String.valueOf(athleteId));
            }
        }
    }
}
