package UserTests;
import application.Field;
import application.User.User;
import application.User.UserFactory;

import java.util.Collections;
import java.util.List;

public class UserBuilder {
    private String name = "Jan";
    private String lastName = "Kowalski";
    private String phoneNumber = "+48999999999";
    private List<Field> fields = Collections.emptyList();
    private List<User.Role> roles = Collections.emptyList();

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserBuilder setFields(List<Field> fields) {
        this.fields = fields;
        return this;
    }

    public UserBuilder setRoles(List<User.Role> roles) {
        this.roles = roles;
        return this;
    }

    public User build() throws Exception {
        User user = User.create(name, lastName, phoneNumber, fields, roles);
        return new UserFactory().getUser(user);
    }
}
