package application.User.Handlers.Commands;

public class AddUserCommand extends UserCommand {
    public AddUserCommand() {}

    public AddUserCommand(UpdateUserCommand command) {
        this.name = command.name;
        this.lastName = command.lastName;
        this.phoneNumber = command.phoneNumber;
        this.locations = command.locations;
    }
}
