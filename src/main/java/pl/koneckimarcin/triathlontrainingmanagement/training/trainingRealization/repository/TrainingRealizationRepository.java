package pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization.TrainingRealizationEntity;

@Repository
public interface TrainingRealizationRepository extends JpaRepository<TrainingRealizationEntity, Long> {

}
