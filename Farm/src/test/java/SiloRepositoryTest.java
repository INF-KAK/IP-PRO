import application.Silo.Silo;
import application.Silo.SiloRepository;

import java.util.LinkedList;
import java.util.List;

public class SiloRepositoryTest implements SiloRepository {
    private List<Silo> silos = new LinkedList<>();

    @Override
    public void add(Silo silo) {
        silos.add(silo);
    }

    @Override
    public void remove(Silo silo) {
        silos.remove(silo);
    }

    @Override
    public List<Silo> getAllSilos() {
        return silos;
    }
}
