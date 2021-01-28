import UserTests.UserBuilder;
import application.Administrator.Administrator;
import application.Field;
import application.Mediator.Mediator;
import application.User.Handlers.Queries.GetUserQuery;
import application.User.User;
import application.User.UserFactory;
import application.User.UserRepo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GetUserHandlerTest {
    UserRepo repository = new UserRepo();
    Mediator mediator = new Mediator(repository);

    @Test
    public void shouldCreateUser() throws Exception {
        //arrange
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

    @Test()
    public void shouldThrowExceptionWhenTryingCastToInvalidRoleClass() throws Exception {
        List<User.Role> roles = Collections.singletonList(User.Role.EMPLOYEE);
        repository.add(new UserBuilder().setRoles(roles).build());

        //act
        GetUserQuery getUserQuery = new GetUserQuery();
        getUserQuery.name = "Jan";

        User user = mediator.send(getUserQuery);

        //assert
        assertThrows(ClassCastException.class, () -> {
            Administrator user2 = (Administrator) new UserFactory().getUser(user);
        });
    }
}
