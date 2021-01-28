package application.User.Handlers.Queries;

import application.Mediator.RequestHandler;
import application.User.Report;
import application.User.ReportRepository;

import java.util.List;

public class GetReportHandler implements RequestHandler<GetReportQuery, List<Report>> {
    private ReportRepository repository;

    public GetReportHandler(ReportRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Report> handle(GetReportQuery getReportQuery) throws Exception {
        return repository.getReports(getReportQuery.sender, getReportQuery.receiver);
    }
}

