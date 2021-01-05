package application.Date;

import java.time.LocalDate;
import java.time.Period;

public class DateFormat {
    private int year;
    private int month;
    private int day;
    private LocalDate firstDate;
    private LocalDate secondDate;

    public DateFormat(LocalDate firstDate, LocalDate secondDate) {
        this.firstDate = firstDate;
        this.secondDate = secondDate;
    }

    @Override
    public String toString() {
        Period period = Period.between(firstDate, secondDate);
        year = period.getYears();
        month = period.getMonths();
        day = period.getDays();
        if(year == 0 && month == 0 && day == 0) {
            return "Exhibition starts today";
        }
        else if(year < 0 || month < 0 || day < 0) {
            return "Exhibition has already took place";
        }
        else {
            return String.format("Exhibition starts in %d years %d months and %d days", year, month, day);
        }
    }
}
