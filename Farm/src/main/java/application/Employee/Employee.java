package application.Employee;

import application.Field;
import application.Silo.Silo;
import application.Silo.SiloRepository;
import application.User.User;
import application.User.UserRepository;

import java.util.List;

public class Employee extends User {

    private Employee(String name, String lastName, String email,
                          String phoneNumber, List<Field> fields, List<Role> roles) {

        super(name, lastName, phoneNumber, fields, roles);
    }

    public void removeSilo(SiloRepository repository, Silo silo) { repository.remove(silo); }
}
