package pl.koneckimarcin.triathlontrainingmanagement.athlete;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AthleteRepository extends JpaRepository <AthleteEntity, Long> {
}
