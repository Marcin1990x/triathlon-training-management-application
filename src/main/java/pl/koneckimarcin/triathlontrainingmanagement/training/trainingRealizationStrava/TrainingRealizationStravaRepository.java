package pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealizationStrava;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRealizationStravaRepository extends JpaRepository<TrainingRealizationStravaEntity, Long> {

    List<TrainingRealizationStravaEntity> findByAthleteId(Long athleteId);

}
