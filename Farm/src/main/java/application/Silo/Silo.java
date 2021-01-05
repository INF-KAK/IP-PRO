package application.Silo;

import application.Field;
import application.Validation;

public class Silo {
    private String name;
    private Field field;

    private Silo(String name, Field field) {
        this.name = name.trim();
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public Field getField() {
        return field;
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
