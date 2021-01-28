package UserTests;

import application.Field;
import application.User.User;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest {
    @Test
    public void shouldCreateUser() throws Exception {
        //act
        List<Field> fields = Arrays.asList(new Field("Kielce"), new Field("Strawczyn"));
        List<User.Role> roles = Collections.singletonList(User.Role.ADMIN);
        User user = User.create("Jan", "Kowalski", "+48999999999", fields, roles);

        //assert
        assertEquals("Jan", user.getName());
        assertEquals("Kowalski", user.getLastName());
        assertEquals("+48999999999", user.getPhoneNumber());
        assertEquals(2, (long) user.getFields().size());
        assertEquals(fields, user.getFields());
        assertEquals(roles, user.getRoles());
    }

    @Test
    public void shouldThrowExceptionWhenUserInvalid() {
        //assert
        Exception exception = assertThrows(Exception.class, () ->
                User.create("Jan1", "Kow@l!", "notphone", null, null));

        assertEquals("User is invalid!", exception.getMessage());
    }
}
