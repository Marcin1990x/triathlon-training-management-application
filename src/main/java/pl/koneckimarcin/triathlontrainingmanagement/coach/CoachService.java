package pl.koneckimarcin.triathlontrainingmanagement.coach;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteEntity;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.repository.AthleteRepository;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.service.AthleteService;
import pl.koneckimarcin.triathlontrainingmanagement.coach.dto.Coach;
import pl.koneckimarcin.triathlontrainingmanagement.coach.dto.CoachResponseDto;
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

    public CoachResponseDto findById(Long id) {

        Optional<CoachEntity> coachEntity = coachRepository.findById(id);

        if (coachEntity.isPresent()) {
            return CoachResponseDto.fromCoachEntity(coachEntity.get());
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

        if (!checkIfIsNotNull(coachId)) {
            throw new ResourceNotFoundException("Coach", "id", String.valueOf(coachId));
        }
        if (!athleteService.checkIfIsNotNull(athleteId)) {
            throw new ResourceNotFoundException("Athlete", "id", String.valueOf(athleteId));
        }

        AthleteEntity athlete = athleteRepository.findById(athleteId).get();
        CoachEntity coachToUpdate = coachRepository.findById(coachId).get();

        setCoachIdForAthlete(athlete, coachId);

        coachToUpdate.getAthletes().add(athlete);

        return Coach.fromCoachEntity(coachRepository.save(coachToUpdate));
    }

    public Coach removeAthleteFromCoach(Long coachId, Long athleteId) {

        if (!checkIfIsNotNull(coachId)) {
            throw new ResourceNotFoundException("Coach", "id", String.valueOf(coachId));
        }
        if (!athleteService.checkIfIsNotNull(athleteId)) {
            throw new ResourceNotFoundException("Athlete", "id", String.valueOf(athleteId));
        }
        CoachEntity coach = coachRepository.findById(coachId).get();
        Set<AthleteEntity> athletes = coach.getAthletes();

        athletes.removeIf(athlete -> athlete.getId() == athleteId);

        setCoachIdForAthlete(athleteRepository.findById(athleteId).get(), null);

        coachRepository.save(coach);

        //todo: remove also training-plans with coachId?

        return Coach.fromCoachEntity(coach);
    }

    private void setCoachIdForAthlete(AthleteEntity athlete, Long coachId) {

        athlete.setCoachId(coachId);
        athleteRepository.save(athlete);
    }
}
