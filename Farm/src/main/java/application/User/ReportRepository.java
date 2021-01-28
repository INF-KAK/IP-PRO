package application.User;

import java.util.List;

public interface ReportRepository {
    void sendReport(Report report);
    List<Report> getReports(User sender, User receiver);
}
