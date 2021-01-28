import UserTests.UserBuilder;
import application.Mediator.Mediator;
import application.User.Handlers.Commands.SendReportCommand;
import application.User.Handlers.Commands.SendReportHandler;
import application.User.Report;
import application.User.User;
import application.User.UserRepo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SendMessageHandlerTest {

    private User user1 = new UserBuilder().setName("Kowalski").build();
    private User user2 = new UserBuilder().setName("Michalski").build();

    private UserRepo repository = new UserRepo();
    private Mediator mediator = new Mediator(repository);

    public SendMessageHandlerTest() throws Exception {
    }


    @Test
    public void shouldSendMessage() throws Exception {
        //arrange
        SendReportCommand command = new SendReportCommand();
        command.receiver = user2;
        command.sender = user1;
        command.text = "Przykladowy tekst";


        Report report = Report.create(user1.getId(),user2.getId(),command.text);

        //assert
        Assert.assertEquals("Przykladowy tekst", report.getText());
    }
}