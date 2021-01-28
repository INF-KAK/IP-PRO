import application.FarmSystem;
import application.Mediator.Mediator;
import application.Silo.GetAllSilosQuery;
import application.Silo.Silo;
import application.Silo.SiloRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FreeSpaceSiloTest {
    FarmSystem fm = new FarmSystem();

    @Test
    public void shouldReturnTrueSilo() throws Exception{
        //arrange
        Silo silo = Silo.create1("SilosTest",100);


        assertEquals("SilosTest",silo.getName());

        assertEquals(100,fm.ResultSilo(silo));
    }

    @Test
    public void shouldNotFindFreeSpace() throws Exception {

        Silo silo = Silo.create1("SilosTest",0);



        assertEquals(fm.ResultSilo(silo), 0);
    }
}
