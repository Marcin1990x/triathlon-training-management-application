package pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealizationStrava;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRealizationStravaRepository extends JpaRepository<TrainingRealizationStravaEntity, Long> {

}
