package pl.koneckimarcin.triathlontrainingmanagement.coach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.koneckimarcin.triathlontrainingmanagement.EntityController;

import java.util.List;

@RestController
@RequestMapping("/coach")
public class CoachController implements EntityController<CoachEntity, Coach> {

    @Autowired
    private CoachService coachService;

    @Override
    public List<Coach> getAll() {
        return coachService.getAll();
    }

    @Override
    public CoachEntity getById(long id) {
        return coachService.findById(id);
    }

    @Override
    public Coach addNew(Coach coach) {

        return coachService.addNew(coach);
    }

    @Override
    public void deleteById(long id) {

        coachService.deleteById(id);
    }
}
