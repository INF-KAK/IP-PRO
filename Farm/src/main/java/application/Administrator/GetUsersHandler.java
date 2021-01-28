package application.Administrator;

import application.Mediator.RequestHandler;
import application.User.User;
import application.User.UserRepository;

import java.util.List;

public class GetUsersHandler implements RequestHandler<GetUsersQuery, List<User>> {
    UserRepository repository;

    public GetUsersHandler(UserRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<User> handle(GetUsersQuery getUsersQuery) throws Exception {
        if(getUsersQuery.user.getRoles().contains(User.Role.ADMIN)) {
            return repository.getAllUsers();
        } else {
            throw new Exception("You do not have permission!");
        }
    }
}
