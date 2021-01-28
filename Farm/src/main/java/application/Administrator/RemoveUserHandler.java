package application.Administrator;

import application.Mediator.RequestHandler;
import application.User.User;
import application.User.UserRepository;

import java.util.Optional;

public class RemoveUserHandler implements RequestHandler<RemoveUserCommand, String> {
    UserRepository repository;

    public RemoveUserHandler(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public String handle(RemoveUserCommand removeUserCommand) throws Exception {
        Administrator admin = Administrator.create(removeUserCommand.admin);
        Optional<User> user = repository.getByName(removeUserCommand.removedUser.getName());

        if(!user.isPresent()) {
            return "User was not found!";
        }

        admin.removeUser(repository, user.get());
        return "User removed successfully";
    }
}