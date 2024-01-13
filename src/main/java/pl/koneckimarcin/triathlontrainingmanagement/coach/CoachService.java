package pl.koneckimarcin.triathlontrainingmanagement.coach;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteEntity;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteRepository;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteService;
import pl.koneckimarcin.triathlontrainingmanagement.exception.ResourceNotFoundException;

import java.util.Optional;
import java.util.Set;

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

    public Coach addNew(@Valid Coach coach) {

        CoachEntity coachEntity = coach.mapToCoachEntity();
        CoachEntity savedCoachEntity = coachRepository.save(coachEntity);
        return Coach.fromCoachEntity(savedCoachEntity);
    }

    public void deleteById(Long id) {
        if (checkIfIsNotNull(id)) {
            coachRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Coach", "id", String.valueOf(id));
        }
    }

    public Coach addAthleteToCoach(Long coachId, Long athleteId) {

        if (checkIfIsNotNull(coachId) && athleteService.checkIfIsNotNull(athleteId)) {

            AthleteEntity athlete = athleteRepository.findById(athleteId).get();

            CoachEntity coachToUpdate = coachRepository.findById(coachId).get();
            coachToUpdate.getAthletes().add(athlete);

            return Coach.fromCoachEntity(coachRepository.save(coachToUpdate));
        } else {
            if (!checkIfIsNotNull(coachId)) {
                throw new ResourceNotFoundException("Coach", "id", String.valueOf(coachId));
            } else {
                throw new ResourceNotFoundException("Athlete", "id", String.valueOf(athleteId));
            }
        }
    }

    public Coach removeAthleteFromCoach(Long coachId, Long athleteId) {

        if (checkIfIsNotNull(coachId) && athleteService.checkIfIsNotNull(athleteId)) {

            CoachEntity coach = coachRepository.findById(coachId).get();
            Set<AthleteEntity> athletes = coach.getAthletes();

            athletes.removeIf(athlete -> athlete.getId() == athleteId);

            coachRepository.save(coach);

            //todo: remove also training-plans with coachId?

            return Coach.fromCoachEntity(coach);

        } else {
            if (!checkIfIsNotNull(coachId)) {
                throw new ResourceNotFoundException("Coach", "id", String.valueOf(coachId));
            } else {
                throw new ResourceNotFoundException("Athlete", "id", String.valueOf(athleteId));
            }
        }
    }
}
