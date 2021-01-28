package application.User.Handlers.Commands;

import application.Mediator.RequestHandler;
import application.User.User;
import application.User.UserRepository;

import java.util.Optional;

public class AddUserHandler implements RequestHandler<AddUserCommand, User> {
    private UserRepository repository;

    public AddUserHandler(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User handle(AddUserCommand addUserCommand) throws Exception {
        if(addUserCommand.fields.size() <= 0) {
            throw new Exception("Niepoprawna wartosc pol");
        }
        Optional<User> userExists = repository.getByName(addUserCommand.name);

        if(userExists.isPresent()) {
            throw new Exception("User already exists!");
        }

        User user = User.create(addUserCommand);
        repository.add(user);

        return user;
    }
}
