import application.Field;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class FieldTest {
    @Test
    public void shouldCreateField() throws Exception {
        //act
        Field field = Field.create("Kielce", "Sienkiewicza", 16);

        //assert
        assertEquals("Kielce", field.getCity());
        assertEquals("Sienkiewicza", field.getStreet());
        assertEquals(16, field.getNumber());
    }

    @Test
    public void shouldThrowExceptionWhenFieldInvalid() {
        //assert
        Exception exception = assertThrows(Exception.class, () ->
                Field.create("Kielce3", "Sienkie@@wicza", 101));

        assertEquals("Field is invalid!", exception.getMessage());
    }
}