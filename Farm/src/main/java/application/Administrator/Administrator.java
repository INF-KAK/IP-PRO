package application.Administrator;

import application.Field;
import application.Mediator.RequestHandler;
import application.Silo.Silo;
import application.Silo.SiloRepository;
import application.User.User;
import application.User.UserRepository;

import java.util.List;
import java.util.Optional;

public class Administrator extends User {

    private Administrator(String name, String lastName, String phoneNumber, List<Field> fields, List<Role> roles) {

        super(name, lastName, phoneNumber, fields, roles);
    }

    public static Administrator create(User user) throws Exception {
        Administrator admin = new Administrator(user.getName(), user.getLastName(), user.getPhoneNumber(), user.getFields(), user.getRoles());

        if(!admin.getRoles().contains(Role.ADMIN)) {
            throw new Exception("You do not have permission!");
        }

        return admin;
    }

    private Administrator(String name, String lastName, String phoneNumber, List<Field> fields, String role) {

        super(name, lastName, phoneNumber, fields, role);
    }

    public static Administrator create2(User user) throws Exception {
        Administrator admin = new Administrator(user.getName(), user.getLastName(), user.getPhoneNumber(), user.getFields(), user.getRola());


        return admin;
    }

    public void removeUser(UserRepository repository, User user) {
        repository.remove(user);
    }
}