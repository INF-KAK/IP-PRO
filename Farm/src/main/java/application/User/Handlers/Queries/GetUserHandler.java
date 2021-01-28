package application.User.Handlers.Queries;

import application.Mediator.RequestHandler;
import application.User.User;
import application.User.UserFactory;
import application.User.UserRepository;

import java.util.Optional;

public class GetUserHandler implements RequestHandler<GetUserQuery, User> {
    UserRepository repository;

    public GetUserHandler(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User handle(GetUserQuery getUserQuery) throws Exception {
        Optional<User> user = repository.getByName(getUserQuery.name);

        if(!user.isPresent()) {
            throw new Exception("User does not exists");
        }

        return new UserFactory().getUser(user.get());
    }
}