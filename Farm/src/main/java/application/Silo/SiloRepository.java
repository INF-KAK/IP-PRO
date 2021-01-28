package application.Silo;

import application.Field;

import java.util.List;
import java.util.Optional;

public interface SiloRepository {
    void add(Silo silo);
    void remove(Silo silo);
    List<Silo> getAllSilos();
}
