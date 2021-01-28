package AdminTests;

import application.Employee.Employee;
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

public class EmployeeTest {
    UserRepository repository = new UserRepo();

    @Test
    public void shouldCreateOwner() throws Exception {
        //act
        List<Field> fields = Arrays.asList(new Field("Promnik"), new Field("Oblegor"));
        List<User.Role> roles = Collections.singletonList(User.Role.EMPLOYEE);

        Employee employee = Employee.create(User.create("Marian", "Kowalski", "+48111222333", fields, roles));

        //assert
        assertEquals("Marian", employee.getName());
        assertEquals("Kowalski", employee.getLastName());
        assertEquals("+48111222333", employee.getPhoneNumber());
        assertEquals(2, (long) employee.getFields().size());
        assertEquals(fields, employee.getFields());
        assertEquals(roles, employee.getRoles());
    }

    @Test
    public void shouldRemoveOwner() throws Exception {
        User user = User.create("Marian", "Kowalski", "+48111222333", null, null);
        repository.add(user);

        Employee employee = Employee.create(User.create("pracownik", "pracownik", "+48121212121", null, Collections.singletonList(User.Role.EMPLOYEE)));

        employee.removeUser(repository, user);

        assertEquals(0, repository.getAllUsers().size());
    }

    @Test
    public void shouldThrowExceptionWhenRolesNotContainsEmployee() {
        //assert
        Exception exception = assertThrows(Exception.class, () ->
                Owner.create(User.create("Marian", "Kowalski", "+48111222333", null, Collections.singletonList(User.Role.EMPLOYEE))));

        assertEquals("You do not have permission!", exception.getMessage());
    }
}