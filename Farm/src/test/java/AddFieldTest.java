import application.Field;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddFieldTest {
    @Test
    public void shouldCreateTask() throws Exception{
        //arrange
        Field field = Field.create("Kielce","Wiejska",20);

        //assert
        assertEquals("Kielce",field.getCity());
        assertEquals("Wiejska",field.getStreet());
        assertEquals(20,field.getNumber());
    }

    @Test
    public void shouldThrowExceptionWhenCityIsNull() throws Exception {
        //arrange
        Field field = Field.create(null,"Wiejska",20);


        //assert
        Assert.assertNull("City is null!", field.getCity());
    }
}
