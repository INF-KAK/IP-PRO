package application.Task;

import java.time.LocalDateTime;

public class Work {
    private int id;
    private int id_task;
    private int id_employee;
    private int id_machine;
    private String dateFrom;
    private String dateTo;

    public Work() {
    }

    public Work(int id_task, int id_employee, int id_machine, String dateFrom, String dateTo) {
        this.id_task = id_task;
        this.id_employee = id_employee;
        this.id_machine = id_machine;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public static Work create(int id_task, int id_employee, int id_machine, String dateFrom, String dateTo) throws Exception{
        Work work = new Work(id_task,id_employee,id_machine,dateFrom,dateTo);
        return work;
    }

    public int getId_task() {
        return id_task;
    }

    public void setId_task(int id_task) {
        this.id_task = id_task;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public int getId_machine() {
        return id_machine;
    }

    public void setId_machine(int id_machine) {
        this.id_machine = id_machine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
}
