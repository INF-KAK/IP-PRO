package application.User;

import java.util.List;

public interface ReportRepository {
    List<Report> getReports(User user);
}
