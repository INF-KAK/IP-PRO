import application.FarmSystem;
import application.Mediator.Mediator;
import application.Silo.GetAllSilosQuery;
import application.Silo.Silo;
import application.Silo.SiloRepository;
import application.User.ReportRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GetSiloTest {
    SiloRepository repository = new SiloRepositoryTest();
    Mediator mediator = new Mediator(repository);

    @Test
    public void shouldFindSilo() throws Exception{
        //arrange
        Silo silo1 = Silo.create("SilosTest1","2500","Strawczyn");

        Silo silo2 = Silo.create("SilosTest2","3000","Ruda Strawczyn");


        //act
        GetAllSilosQuery query = new GetAllSilosQuery();
        List<Silo> silos = mediator.send(query);

        //assert
        assertEquals("SilosTest1",silo1.getName());
        assertEquals("2500",silo1.getCapacity());
        assertEquals("Strawczyn",silo1.getFieldName());

        assertEquals("SilosTest2",silo2.getName());
        assertEquals("3000",silo2.getCapacity());
        assertEquals("Ruda Strawczyn",silo2.getFieldName());
    }

    @Test
    public void shouldNotFindAnySilos() throws Exception {
        GetAllSilosQuery query = new GetAllSilosQuery();

        //act
        List<Silo> silos = mediator.send(query);

        //assert
        assertEquals(silos.size(), 0);
    }
}
