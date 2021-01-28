package application;

import application.Machine.Machine;
import application.Silo.Silo;
import application.Task.Task;
import application.Task.Work;
import application.User.Report;
import application.User.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class dbQuery {
    public void addNewUser(User user) throws SQLException{
        SQLiteDB connection = new SQLiteDB();
        try (Connection conn = connection.DbConnect()) {
            PreparedStatement statement = (PreparedStatement) conn.prepareStatement("INSERT INTO Uzytkownicy (rodzaj, imie, nazwisko, numer, pole1, pole2)" +
                    "VALUES (?, ?, ?, ?, ?, ?)");
            Field field0 = user.getFields().get(0);
            Field field1 = user.getFields().get(1);
            statement.setString(1, user.getRola());
            statement.setString(2, user.getName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getPhoneNumber());
            statement.setString(5, field0.getCity());
            statement.setString(6, field1.getCity());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            connection.DbDisconnect();
        }
    }

    public int CheckLogin (User user) throws SQLException {
        SQLiteDB connection = new SQLiteDB();
        Connection conn = connection.DbConnect();
        PreparedStatement statement = (PreparedStatement) conn.prepareStatement("SELECT * FROM Uzytkownicy WHERE imie = ? and nazwisko = ? ");
        statement.setString(1, user.getName());
        statement.setString(2, user.getLastName());
        ResultSet rs = statement.executeQuery();
        String imie = null;
        String nazwisko = null;
        int userId = 0;
        while(rs.next())
        {
            //userId = rs.getInt("id");
            imie = rs.getString("imie");
            nazwisko = rs.getString("nazwisko");
        }
        if (imie == null && nazwisko == null ) return 0;
        rs.close();
        connection.DbDisconnect();
        return 1;
    }

    public String GetName(int id) throws SQLException {
        SQLiteDB connection = new SQLiteDB();
        Connection conn = connection.DbConnect();
        PreparedStatement statement = (PreparedStatement) conn.prepareStatement("SELECT * FROM Uzytkownicy WHERE id = ? ");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        String imie = null;
        while(rs.next())
        {
            imie = rs.getString("imie");
        }
        if (imie == null) return null;
        rs.close();
        connection.DbDisconnect();
        return imie;
    }

    public String CheckRole (User user) throws SQLException {
        SQLiteDB connection = new SQLiteDB();
        Connection conn = connection.DbConnect();
        PreparedStatement statement = (PreparedStatement) conn.prepareStatement("SELECT * FROM Uzytkownicy WHERE imie = ? and nazwisko = ? ");
        statement.setString(1, user.getName());
        statement.setString(2, user.getLastName());
        ResultSet rs = statement.executeQuery();
        String role = null;
        while(rs.next())
        {
            role = rs.getString("rodzaj");
        }
        if (role == null ) return null;
        rs.close();
        connection.DbDisconnect();
        return role;
    }

    public void AddReport(User user, User sender, Report report) throws SQLException {
        SQLiteDB connection = new SQLiteDB();
        try (Connection conn = connection.DbConnect()) {
            PreparedStatement statement = (PreparedStatement) conn.prepareStatement("INSERT INTO Raport (id_uzytkownika,id_nadawcy,tresc)" +
                    "VALUES (?,?,?)");
            statement.setInt(1, user.getId());
            statement.setInt(2, sender.getId());
            statement.setString(3, report.getText());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            connection.DbDisconnect();
        }
    }

    public void DeleteUser(User user) throws SQLException{
        SQLiteDB connection = new SQLiteDB();
        Connection conn = connection.DbConnect();
        PreparedStatement statement = (PreparedStatement) conn.prepareStatement("DELETE FROM Uzytkownicy WHERE id = ? ");
        statement.setInt(1, user.getId());
        statement.executeUpdate();
        connection.DbDisconnect();
    }

    public int CheckID(User user) throws SQLException {
        SQLiteDB connection = new SQLiteDB();
        Connection conn = connection.DbConnect();
        PreparedStatement statement = (PreparedStatement) conn.prepareStatement("SELECT * FROM Uzytkownicy WHERE imie = ? and nazwisko = ? ");
        statement.setString(1, user.getName());
        statement.setString(2, user.getLastName());
        ResultSet rs = statement.executeQuery();
        String imie = null;
        String nazwisko = null;
        int userId = 0;
        while(rs.next())
        {
            userId = rs.getInt("id");
            imie = rs.getString("imie");
            nazwisko = rs.getString("nazwisko");
        }
        rs.close();
        connection.DbDisconnect();
        return userId;
    }

    public int SearchID(User user) throws SQLException {
        SQLiteDB connection = new SQLiteDB();
        Connection conn = connection.DbConnect();
        PreparedStatement statement = (PreparedStatement) conn.prepareStatement("SELECT * FROM Uzytkownicy WHERE id = ?");
        statement.setInt(1, user.getId());
        ResultSet rs = statement.executeQuery();
        int userId = 0;
        while(rs.next())
        {
            userId = rs.getInt("id");
        }
        rs.close();
        connection.DbDisconnect();
        return userId;
    }

    public List<User> GetAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        SQLiteDB connection = new SQLiteDB();
        Connection conn = connection.DbConnect();
        PreparedStatement statement = (PreparedStatement) conn.prepareStatement("SELECT id, rodzaj, imie, nazwisko, numer, pole1, pole2 FROM Uzytkownicy");
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setRola(rs.getString("rodzaj"));
            user.setName(rs.getString("imie"));
            user.setLastName(rs.getString("nazwisko"));
            user.setPhoneNumber(rs.getString("numer"));
            user.setField1(rs.getString("pole1"));
            user.setField2(rs.getString("pole2"));
            users.add(user);
        }
        rs.close();
        connection.DbDisconnect();
        return users;
    }

    public List<Report> GetAllReports() throws SQLException {
        List<Report> reports = new ArrayList<>();
        SQLiteDB connection = new SQLiteDB();
        Connection conn = connection.DbConnect();
        PreparedStatement statement = (PreparedStatement) conn.prepareStatement("SELECT id, id_uzytkownika, id_nadawcy, tresc FROM Raport");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Report report = new Report();
            report.setId(rs.getInt("id"));
            report.setReceiverR(rs.getInt("id_uzytkownika"));
            report.setSenderR(rs.getInt("id_nadawcy"));
            report.setText(rs.getString("tresc"));
            reports.add(report);
        }
        rs.close();
        connection.DbDisconnect();
        return reports;
    }

    public void AddNewTask(Task task) throws SQLException {
        SQLiteDB connection = new SQLiteDB();
        try (Connection conn = connection.DbConnect()) {
            PreparedStatement statement = (PreparedStatement) conn.prepareStatement("INSERT INTO Zadania (nazwa)" +
                    "VALUES (?)");
            statement.setString(1, task.getNazwa());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            connection.DbDisconnect();
        }
    }

    public List<Task> GetAllTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        SQLiteDB connection = new SQLiteDB();
        Connection conn = connection.DbConnect();
        PreparedStatement statement = (PreparedStatement) conn.prepareStatement("SELECT id, nazwa FROM Zadania");
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            Task task = new Task();
            task.setId(rs.getInt("id"));
            task.setNazwa(rs.getString("nazwa"));
            tasks.add(task);
        }
        rs.close();
        connection.DbDisconnect();
        return tasks;
    }

    public void AddNewWork(Work work) throws SQLException {
        SQLiteDB connection = new SQLiteDB();
        try (Connection conn = connection.DbConnect()) {
            PreparedStatement statement = (PreparedStatement) conn.prepareStatement("INSERT INTO Praca (id_zadanie, id_pracownika, id_maszyny, data_od, data_do)" +
                    "VALUES (?,?,?,?,?)");
            statement.setInt(1, work.getId_task());
            statement.setInt(2, work.getId_employee());
            statement.setInt(3, work.getId_machine());
            statement.setString(4, work.getDateFrom());
            statement.setString(5, work.getDateTo());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            connection.DbDisconnect();
        }
    }

    public List<Work> GetAllWorks() throws SQLException {
        List<Work> works = new ArrayList<>();
        SQLiteDB connection = new SQLiteDB();
        Connection conn = connection.DbConnect();
        PreparedStatement statement = (PreparedStatement) conn.prepareStatement("SELECT id, id_zadanie, id_pracownika, id_maszyny, data_od, data_do FROM Praca");
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            Work work = new Work();
            work.setId(rs.getInt("id"));
            work.setId_task(rs.getInt("id_zadanie"));
            work.setId_employee(rs.getInt("id_pracownika"));
            work.setId_machine(rs.getInt("id_maszyny"));
            work.setDateFrom(rs.getString("data_od"));
            work.setDateTo(rs.getString("data_do"));
            works.add(work);
        }
        rs.close();
        connection.DbDisconnect();
        return works;
    }

    public void AddNewMachine(Machine machine) throws SQLException {
        SQLiteDB connection = new SQLiteDB();
        try (Connection conn = connection.DbConnect()) {
            PreparedStatement statement = (PreparedStatement) conn.prepareStatement("INSERT INTO Maszyna (nazwa,dostepnosc)" +
                    "VALUES (?,?)");
            statement.setString(1, machine.getName());
            statement.setString(2, machine.getAvailable());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            connection.DbDisconnect();
        }
    }

    public void ChangeStatusMachineOFF(Machine machine) throws SQLException{
        SQLiteDB connection = new SQLiteDB();
        try (Connection conn = connection.DbConnect()) {
            PreparedStatement statement = (PreparedStatement) conn.prepareStatement("UPDATE Maszyna SET dostepnosc = ? WHERE id = ?");
            //String dostep = "nie";
            statement.setString(1, "nie");
            statement.setInt(2, machine.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            connection.DbDisconnect();
        }
    }

    public void ChangeStatusMachineON(Machine machine) throws SQLException{
        SQLiteDB connection = new SQLiteDB();
        try (Connection conn = connection.DbConnect()) {
            PreparedStatement statement = (PreparedStatement) conn.prepareStatement("UPDATE Maszyna SET dostepnosc = ? WHERE id = ?");
            statement.setString(1, "tak");
            statement.setInt(2, machine.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            connection.DbDisconnect();
        }
    }

    public int CheckMachine (Machine machine) throws SQLException {
        SQLiteDB connection = new SQLiteDB();
        Connection conn = connection.DbConnect();
        PreparedStatement statement = (PreparedStatement) conn.prepareStatement("SELECT * FROM Maszyna WHERE id = ?");
        statement.setInt(1, machine.getId());
        //statement.setString(2, machine.getAvailable());
        ResultSet rs = statement.executeQuery();
        int MachineId = 0;
        String dostep = null;
        while(rs.next())
        {
            MachineId = rs.getInt("id");
            dostep = rs.getString("dostepnosc");
        }
        if (MachineId == 0) return 0;
        else if(dostep.equals("nie")) return 0;
        rs.close();
        connection.DbDisconnect();
        return 1;
    }

    public int CheckEmployee (Work work) throws SQLException, ParseException {
        SQLiteDB connection = new SQLiteDB();
        Connection conn = connection.DbConnect();
        PreparedStatement statement = (PreparedStatement) conn.prepareStatement("SELECT * FROM Praca WHERE id_pracownika = ?");
        statement.setInt(1, work.getId_employee());
        ResultSet rs = statement.executeQuery();
        String DateFrom = null;
        String DateTo = null;
        int EmployeeId = 0;
        while(rs.next())
        {
            DateFrom = rs.getString("data_od");
            DateTo = rs.getString("data_do");
            EmployeeId = rs.getInt("id_pracownika");
        }
        if (EmployeeId == 0) return 0;
        SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date currDate = new Date();
        long curr = currDate.getTime();
        Date date1=formatter.parse(DateFrom);
        long datefrom = date1.getTime();
        Date date2=formatter.parse(DateTo);
        long dateto = date2.getTime();
        if(curr > datefrom && curr > dateto) return 0;
//        LocalDate curr = LocalDate.now();
//        LocalDate dateFrom = LocalDate.parse(work.getDateFrom());
//        LocalDate dateTo = LocalDate.parse(work.getDateTo());
//        if(curr.isAfter(dateFrom) && curr.isAfter(dateTo)) return 0;
        rs.close();
        connection.DbDisconnect();
        return 1;
    }

    public void AddSilo(Silo silo) throws SQLException {
        SQLiteDB connection = new SQLiteDB();
        try (Connection conn = connection.DbConnect()) {
            PreparedStatement statement = (PreparedStatement) conn.prepareStatement("INSERT INTO Silos (nazwa,pojemnosc,wolne,nazwa_dzialki)" +
                    "VALUES (?,?,?,?)");

            statement.setString(1, silo.getName());
            statement.setString(2, silo.getCapacity());
            statement.setInt(3, silo.getFreeSpace());
            statement.setString(4, silo.getField().getCity());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            connection.DbDisconnect();
        }
    }

    public List<Silo> GetAllSilos() throws SQLException {
        List<Silo> silos = new ArrayList<>();
        SQLiteDB connection = new SQLiteDB();
        Connection conn = connection.DbConnect();
        PreparedStatement statement = (PreparedStatement) conn.prepareStatement("SELECT id, nazwa, pojemnosc, wolne, nazwa_dzialki FROM Silos");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Silo silo = new Silo();
            silo.setId(rs.getInt("id"));
            silo.setName(rs.getString("nazwa"));
            silo.setCapacity(rs.getString("pojemnosc"));
            silo.setFreeSpace(rs.getInt("wolne"));
            silo.setFieldName(rs.getString("nazwa_dzialki"));
            silos.add(silo);
        }
        rs.close();
        connection.DbDisconnect();
        return silos;
    }

    public void AddField(Field field) throws SQLException {
        SQLiteDB connection = new SQLiteDB();
        try (Connection conn = connection.DbConnect()) {
            PreparedStatement statement = (PreparedStatement) conn.prepareStatement("INSERT INTO Dzialka (miasto,ulica,numer)" +
                    "VALUES (?,?,?)");
            statement.setString(1, field.getCity());
            statement.setString(2, field.getStreet());
            statement.setInt(3, field.getNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            connection.DbDisconnect();
        }
    }

    public List<Field> GetAllFields() throws SQLException {
        List<Field> fields = new ArrayList<>();
        SQLiteDB connection = new SQLiteDB();
        Connection conn = connection.DbConnect();
        PreparedStatement statement = (PreparedStatement) conn.prepareStatement("SELECT id, miasto, ulica, numer FROM Dzialka");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Field field = new Field();
            field.setId(rs.getInt("id"));
            field.setCity(rs.getString("miasto"));
            field.setStreet(rs.getString("ulica"));
            field.setNumber(rs.getInt("numer"));
            fields.add(field);
        }
        rs.close();
        connection.DbDisconnect();
        return fields;
    }

    public ArrayList<Integer> GetPrice(String name) throws SQLException {
        ArrayList<Integer> price = new ArrayList<>();
        SQLiteDB connection = new SQLiteDB();
        Connection conn = connection.DbConnect();
        PreparedStatement statement = (PreparedStatement) conn.prepareStatement("SELECT id, nazwa, cena FROM Surowce WHERE nazwa = ?");
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        while(rs.next())
        {
            price.add(rs.getInt("cena"));
        }
        rs.close();
        connection.DbDisconnect();
        return price;
    }


    public List<Machine> GetAllMachines() throws SQLException {
        List<Machine> machines = new ArrayList<>();
        SQLiteDB connection = new SQLiteDB();
        Connection conn = connection.DbConnect();
        PreparedStatement statement = (PreparedStatement) conn.prepareStatement("SELECT id, nazwa, dostepnosc FROM Maszyna");
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            Machine machine = new Machine();
            machine.setId(rs.getInt("id"));
            machine.setName(rs.getString("nazwa"));
            machine.setAvailable(rs.getString("dostepnosc"));
            machines.add(machine);
        }
        rs.close();
        connection.DbDisconnect();
        return machines;
    }

    public int CheckField (Field field) throws SQLException {
        SQLiteDB connection = new SQLiteDB();
        Connection conn = connection.DbConnect();
        PreparedStatement statement = (PreparedStatement) conn.prepareStatement("SELECT * FROM Dzialka WHERE miasto = ?");
        statement.setString(1, field.getCity());
        ResultSet rs = statement.executeQuery();
        int FieldId = 0;
        while(rs.next())
        {
            FieldId = rs.getInt("id");
        }
        if (FieldId == 0) return 0;
        rs.close();
        connection.DbDisconnect();
        return 1;
    }

    public void ChangeFreeSpace(Silo silo, int cap) throws SQLException{
        SQLiteDB connection = new SQLiteDB();
        Connection conn = connection.DbConnect();
        PreparedStatement statement = (PreparedStatement) conn.prepareStatement("SELECT wolne FROM Silos WHERE id = ?");
        statement.setInt(1, silo.getId());
        ResultSet rs = statement.executeQuery();
        int i = 0;
        while(rs.next())
        {
            i = rs.getInt("wolne");
        }
        statement = (PreparedStatement) conn.prepareStatement("UPDATE Silos SET wolne = ? WHERE id = ?");
        statement.setInt(1, i-cap);
        statement.setInt(2, silo.getId());
        statement.executeUpdate();
        connection.DbDisconnect();
    }

}
