package application.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Task {
    private int id;
    private String nazwa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Task() {
    }

    public Task(String nazwa) {
        this.nazwa = nazwa;
    }

    public static Task create(String nazwa) throws Exception{
        Task task = new Task(nazwa);
        return  task;
    }

    //    private LocalDateTime dateFrom;
//    private LocalDateTime dateTo;

//    public Task(int id, LocalDateTime dateFrom, LocalDateTime dateTo) {
//        this.id = id;
//        this.dateFrom = dateFrom;
//        this.dateTo = dateTo;
//    }

//    public float calculateHours(){
//        float timeFrom, timeTo, time;
//        timeFrom = dateFrom.getHour();
//        timeTo = dateTo.getHour();
//        time = timeFrom-timeTo;
//
//        return time;
//    }

//    public List<String> initTasks() {
//        List<String> tasks = new ArrayList<>();
//        tasks.add("Oraj pole");
//        tasks.add("Zasiej pole");
//        tasks.add("Podlej pole");
//        tasks.add("Kos pole");
//        return tasks;
//    }

}
