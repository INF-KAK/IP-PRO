package application;

import java.time.LocalDate;
import java.util.Formatter;

public abstract class Validation<T> {
    protected T validatedObject;

    protected Validation(T obj) {
        this.validatedObject = obj;
    }

    protected boolean stringContainsOnlyLetters(String value) {
        return value.matches("^[ A-Za-z]+$");
    }

    protected boolean numberIsInRange(int number, int min, int max) {
        return number > 0 && number < max;
    }

    protected boolean phoneNumberIsCorrect(String number) {
        return number.matches("^(?:[+][0-9]{2}\\s?[0-9]{3}[-]?[0-9]{3,}|(?:[(][0-9]{3}[)]|[0-9]{3})\\s*[-]?\\s*[0-9]{3}[-][0-9]{4})(?:\\s*x\\s*[0-9]+)?"); }

    protected boolean stringIsNotTooLong(String value) {
        return value.length() > 0 && value.length() <= 100;
    }

    protected boolean stringIsNotTooLong(String value, int length) {
        return value.length() > 0 && value.length() <= length;
    }

    protected boolean dateIsExpired(LocalDate date) {
        return date.isBefore(LocalDate.now());
    }

    protected boolean stringIsNotNullOrEmpty(String value) {
        return value != null && !value.isEmpty();
    }

    public abstract boolean isValid();

    public void validateOrThrow() throws Exception {
        Formatter formatter = new Formatter();
        if(!isValid()) {
            throw new Exception(formatter.format("%s is invalid!", validatedObject.getClass().getSimpleName()).toString());
        }
    }
}
