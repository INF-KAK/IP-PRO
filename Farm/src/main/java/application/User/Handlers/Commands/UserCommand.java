package application.User.Handlers.Commands;

import application.Field;
import application.User.User;

import java.util.List;

public class UserCommand {
    public String name;
    public String lastName;
    public String phoneNumber;
    public List<Field> fields;
    public List<User.Role> roles;
}
