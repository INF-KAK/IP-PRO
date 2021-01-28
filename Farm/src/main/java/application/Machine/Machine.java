package application.Machine;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class Machine {
    private int id;
    private String name;
    private String available;

    public Machine() {
    }

    public Machine(String name) {
        this.name = name;
    }

    public static Machine create(String name){
        Machine machine = new Machine(name);

        return machine;
    }


    public Machine(String name, float fuel, String available) {
        this.id = id;
        this.name = name;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
}
