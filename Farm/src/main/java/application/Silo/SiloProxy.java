package application.Silo;

import application.dbQuery;

import java.sql.SQLException;
import java.util.List;

public class SiloProxy implements SiloChecker{
    private Silo realSilo;
    public SiloProxy(){
        realSilo = new Silo();
    }
    public int checkSilo(int wheat, Silo s) throws SQLException{
        if(wheat < 0 || s == null || s.getName() == null) {
            return 0;
        }
        return realSilo.checkSilo(wheat, s);
    }

}