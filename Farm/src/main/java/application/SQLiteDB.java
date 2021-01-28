package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDB {
    Connection conn = null;
    public Connection DbConnect()
    {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:/Users/konradmichalski/Desktop/studies/semestr V/Projekt IP/Farm/database.db");
            System.out.println("Connected");
            return conn;
        }
        catch(Exception ex)
        {
            System.out.println("Error " + ex.getMessage());
            return null;
        }
    }
    public void DbDisconnect() throws SQLException {
        try
        {
            conn.close();
            System.out.println("Disconnected");
        }
        catch (Exception ex)
        {
            System.out.println("Error " + ex.getMessage());
        }
    }
}
