package application.Employee;

import application.Administrator.Administrator;
import application.Field;
import application.Silo.Silo;
import application.Silo.SiloRepository;
import application.User.User;
import application.User.UserRepository;

import java.util.List;

public class Employee extends User {

    public Employee(String name, String lastName, String phoneNumber, List<Field> fields, List<Role> roles) {

        super(name, lastName, phoneNumber, fields, roles);
    }

    public Employee(String name, String lastName, String phoneNumber, List<Field> fields, String role) {

        super(name, lastName, phoneNumber, fields, role);
    }

    public static Employee create2(User user) throws Exception {
        Employee employee = new Employee(user.getName(), user.getLastName(), user.getPhoneNumber(), user.getFields(), user.getRola());

        return employee;
    }

    public static Employee create(User user) throws Exception {
        Employee employee = new Employee(user.getName(), user.getLastName(), user.getPhoneNumber(), user.getFields(), user.getRoles());

        if(!employee.getRoles().contains(Role.EMPLOYEE)) {
            throw new Exception("You do not have permission!");
        }

        return employee;
    }

    public void removeUser(UserRepository repository, User user) {
        repository.remove(user);
    }
}
