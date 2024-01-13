package pl.koneckimarcin.triathlontrainingmanagement.training.trainingRealization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRealizationRepository extends JpaRepository <TrainingRealizationEntity, Long> {
}
