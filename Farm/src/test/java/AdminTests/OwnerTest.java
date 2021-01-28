package AdminTests;

import application.Field;
import application.Owner.Owner;
import application.User.User;
import application.User.UserRepo;
import application.User.UserRepository;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OwnerTest {
    UserRepository repository = new UserRepo();

    @Test
    public void shouldCreateOwner() throws Exception {
        //act
        List<Field> fields = Arrays.asList(new Field("Borki"), new Field("Ruda"));
        List<User.Role> roles = Collections.singletonList(User.Role.OWNER);

        Owner owner = Owner.create(User.create("Marek", "Kowalski", "+48111222333", fields, roles));

        //assert
        assertEquals("Marek", owner.getName());
        assertEquals("Kowalski", owner.getLastName());
        assertEquals("+48111222333", owner.getPhoneNumber());
        assertEquals(2, (long) owner.getFields().size());
        assertEquals(fields, owner.getFields());
        assertEquals(roles, owner.getRoles());
    }

    @Test
    public void shouldRemoveOwner() throws Exception {
        User user = User.create("Marek", "Kowalski", "+48111222333", null, null);
        repository.add(user);

        Owner owner = Owner.create(User.create("wlasciciel", "wlasciciel", "+48121212121", null, Collections.singletonList(User.Role.OWNER)));

        owner.removeUser(repository, user);

        assertEquals(0, repository.getAllUsers().size());
    }

    @Test
    public void shouldThrowExceptionWhenRolesNotContainsOwner() {
        //assert
        Exception exception = assertThrows(Exception.class, () ->
                Owner.create(User.create("Marek", "Kowalski", "+48111222333", null, Collections.singletonList(User.Role.EMPLOYEE))));

        assertEquals("You do not have permission!", exception.getMessage());
    }
}