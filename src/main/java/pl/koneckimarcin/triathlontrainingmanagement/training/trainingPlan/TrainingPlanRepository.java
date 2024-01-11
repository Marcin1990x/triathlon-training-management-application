package pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingPlanRepository extends JpaRepository<TrainingPlanEntity, Long> {

    List<TrainingPlanEntity> findByTrainingType(TrainingType trainingType);
}
