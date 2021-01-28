package application.User;
import application.Field;
import application.User.Handlers.Commands.AddUserCommand;
import application.User.Handlers.Commands.UpdateUserCommand;
import application.Validation;

import java.util.List;

public class User {

    public enum Role {
        ADMIN, OWNER, EMPLOYEE;

        public String toString() {
            switch (this) {
                case ADMIN:
                    return "ADMIN";
                case OWNER:
                    return "OWNER";
                case EMPLOYEE:
                    return "EMPLOYEE";
            }
            return null;
        }
    }
    protected int id;
    protected String name;
    protected String lastName;
    protected String phoneNumber;
    protected List<Field> fields;
    protected List<Role> roles;

    protected String rola;
    protected String field1;
    protected String field2;

    public User() {}

    public User(String name, String lastName, String phoneNumber, List<Field> fields, List<Role> roles) {
        this.name = name.trim();
        this.lastName = lastName.trim();
        this.phoneNumber = phoneNumber.trim();
        this.fields = fields;
        this.roles = roles;
    }

    public User(String name, String lastName, String phoneNumber, List<Field> fields, String rola) {
        this.name = name.trim();
        this.lastName = lastName.trim();
        this.phoneNumber = phoneNumber.trim();
        this.fields = fields;
        this.rola = rola;
    }

    public User(String rola, String name, String lastName, String phoneNumber, String field1, String field2) {
        this.rola = rola;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.field1 = field1;
        this.field2 = field2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRola() {
        return rola;
    }

    public void setRola(String rola) {
        this.rola = rola;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Field> getFields() {
        return fields;
    }

    public List<Role> getRoles() { return roles; }

    public static User create1(String name, String lastName, String phoneNumber, List<Field> fields, String role) throws Exception {
        User user = new User(name, lastName, phoneNumber, fields, role);

        UserValidation validator = new UserValidation(user);
        validator.validateOrThrow();

        return user;
    }

    public static User create(String name, String lastName, String phoneNumber, List<Field> fields, List<Role> roles) throws Exception {
        User user = new User(name, lastName, phoneNumber, fields, roles);

        UserValidation validator = new UserValidation(user);
        validator.validateOrThrow();

        return user;
    }

    public static User create(String role, String name, String lastName, String phoneNumber, String pole1, String pole2) throws Exception {
        User user = new User(role, name, lastName, phoneNumber, pole1, pole2);

        UserValidation validator = new UserValidation(user);
        validator.validateOrThrow();

        return user;
    }


    public static User create(AddUserCommand command) throws Exception {
        User user = new User(command.name, command.lastName, command.phoneNumber, command.fields, command.roles);

        new UserValidation(user).validateOrThrow();

        return user;
    }

    public static User update(UpdateUserCommand command) throws Exception {
        User user = new User("", "", command.phoneNumber, command.fields, command.roles);
        new UserValidation(user).validateOrThrowOnUpdate();

        return user;
    }
}

class UserValidation extends Validation<User> {
    public UserValidation(User user) {
        super(user);
    }

    public void validateOrThrowOnUpdate() throws Exception {
        String number = validatedObject.getPhoneNumber();
        if(!number.isEmpty()) {
            if(!phoneNumberIsCorrect(number)) {
                throw new Exception("Phone number is invalid!");
            }
        }
    }

    @Override
    public boolean isValid() {
        User user = validatedObject;
        boolean nameIsValid = stringContainsOnlyLetters(user.getName());
        boolean lastNameIsValid = stringContainsOnlyLetters(user.getLastName());
        boolean phoneNumberIsValid = phoneNumberIsCorrect(user.getPhoneNumber());

        return nameIsValid && lastNameIsValid && phoneNumberIsValid;
    }
}