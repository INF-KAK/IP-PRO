package application.Owner;

import application.Field;
import application.Silo.Silo;
import application.Silo.SiloRepository;
import application.User.User;
import application.User.UserRepository;

import java.util.List;

public class Owner extends User {

    private Owner(String name, String lastName, String email,
                          String phoneNumber, List<Field> fields, List<Role> roles) {

        super(name, lastName, phoneNumber, fields, roles);
    }

    public static Owner create(User user) throws Exception {
        Owner owner = new Owner(user.getName(), user.getLastName(),
                user.getPhoneNumber(), user.getFields(), user.getRoles());

        if(!admin.getRoles().contains(Role.OWNER)) {
            throw new Exception("You do not have permission!");
        }

        return owner;
    }

    public void removeUser(UserRepository repository, User user) {
        repository.remove(user);
    }

    public void removeSilo(SiloRepository repository, Silo silo) { repository.remove(silo); }
}
