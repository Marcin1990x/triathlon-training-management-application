package pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.bike;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.triathlontrainingmanagement.training.trainingStage.GenericStageController;

@RestController
@RequestMapping("/bike")
public class BikeStageController extends GenericStageController<BikeStage> {

}
