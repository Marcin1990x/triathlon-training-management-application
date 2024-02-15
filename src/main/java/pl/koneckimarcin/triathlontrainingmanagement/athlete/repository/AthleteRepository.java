package pl.koneckimarcin.triathlontrainingmanagement.athlete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteEntity;

public interface AthleteRepository extends JpaRepository <AthleteEntity, Long> {

    public AthleteEntity findByLastName(String lastName);
}
