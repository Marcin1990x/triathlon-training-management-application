package pl.koneckimarcin.triathlontrainingmanagement;

import java.util.List;

public interface EntityService <Entity, MappedObject> {

    public boolean checkIfIsNotNull(long id);

    public List<MappedObject> getAll();

    public Entity findById(long id);

    public MappedObject addNew(MappedObject mappedObject);

    public void deleteById(long id);
}
