package application.Owner;

import application.Field;
import application.Silo.Silo;
import application.Silo.SiloRepository;
import application.User.User;
import application.User.UserRepository;

import java.util.List;

public class Owner extends User {

    private Owner(String name, String lastName, String phoneNumber, List<Field> fields, List<Role> roles) {

        super(name, lastName, phoneNumber, fields, roles);
    }

    private Owner(String type, String name, String lastName, String phoneNumber, String pole1, String pole2) {

        super(type, name, lastName, phoneNumber, pole1, pole2);
    }

    private Owner(String name, String lastName, String phoneNumber, List<Field> fields, String rola) {

        super(name, lastName, phoneNumber, fields, rola);
    }

    public static Owner create2(User user) throws Exception {
        Owner owner = new Owner(user.getName(), user.getLastName(),
                user.getPhoneNumber(), user.getFields(), user.getRola());

        return owner;
    }

    public static Owner create(User user) throws Exception {
        Owner owner = new Owner(user.getName(), user.getLastName(),
                user.getPhoneNumber(), user.getFields(), user.getRoles());

        if(!owner.getRoles().contains(Role.OWNER)) {
            throw new Exception("You do not have permission!");
        }

        return owner;
    }

    public static Owner create1(User user) throws Exception {
        Owner owner = new Owner(user.getRola(), user.getName(), user.getLastName(),
                user.getPhoneNumber(), user.getField1() , user.getField2());

        if(!owner.getRoles().contains(Role.OWNER)) {
            throw new Exception("You do not have permission!");
        }

        return owner;
    }

    public void removeUser(UserRepository repository, User user) {
        repository.remove(user);
    }
}
