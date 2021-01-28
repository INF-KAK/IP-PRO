package application.User.Handlers.Commands;

import application.Mediator.RequestHandler;
import application.User.User;
import application.User.UserRepository;

import java.util.Optional;

public class UpdateUserHandler implements RequestHandler<UpdateUserCommand, User> {
    private UserRepository repository;

    public UpdateUserHandler(UserRepository repository) {
        this.repository = repository;
    }

    User user;

    @Override
    public User handle(UpdateUserCommand userCommand) throws Exception {
        Optional<User> userExists = repository.getByName(userCommand.name);

        User user;

        if (userExists.isPresent()) {
            user = User.update(userCommand);
            repository.update(user);
        } else {
            user = User.create(new AddUserCommand(userCommand));
            repository.add(user);
        }

        return user;
    }
}