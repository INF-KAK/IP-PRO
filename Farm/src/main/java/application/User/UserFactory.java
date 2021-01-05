package application.User;

import application.Administrator.Administrator;
import application.Event.EventOrganizer;
import application.Exhibition.Contractor;

public class UserFactory {
    public User getUser(User user) throws Exception {
        if(user.getRoles().isEmpty()) {
            return user;
        }

        return user.getRoles().contains(User.Role.ADMIN) ?
                Administrator.create(user) :
                user.getRoles().contains(User.Role.OWNER) ? Owner.create(user): user;
    }
}
