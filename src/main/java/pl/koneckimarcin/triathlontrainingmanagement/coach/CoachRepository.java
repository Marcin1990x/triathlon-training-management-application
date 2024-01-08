package pl.koneckimarcin.triathlontrainingmanagement.coach;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository <CoachEntity, Long> {
}
