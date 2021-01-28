package application.User.Handlers.Commands;

import application.Mediator.RequestHandler;
import application.User.Report;
import application.User.ReportRepository;

public class SendReportHandler implements RequestHandler<SendReportCommand, String> {
    private ReportRepository repository;

    public SendReportHandler(ReportRepository repository) {
        this.repository = repository;
    }

    @Override
    public String handle(SendReportCommand sendReportCommand) throws Exception {
        Report report = new Report(sendReportCommand.sender, sendReportCommand.receiver, sendReportCommand.text);
        repository.sendReport(report);
        return report.getText();
    }
}