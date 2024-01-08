package pl.koneckimarcin.triathlontrainingmanagement;

import org.springframework.web.bind.annotation.*;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.Athlete;
import pl.koneckimarcin.triathlontrainingmanagement.athlete.AthleteEntity;

import java.util.List;

public interface EntityController <Entity, MappedEntity> {

    @GetMapping
    public List<MappedEntity> getAll();

    @GetMapping("/{id}")
    public Entity getById(@PathVariable long id);

    @PostMapping
    public MappedEntity addNew(@RequestBody MappedEntity mappedEntity);

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id);
}
