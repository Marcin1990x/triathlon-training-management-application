package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericStageRepository <T> extends JpaRepository <T, Long> {
}
