import application.FarmSystem;
import application.Field;
import application.Mediator.Mediator;
import application.Silo.GetAllSilosQuery;
import application.Silo.Silo;
import application.Silo.SiloRepository;
import application.User.ReportRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GetFieldTest {
    List<Field> fields = new ArrayList<>();
    @Test
    public void shouldFindField() throws Exception{
        //arrange
        Field field1 = Field.create("Ruda","Miejska",40);

        Field field2 = Field.create("Strawczyn","Wiejska",20);


        fields.add(field1);
        fields.add(field2);

        //assert
        assertEquals("Ruda", field1.getCity());
        assertEquals("Miejska", field1.getStreet());
        assertEquals(40, field1.getNumber());

        assertEquals("Strawczyn", field2.getCity());
        assertEquals("Wiejska", field2.getStreet());
        assertEquals(20, field2.getNumber());
    }

    @Test
    public void shouldNotFindAnyField() throws Exception {
        List<Field> query = fields;

        //assert
        assertEquals(query.size(), 0);
    }
}

