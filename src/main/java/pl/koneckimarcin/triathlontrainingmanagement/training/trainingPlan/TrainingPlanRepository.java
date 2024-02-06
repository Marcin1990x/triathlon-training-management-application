package pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingPlan.constant.TrainingType;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingPlanRepository extends JpaRepository<TrainingPlanEntity, Long> {

    List<TrainingPlanEntity> findByTrainingType(TrainingType trainingType);
}
