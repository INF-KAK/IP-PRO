package application;

import application.User.Report;

import java.sql.SQLException;
import java.util.List;

public class GenerateReport {
    private static Report getReport(int id) throws SQLException {
        dbQuery db = new dbQuery();
        List<Report> reports = db.GetAllReports();
        for (Report report : reports){
            if(id == report.getSenderR()) {
                return report;
            }
        }
        return null;
    }

    public static String execute(int id) throws SQLException{
        Report report = getReport(id);

        if(report != null)
        {
            return "Raport zostal wygenerowany przez Wlasciciela o id : " + report.getSenderR();
        }
        return null;
    }
}
