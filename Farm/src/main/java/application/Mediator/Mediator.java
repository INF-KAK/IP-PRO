package application.Mediator;

import application.Administrator.GetUsersHandler;
import application.Administrator.GetUsersQuery;
import application.Field;
import application.Silo.GetAllSilosHandler;
import application.Silo.GetAllSilosQuery;
import application.Silo.Silo;
import application.Silo.SiloRepository;
import application.User.*;
import application.User.Handlers.Commands.*;
import application.User.Handlers.Queries.GetReportHandler;
import application.User.Handlers.Queries.GetUserHandler;
import application.User.Handlers.Queries.GetUserQuery;

import java.util.List;

public class Mediator {
    private UserRepository userRepository;
    private ReportRepository reportRepository;


    private GetUserHandler getUserHandler;
    private AddUserHandler addUserHandler;
    private GetUsersHandler getUsersHandler;
    private GetAllSilosHandler getAllSilosHandler;
    private SiloRepository siloRepository;
    private Field field;

    private SendReportHandler sendReportHandler;
    private GetReportHandler getReportHandler;

    public Mediator(UserRepository userRepository) {
        this.userRepository = userRepository;


        getUserHandler = new GetUserHandler(userRepository);
        addUserHandler = new AddUserHandler(userRepository);
    }

    public Mediator(SiloRepository siloRepository) {
        this.siloRepository = siloRepository;
        getAllSilosHandler = new GetAllSilosHandler(siloRepository);
    }

    public Mediator(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;

        sendReportHandler = new SendReportHandler(reportRepository);
        getReportHandler = new GetReportHandler(reportRepository);
    }

    public User send(GetUserQuery query) throws Exception {
        return getUserHandler.handle(query);
    }

    public void send(AddUserCommand command) throws Exception {
        addUserHandler.handle(command);
    }

    public List<User> send(GetUsersQuery query) throws Exception {
        return getUsersHandler.handle(query);
    }

    public List<Silo> send(GetAllSilosQuery query) throws Exception{
        return getAllSilosHandler.handle(query);
    }

    public void send(SendReportCommand command) throws Exception {
        sendReportHandler.handle(command);
    }

}