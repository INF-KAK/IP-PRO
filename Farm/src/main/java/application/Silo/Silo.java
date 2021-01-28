package application.Silo;

import application.Field;
import application.Machine.Machine;
import application.Mediator.RequestHandler;
import application.Validation;
import application.dbQuery;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Silo implements SiloChecker {
    private int id;
    private String name;
    private Field field;
    private String capacity;
    private String fieldName;
    private int freeSpace;

    private Silo(String name, Field field) {
        this.name = name.trim();
        this.field = field;
    }

    public Silo() {
    }

    public static Silo create(String name, String capacity, String fieldName){
        Silo silo = new Silo(name, capacity, fieldName);

        return silo;
    }

    private Silo(String name, int freeSpace) {
        this.name = name.trim();
        this.freeSpace = freeSpace;
    }

    public static Silo create1(String name, int freeSpace){
        Silo silo = new Silo(name, freeSpace);

        return silo;
    }

    public Silo(String name, String capacity, String fieldName) {
        this.name = name;
        this.capacity = capacity;
        this.fieldName = fieldName;
    }

    public int checkSilo(int wheat, Silo s) throws SQLException {
        dbQuery db = new dbQuery();
        List<Silo> silos = db.GetAllSilos();
        int idsilo;
        for (Silo silo : silos){
            int freespace = 0;
            if(s.getName().equals(silo.getName())){
                freespace = silo.getFreeSpace();
                if(freespace >= wheat)
                {
                    idsilo = silo.getId();
                    return idsilo;
                }
            }
            if(s.getName().equals(silo.getName())){
                freespace = silo.getFreeSpace();
                if(freespace >= wheat)
                {
                    idsilo = silo.getId();
                    return idsilo;
                }
            }
            if(s.getName().equals(silo.getName())){
                freespace = silo.getFreeSpace();
                if(freespace >= wheat)
                {
                    idsilo = silo.getId();
                    return idsilo;
                }
            }
        }
        return 0;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public int getFreeSpace() {
        return freeSpace;
    }

    public void setFreeSpace(int freeSpace) {
        this.freeSpace = freeSpace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public static Silo create(String newName, Field newField) throws Exception {
        Silo silo = new Silo(newName, newField);

        new SiloValidation(silo).validateOrThrow();

        return silo;
    }
}

class SiloValidation extends Validation<Silo> {

    public SiloValidation(Silo silo) {
        super(silo);
    }

    @Override
    public boolean isValid() {
        return stringIsNotTooLong(validatedObject.getName())
                && validatedObject.getField() != null
                && stringIsNotNullOrEmpty(validatedObject.getField().getCity())
                && stringIsNotNullOrEmpty(validatedObject.getField().getStreet());
    }
}

