package application;

import application.Administrator.Administrator;
import application.Employee.Employee;
import application.Machine.Machine;
import application.Owner.Owner;
import application.Silo.Silo;
import application.Silo.SiloChecker;
import application.Silo.SiloProxy;
import application.Task.Task;
import application.Task.Work;
import application.User.Report;
import application.User.User;

import javax.jws.soap.SOAPBinding;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FarmSystem {
    public static Scanner in = new Scanner(System.in);

    public void initMenu() throws Exception {
        int choice;
        do {
            try {
                printMenuStart();
                choice = Integer.parseInt(in.nextLine());
                switch (choice) {
                    case 1:
                        User user = new User();
                        System.out.print("Imie: ");
                        String a = in.nextLine();
                        System.out.print("Nazwisko: ");
                        String b = in.nextLine();
                        System.out.println("Authenticator: " + readPasswordFromFile());
                        String c = in.nextLine();
                        String pass = readPasswordFromFile();
                        dbQuery db = new dbQuery();
                        user.setName(a);
                        user.setLastName(b);
                        String role = db.CheckRole(user);

                        if (db.CheckLogin(user) == 1 && c.equals(pass) && (role.equals("ADMIN") || (role.equals("OWNER")))) {

                            user.setId(db.CheckID(user));
                            createMenuAdmin(user);
                        } else if (a.equals("stop") || b.equals("stop"))
                            System.exit(0);

                        else {
                            System.out.println("Brak dostepu, sprobuj ponownie!");
                        }
                        break;
                    case 2:
                        User user1 = new User();
                        System.out.print("Imie: ");
                        String name = in.nextLine();
                        System.out.print("Nazwisko: ");
                        String lastname = in.nextLine();
                        user1.setName(name);
                        user1.setLastName(lastname);
                        dbQuery db1 = new dbQuery();
                        if (db1.CheckLogin(user1) == 1) {
                            user1.setId(db1.CheckID(user1));
                            createMenuEmployee(user1);
                        } else if (name.equals("stop") || lastname.equals("stop"))
                            System.exit(0);

                        else {
                            System.out.println("Nie ma takiego pracownika, sprobuj ponownie!");
                        }
                        break;
                    case 0:
                        theEnd();
                        System.exit(0);
                    default:
                        System.out.println("Wystapil blad, sprobuj ponownie!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Podano zly znak..");
            }
        } while (true);
    }

    public void getUsers() throws Exception {
        dbQuery db = new dbQuery();
        List<User> users = db.GetAllUsers();
        for (User us : users) {
            System.out.println("Rola: " + us.getRola() + " Imie: " + us.getName() + " Nazwisko: " + us.getLastName() + " Numer: " + us.getPhoneNumber());
        }
    }

    public void getTasks() throws Exception {
        dbQuery db = new dbQuery();
        List<Task> tasks = db.GetAllTasks();
        for (Task ta : tasks) {
            System.out.println("ID: " + ta.getId() + " Nazwa zadania: " + ta.getNazwa());
        }
    }

    public void addTask() throws Exception {
        System.out.println("Podaj nazwe zadania:");
        String zad = in.nextLine();
        dbQuery db = new dbQuery();
        if (zad.equals(""))
            System.out.println("Puste pole");
        else {
            Task task = Task.create(zad);
            db.AddNewTask(task);
        }
    }


    public void getMachines() throws Exception {
        dbQuery db = new dbQuery();
        List<Machine> machines = db.GetAllMachines();
        checkMachines();
        for (Machine ma : machines) {
            System.out.println("ID: " + ma.getId() + " Maszyna: " + ma.getName() + " Dostepnosc: " + ma.getAvailable());
        }
    }

    public void addMachine() throws Exception {
        System.out.println("Podaj nazwe maszyny:");
        String name = in.nextLine();
        Machine machine = Machine.create(name);
        dbQuery db = new dbQuery();
        machine.setAvailable("tak");
        db.AddNewMachine(machine);
    }

    public void getWorks() throws Exception {
        dbQuery db = new dbQuery();
        List<Work> works = db.GetAllWorks();
        for (Work wo : works) {
            System.out.println("ID zadania: " + wo.getId_task() + " ID pracownika: " + wo.getId_employee() + " ID maszyny: " + wo.getId_machine() + " Data od: " + wo.getDateFrom() + " Data do: " + wo.getDateTo());
        }
    }

    public void getWorksForEmployee(User user) throws Exception {
        dbQuery db = new dbQuery();
        List<Work> works = db.GetAllWorks();
        for (Work wo : works) {
            if (user.getId() == wo.getId_employee())
                System.out.println("ID zadania: " + wo.getId_task() + " ID maszyny: " + wo.getId_machine() + " Data od: " + wo.getDateFrom() + " Data do: " + wo.getDateTo());
        }
    }

    public int getAllWorksEmployee(User user) throws Exception {
        dbQuery db = new dbQuery();
        List<Work> works = db.GetAllWorks();
        int count = 0;
        for (Work wo : works) {
            if (user.getId() == wo.getId_employee()) {
                count++;
            }
        }
        return count;
    }

    public void getCurrentWorksForEmployee(User user) throws Exception {
        dbQuery db = new dbQuery();
        List<Work> works = db.GetAllWorks();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        for (Work wo : works) {
            if (user.getId() == wo.getId_employee()) {
                long curDate = date.getTime();
                Date date1 = formatter.parse(wo.getDateFrom());
                long datefrom = date1.getTime();
                if (datefrom > curDate) {
                    System.out.println("ID zadania: " + wo.getId_task() + " ID maszyny: " + wo.getId_machine() + " Data od: " + wo.getDateFrom() + " Data do: " + wo.getDateTo());
                }
            }
        }
    }

    public float countSalary(User user) throws Exception {
        int sum = getAllWorksEmployee(user);
        float salary = 0;
        salary = sum * 250;
        return salary;
    }


    public void checkMachines() throws Exception {
        dbQuery db = new dbQuery();
        List<Work> works = db.GetAllWorks();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        Machine machine = new Machine();
        for (Work w : works) {
            long curDate = date.getTime();
            Date date1 = formatter.parse(w.getDateTo());
            long dateto = date1.getTime();
            Date date2 = formatter.parse(w.getDateFrom());
            long datefrom = date2.getTime();
            machine.setId(w.getId_machine());
            if (curDate < dateto && datefrom < curDate) {
                //if(dateto>curDate){
                db.ChangeStatusMachineOFF(machine);
            } else db.ChangeStatusMachineON(machine);
        }
    }

    public int checkFreeMachine(Work work) throws Exception {
        dbQuery db = new dbQuery();

        List<Work> works = db.GetAllWorks();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        for (Work work1 : works) {
            if (work1.getId_machine() == work.getId_machine()) {
                Date date = new Date();
                long curDate = date.getTime();
                Date date1 = formatter.parse(work1.getDateTo());
                long dateto = date1.getTime();
                Date date2 = formatter.parse(work1.getDateFrom());
                long datefrom = date2.getTime();
                if (curDate < dateto && datefrom < curDate) return 0;
            }
        }
        return 1;
    }

    public void createReport(User sender) throws Exception {
        //Report report = new Report();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id pracownika");
        int id = in.nextInt();
        System.out.println("Nadaj wiadomosc do uzytkownika");
        String text = scanner.nextLine();
        User user = new User();
        user.setId(id);
        //report.setText(text);
        Report report1 = Report.create(id, sender.getId(), text);
        dbQuery db = new dbQuery();
        String role = db.CheckRole(sender);
        if (db.SearchID(user) != 0 && role.equals("OWNER")) {
            db.AddReport(user, sender, report1);
            GenReport(sender.getId());
        } else {
            if (role.equals("ADMIN"))
                System.out.println("Brak uprawnien");
            else
                System.out.println("Blad nadania wiadomosci");
        }
    }

    public void getReport(Report repo) throws Exception {
        dbQuery db = new dbQuery();
        List<Report> reports = db.GetAllReports();
        for (Report report : reports) {
            if (report.getReceiverR() == repo.getReceiverR()) {
                String name = db.GetName(report.getSenderR());
                System.out.println("Wiadomosc od " + name + " :");
                System.out.println("~~~ " + report.getText());
            }
        }
    }

    public void GenReport(int id) throws Exception {
        System.out.println(GenerateReport.execute(id));
    }

    public int checkFreeEmployee(Work work) throws Exception {
        dbQuery db = new dbQuery();

        List<Work> works = db.GetAllWorks();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        for (Work work1 : works) {
            if (work1.getId_employee() == work.getId_employee()) {
                Date date = new Date();
                long curDate = date.getTime();
                Date date1 = formatter.parse(work1.getDateTo());
                long dateto = date1.getTime();
                if (curDate < dateto) return 0;
            }
        }
        return 1;
    }

    public int checkEmployee(Work work) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            if (work.getId_employee() == work.getId_employee()) {
                Date date = new Date();
                long curDate = date.getTime();
                Date date1 = formatter.parse(work.getDateTo());
                long dateto = date1.getTime();
                if (curDate < dateto) return 0;
            }
        return 1;
    }

    public void addWorks() throws Exception {
        System.out.println("Podaj ID zadania");
        int idz = in.nextInt();
        System.out.println("Podaj ID pracownika");
        int idp = in.nextInt();
        System.out.println("Podaj ID maszyny");
        int idm = in.nextInt();

        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj date rozpoczecia(dd-MM-yyyy HH:mm:ss):");
        //System.out.println("Podaj date rozpoczecia(yyyy-MM-dd):");
        String date_from = scan.nextLine();
        //System.out.println("Podaj liczbe dni na wykonanie zadania:");
        System.out.println("Podaj date zakonczenia(dd-MM-yyyy HH:mm:ss):");
        String date_to = scan.nextLine();

        Work work = Work.create(idz,idp,idm,date_from,date_to);
        dbQuery db = new dbQuery();

        Machine machine = new Machine();
        machine.setId(idm);
        try{

        if (checkFreeMachine(work) == 0) {
            System.out.println("Maszyna niedostepna");
        } else {
            db.ChangeStatusMachineON(machine);
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date();
            long curDate = date.getTime();
            Date date1 = formatter.parse(date_to);
            long dateto = date1.getTime();
            Date date2 = formatter.parse(date_from);
            long datefrom = date2.getTime();
            Silo silo = new Silo();

            if (checkFreeEmployee(work) == 1) {
                if (curDate < dateto) { //datefrom < curDate &&
                    db.ChangeStatusMachineOFF(machine);
                    db.AddNewWork(work);
                    if (idz == 1) {
                        silo.setName("zboze");
                    }
                    if (idz == 12) {
                        silo.setName("przenica");
                    }
                    if (idz == 13) {
                        silo.setName("jeczmien");
                    }
                    if (idz == 1 || idz == 12 || idz == 13) {
                        System.out.println("Podaj przyblizona ilosc zbiorow ziarna: ");
                        int wheat = in.nextInt();

                        SiloChecker siloChecker = new SiloProxy();
                        int id = siloChecker.checkSilo(wheat, silo);

                        if (id == 0)
                            System.out.println("Brak wolnego miejsca w silosach");
                        else {
                            silo.setId(id);
                            if (silo.getName().equals("zboze")) {
                                System.out.println("Dodano " + wheat + " ton zboza do silosa o id " + silo.getId());
                            }
                            if (silo.getName().equals("przenica")) {
                                System.out.println("Dodano " + wheat + " ton przenicy do silosa o id " + silo.getId());
                            }
                            if (silo.getName().equals("jeczmien")) {
                                System.out.println("Dodano " + wheat + " ton jeczmienia do silosa o id " + silo.getId());
                            }
                            db.ChangeFreeSpace(silo, wheat);
                        }
                    }
                }
            } else System.out.println("Pracownik zajety, niemozliwe do wykonania");
        }
        } catch (NumberFormatException | ParseException e) {
            System.out.println("Podano zle dane..");
        }
    }

    public int checkSilos(Silo silo) throws Exception{
        if(silo.getFreeSpace() != 0)
            return 1;
        return 0;
    }

    public int FullSilo(Silo silo) throws Exception{
            if(checkSilos(silo) == 0){
                return 0;
            }
        return 1;
    }

    public int ResultSilo(Silo silo) throws Exception{
        if(FullSilo(silo)!=0)
            return silo.getFreeSpace();
        return 0;
    }

    public void FreeSpace() throws SQLException {
        dbQuery db = new dbQuery();
        List<Silo> silos = db.GetAllSilos();
        for (Silo silo : silos) {
            if (silo.getFreeSpace() < 500) {
                System.out.println("UWAGA! Silos " + silo.getName() + " o id " + silo.getId() + " prawie pelny !!! ");
            } else if (silo.getFreeSpace() < 0)
                System.out.println("UWAGA! Silos " + silo.getName() + " o id " + silo.getId() + " PELNY !!! ");
        }
    }

    static String getRole() {
        String role;
        System.out.println("Podaj typ(1,2,3): 1.OWNER, 2.ADMIN, 3.EMPLOYEE");
        int x = Integer.parseInt(in.nextLine());

        if (x == 1) {
            role = User.Role.OWNER.toString();
            return role;
        } else if (x == 2) {
            role = User.Role.ADMIN.toString();
            return role;
        } else if (x == 3) {
            role = User.Role.EMPLOYEE.toString();
            return role;
        }
        return null;
    }

    public void deleteUser() throws Exception {
        System.out.println("Podaj id uzytkownika do usuniecia:");
        int id = in.nextInt();

        User user = new User();
        dbQuery db = new dbQuery();
        user.setId(id);
        if (db.SearchID(user) == 0)
            System.out.println("Brak uzytkownika o takim id");
        else {
            db.DeleteUser(user);
            System.out.println("Usunieto uzytkownika o id " + id);
        }
    }

    public void addUser() throws SQLException {
        String role = getRole();
        System.out.println("Podaj imie:");
        String imie = in.nextLine();
        System.out.println("Podaj nazwisko:");
        String nazwisko = in.nextLine();
        System.out.println("Podaj numer:");
        String numer = in.nextLine();
        System.out.println("Podaj dzialke nr 1:");
        String dzialka1 = in.nextLine();
        System.out.println("Podaj dzialke nr 2:");
        String dzialka2 = in.nextLine();

        Field field1 = new Field();
        field1.setCity(dzialka1);
        Field field2 = new Field();
        field2.setCity(dzialka2);
        dbQuery db = new dbQuery();

        try{
        if (db.CheckField(field1) == 1 && db.CheckField(field2) == 1){

            if (role.equals(User.Role.OWNER.toString())) {
                List<Field> fields = Arrays.asList(new Field(dzialka1), new Field(dzialka2));
                Owner owner = Owner.create2(User.create1(imie, nazwisko, numer, fields, role));
                db.addNewUser(owner);
            } else if (role.equals(User.Role.ADMIN.toString())) {
                List<Field> fields = Arrays.asList(new Field(dzialka1), new Field(dzialka2));
                Administrator admin = Administrator.create2(User.create1(imie, nazwisko, numer, fields, role));
                db.addNewUser(admin);
            } else if (role.equals(User.Role.EMPLOYEE.toString())) {
                List<Field> fields = Arrays.asList(new Field(dzialka1), new Field(dzialka2));
                Employee employee = Employee.create2(User.create1(imie, nazwisko, numer, fields, role));
                db.addNewUser(employee);
            } else System.out.println("Blad");
            }
        else
            System.out.println("Dzialka nie istnieje..");
        } catch (NumberFormatException e) {
            System.out.println("Podano zla nazwe");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void createMenuAdmin(User user) throws Exception {
        int y;

        do {
            System.out.println("-------------------------");
            System.out.println("1. Dodaj uzytkownika");
            System.out.println("2. Wyswietl uzytkownikow");
            System.out.println("3. Dodaj prace");
            System.out.println("4. Wyswietl prace");
            System.out.println("5. Dodaj zadanie");
            System.out.println("6. Wyswietl zadania");
            System.out.println("7. Dodaj maszyne");
            System.out.println("8. Wyswietl maszyny");
            System.out.println("9. Dodaj dzialke");
            System.out.println("10. Wyswietl dzialki");
            System.out.println("11. Dodaj Silos");
            System.out.println("12. Wyswietl silosy");
            System.out.println("13. Usun uzytkownika");
            System.out.println("14. Nadanie wiadomosci");
            System.out.println("15. Ostrzezenia silos");
            System.out.println("16. Sprawdz stan silosu");
            System.out.println("17. Sprawdz cene surowcow");
            System.out.println("18. Przelacz uzytkownika");
            System.out.println("0. Wyjdz");
            System.out.println("-------------------------");
            try {
            y = Integer.parseInt(in.nextLine());
            switch (y) {
                case 1:
                    addUser();
                    break;
                case 2:
                    getUsers();
                    break;
                case 3:
                    addWorks();
                    break;
                case 4:
                    getWorks();
                    break;
                case 5:
                    addTask();
                    break;
                case 6:
                    getTasks();
                    break;
                case 7:
                    addMachine();
                    break;
                case 8:
                    getMachines();
                    break;
                case 9:
                    addField();
                    break;
                case 10:
                    getFields();
                    break;
                case 11:
                    addSilo();
                    break;
                case 12:
                    getSilos();
                    break;
                case 13:
                    deleteUser();
                    break;
                case 14:
                    createReport(user);
                    break;
                case 15:
                    FreeSpace();
                    break;
                case 16:
                    getSiloCapacity();
                    break;
                case 17:
                    checkPrice();
                    break;
                case 18:
                    initMenu();
                    break;
                case 0:
                    theEnd();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Brak opcji");
                    initMenu();
                    break;
            }
            }
            catch (NumberFormatException e){
            }
        }while(true);

    }

    static void printMenuStart(){
        System.out.println(" ->Gospodarstwo rolne <- ");
        System.out.println("1. Zaloguj jako admin");
        System.out.println("2. Zaloguj jako pracownik");
        System.out.println("0. Zakoncz");
        System.out.print("Podaj opcje: ");
    }

    public void theEnd(){
        System.out.println("Do widzenia, Milego dnia!");
    }

    public void createMenuEmployee(User user) throws Exception {
        int x;
        do {
            System.out.println("1. Sprawdz prace oczekujace");
            System.out.println("2. Wykonane prace");
            System.out.println("3. Wynagrodzenie");
            System.out.println("4. Odczytaj wiadomosci");
            System.out.println("5. Przelacz uzytkownika");
            System.out.println("0. Wyjdz");
            x = Integer.parseInt(in.nextLine());
            switch (x) {
                case 1:
                    getCurrentWorksForEmployee(user);
                    break;
                case 2:
                    System.out.println("Wykonane prace przez " + user.getName() + " " + user.getLastName() + " wynosza: " + getAllWorksEmployee(user));
                    getWorksForEmployee(user);
                    break;
                case 3:
                    System.out.println("Wynagrodzenie wynosi: " + countSalary(user) + " zl");
                    break;
                case 4:
                    Report report = new Report();
                    report.setReceiverR(user.getId());
                    getReport(report);
                    break;
                case 5:
                    initMenu();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Brak opcji");
                    break;
            }
        } while (true);
    }

    public void createPasswordToFile() throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter("pass.txt");
        Random random = new Random();
        int x=0;
        String password = "";
        for(int i=0; i<6; i++){
            x = random.nextInt(10);
            password += String.valueOf(x);
        }
        printWriter.println(password);
        printWriter.close();
    }

    public String readPasswordFromFile() throws FileNotFoundException{
        File file = new File("pass.txt");
        Scanner scanner = new Scanner(file);
        String pass = scanner.nextLine();
        return pass;
    }

    public void addSilo() throws SQLException {
        System.out.println("Podaj nazwe silosa: ");
        String name = in.nextLine();
        System.out.println("Podaj pojemnosc silosa: ");
        String capacity = in.nextLine();
        System.out.println("Podaj miasto dzialki: ");
        String namefield = in.nextLine();
        dbQuery db = new dbQuery();
        Field field = new Field();
        field.setCity(namefield);
        int freespace = Integer.parseInt(capacity);

        if(db.CheckField(field) == 1){
            Silo silo = new Silo();
            silo.setName(name);
            silo.setCapacity(capacity);
            silo.setField(field);
            silo.setFreeSpace(freespace);
            db.AddSilo(silo);
        }
        else System.out.println("Nie znaleziono dzialki");
    }

    public void checkPrice() throws Exception{
        dbQuery db = new dbQuery();
        List<Silo> silos = db.GetAllSilos();
        try{
            System.out.println("Podaj nazwe zbioru: zboze/jeczmien/przenica");
            String name = in.nextLine();
            int l = 0;
        for (Silo silo : silos){
            ArrayList<Integer> price = db.GetPrice(name);
            l = Collections.max(price);
            if(name.equals(silo.getName()) && l == Collections.max(price)) {
                System.out.println("Najlepsza cena dla " + name + " wynosi: " + l + " zl/1 tona");
                break;
            }
            }
        }
        catch (ClassCastException | NoSuchElementException e) {
            System.out.println("Exception : " + e);
        }
    }

    public void getSiloCapacity() throws Exception{
        dbQuery db = new dbQuery();
        List<Silo> silos = db.GetAllSilos();
        double capacity = 0.0;
        double count = 0.0;
        for (Silo silo : silos){
            capacity = Double.parseDouble(silo.getCapacity());
            count = ((capacity-silo.getFreeSpace())/capacity)*100;
            System.out.println("Stan zajetego miejsca w silosie o id " + silo.getId() + " i nazwie " + silo.getName() + " wynosi " + count + " %");
        }
    }


    public void addField() throws SQLException {
        System.out.println("Podaj miasto: ");
        String city = in.nextLine();
        System.out.println("Podaj ulice: ");
        String st = in.nextLine();
        System.out.println("Podaj numer: ");
        int nr = in.nextInt();

        dbQuery db = new dbQuery();
        Field field = new Field();
        field.setCity(city);
        field.setStreet(st);
        field.setNumber(nr);
        db.AddField(field);
    }

    public void getSilos() throws Exception{
        dbQuery db = new dbQuery();
        List<Silo> silos = db.GetAllSilos();
        for (Silo silo : silos){
            System.out.println("ID silosu: " + silo.getId() + " Nazwa: " +  silo.getName() + " Pojemnosc: " + silo.getCapacity() + " Wolne miejsce: " + silo.getFreeSpace() + " Nazwa dzialki: " + silo.getFieldName());
        }
    }

    public void getFields() throws Exception{
        dbQuery db = new dbQuery();
        List<Field> fields = db.GetAllFields();
        for (Field field : fields){
            System.out.println("ID dzialki: " + field.getId() + " Miasto: " +  field.getCity() + " Ulica: " + field.getStreet() + " Numer: " + field.getNumber());
        }
    }
}
