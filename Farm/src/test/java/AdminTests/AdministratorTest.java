package AdminTests;

import application.Administrator.Administrator;
import application.Field;
import application.User.User;
import application.User.UserRepo;
import application.User.UserRepository;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AdministratorTest {
    UserRepository repository = new UserRepo();

    @Test
    public void shouldCreateAdministrator() throws Exception {
        //act
        List<Field> fields = Arrays.asList(new Field("Kielce"), new Field("Ruda"));
        List<User.Role> roles = Collections.singletonList(User.Role.ADMIN);

        Administrator admin = Administrator.create(User.create("Jan", "Nowak", "+48111222333", fields, roles));

        //assert
        assertEquals("Jan", admin.getName());
        assertEquals("Nowak", admin.getLastName());
        assertEquals("+48111222333", admin.getPhoneNumber());
        assertEquals(2, (long) admin.getFields().size());
        assertEquals(fields, admin.getFields());
        assertEquals(roles, admin.getRoles());
    }

    @Test
    public void shouldRemoveUser() throws Exception {
        User user = User.create("Jan", "Nowak", "+48111222333", null, null);
        repository.add(user);

        Administrator admin = Administrator.create(User.create("admin", "admin", "+48121212121", null, Collections.singletonList(User.Role.ADMIN)));

        admin.removeUser(repository, user);

        assertEquals(0, repository.getAllUsers().size());
    }

    @Test
    public void shouldThrowExceptionWhenRolesNotContainsAdmin() {
        //assert
        Exception exception = assertThrows(Exception.class, () ->
                Administrator.create(User.create("Jan", "Nowak", "+48111222333", null, Collections.singletonList(User.Role.EMPLOYEE))));

        assertEquals("You do not have permission!", exception.getMessage());
    }
}