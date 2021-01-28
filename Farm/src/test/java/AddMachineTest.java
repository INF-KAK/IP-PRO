import application.Machine.Machine;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddMachineTest {
    @Test
    public void shouldCreateTask() throws Exception{
        //arrange
        Machine machine = Machine.create("Ursus");

        //assert
        assertEquals("Ursus",machine.getName());
    }

    @Test
    public void shouldThrowExceptionWhenMachineNameIsNull(){
        //arrange
        Machine machine = Machine.create(null);

        //assert
        Assert.assertNull("Machine Name is null!", machine.getName());
    }
}

