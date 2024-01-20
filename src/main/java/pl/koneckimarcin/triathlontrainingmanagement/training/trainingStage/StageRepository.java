package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StageRepository extends JpaRepository <StageEntity, Long> {
}
