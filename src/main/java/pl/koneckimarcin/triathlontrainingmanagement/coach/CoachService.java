package pl.koneckimarcin.triathlontrainingmanagement.coach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.triathlontrainingmanagement.EntityService;
import pl.koneckimarcin.triathlontrainingmanagement.exception.BadRequestNonValidFieldsException;
import pl.koneckimarcin.triathlontrainingmanagement.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CoachService implements EntityService<CoachEntity, Coach> {

    @Autowired
    private CoachRepository coachRepository;

    @Override
    public boolean checkIfIsNotNull(long id) {
        Optional<CoachEntity> coachEntity = coachRepository.findById(id);
        if (coachEntity.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public List<Coach> getAll() {
        List<CoachEntity> coachEntities = coachRepository.findAll();
        List<Coach> coaches = new ArrayList<>();

        for (CoachEntity coachEntity : coachEntities) {
            coaches.add(coachEntity.mapToCoach());
        }
        return coaches;
    }

    @Override
    public CoachEntity findById(long id) {

        Optional<CoachEntity> coachEntity = coachRepository.findById(id);

        if (coachEntity.isPresent()) {
            return coachEntity.get();
        } else {
            throw new ResourceNotFoundException("CoachEntity", "id", String.valueOf(id));
        }
    }

    @Override
    public Coach addNew(Coach coach) {
        if (!isFirstOrLastNameNullOrEmpty(coach.getFirstName(), coach.getLastName())) {
            CoachEntity coachEntity = coach.mapToCoachEntity();
            return coachRepository.save(coachEntity).mapToCoach();
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

    @Override
    public void deleteById(long id) {
        if (checkIfIsNotNull(id)) {
            coachRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("CoachEntity", "id", String.valueOf(id));
        }
    }
}
