package pl.koneckimarcin.triathlontrainingmanagement.coach;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoachRepository extends JpaRepository <CoachEntity, Long> {

    Optional<CoachEntity> findByTrainingPlanEntities_id(Long id);
}
