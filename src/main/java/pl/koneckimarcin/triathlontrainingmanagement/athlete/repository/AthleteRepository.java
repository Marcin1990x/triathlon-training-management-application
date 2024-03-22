package pl.koneckimarcin.triathlontrainingmanagement.athlete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteEntity;

import java.util.List;

public interface AthleteRepository extends JpaRepository <AthleteEntity, Long> {

    public List<AthleteEntity> findByLastNameContainingIgnoreCase(String lastName);
}
