package application.Mediator;

import application.User.Handlers.Commands.*;
import application.User.Handlers.Queries.GetUserHandler;
import application.User.Handlers.Queries.GetUserQuery;
import application.User.Report;
import application.User.ReportRepository;
import application.User.User;
import application.User.UserRepository;

import java.util.List;

public class Mediator {
    private UserRepository userRepository;

    private GetUserHandler getUserHandler;
    private AddUserHandler addUserHandler;
    private UpdateUserHandler updateUserHandler;

    public Mediator(UserRepository userRepository) {
        this.userRepository = userRepository;

        getUserHandler = new GetUserHandler(userRepository);
        addUserHandler = new AddUserHandler(userRepository);
        updateUserHandler = new UpdateUserHandler(userRepository);
    }

}