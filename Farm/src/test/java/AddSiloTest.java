import application.Silo.Silo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddSiloTest {
    @Test
    public void shouldCreateSilo() throws Exception{
        //arrange
        Silo silo = Silo.create("SilosTest","2500","Strawczyn");



        //assert
        assertEquals("SilosTest",silo.getName());
        assertEquals("2500",silo.getCapacity());
        assertEquals("Strawczyn",silo.getFieldName());
    }

    @Test
    public void shouldThrowExceptionWhenFieldNameIsNull(){
        //arrange
        Silo silo = Silo.create("SilosTest","2500",null);


        //assert
        Assert.assertNull("Field Name is null!", silo.getFieldName());
    }
}
