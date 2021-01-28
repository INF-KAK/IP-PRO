package application;

public class Field {
    private int id;
    private String city;
    private String street;
    private int number;


    public Field(String city, String street, int number) {
        this.city = city.trim();
        this.street = street.trim();
        this.number = number;
    }

    public Field() {
    }

    public Field(String city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }

    public static Field create(String city, String street, int number) throws Exception {
        Field field = new Field(city, street, number);

        FieldValidation validator = new FieldValidation(field);
        validator.validateOrThrow();

        return field;
    }
}

class FieldValidation extends Validation<Field> {
    public FieldValidation(Field field) {
        super(field);
    }

    @Override
    public boolean isValid() {
        Field field = validatedObject;
        boolean streetIsValid = stringContainsOnlyLetters(field.getStreet());
        boolean cityIsValid = stringContainsOnlyLetters(field.getCity());
        boolean numberIsValid = numberIsInRange(field.getNumber(), 0, 100);

        return streetIsValid && cityIsValid && numberIsValid;
    }
}