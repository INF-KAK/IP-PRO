package application.Silo;

import application.Field;

import java.util.List;
import java.util.Optional;

public interface SiloRepository {
    void add(Silo silo);
    void remove(Silo silo);
    void update(Silo oldSilo, Silo newSilo) throws Exception;
    Optional<Silo> getByName(String name);
    List<Silo> getByField(Field field);
    List<Silo> getAllSilo();
}
