package application.User;

import java.util.List;

public class User {
    public enum Role {
        ADMIN, OWNER, EMPLOYEE
    }

    protected String name;
    protected String lastName;
    protected String phoneNumber;
    protected List<Field> field;
    protected List<Role> roles;

    public User(String name, String lastName, String phoneNumber, List<Field> field, List<Role> roles) {
        this.name = name.trim();
        this.lastName = lastName.trim();
        this.phoneNumber = phoneNumber.trim();
        this.field = field;
        this.roles = roles;
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

    public List<Field> getField() {
        return field;
    }

    public List<Role> getRoles() { return roles; }

    public static User create(String name, String lastName,
                              String phoneNumber, List<Field> fields, List<Role> roles) throws Exception {
        User user = new User(name, lastName, phoneNumber, fields, roles);

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