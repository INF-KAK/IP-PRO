import application.Field;
import application.Machine.Machine;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetMachineTest {
    List<Machine> machines = new ArrayList<>();
    @Test
    public void shouldFindMachine() throws Exception{
        //arrange
        Machine machine1 = Machine.create("Ursus");

        Machine machine2 = Machine.create("Zetor");

        machines.add(machine1);
        machines.add(machine2);

        //assert
        assertEquals("Ursus",machine1.getName());
        assertEquals("Zetor",machine2.getName());

    }

    @Test
    public void shouldNotFindAnyMachine() throws Exception {
        List<Machine> query = machines;

        //assert
        assertEquals(query.size(), 0);
    }
}