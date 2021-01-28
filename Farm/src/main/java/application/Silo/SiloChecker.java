package application.Silo;

import java.sql.SQLException;

public interface SiloChecker {
    public int checkSilo(int wheat, Silo s) throws SQLException;
}
