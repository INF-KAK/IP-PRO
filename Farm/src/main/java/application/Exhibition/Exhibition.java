package application.Exhibition;

import application.Silo.Silo;
import application.Validation;

import java.time.LocalDate;
import java.util.List;

public class Exhibition {
    private String name;
    private String details;
    private LocalDate date;
    private Silo silo;
    private double price;

    public Exhibition(String name, String details, LocalDate date, Silo silo, double price) {
        this.name = name.trim();
        this.details = details.trim();
        this.date = date;
        this.silo = silo;
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public Silo getSilo() {
        return silo;
    }

    public double getPrice() {
        return price;
    }


    public static Event create(String name, String details, LocalDate date, Silo silo, double price) throws Exception {
        Exhibition exhibition = new Exhibition(name, details, date, silo, price);
        new ExhibitionValidation(exhibition).validateOrThrow();

        return exhibition;
    }
}

class ExhibitionValidation extends Validation<Exhibition> {

    public ExhibitionValidation(Exhibition exhibition) {
        super(exhibition);
    }

    @Override
    public boolean isValid() {
        return !dateIsExpired(validatedObject.getDate()) && validatedObject.getSilo() != null;
    }
}
