package application.Date;

import java.time.Clock;
import java.time.LocalDate;

public class DateProvider implements LocalDateProvider {
    @Override
    public LocalDate now(Clock clock) {
        return LocalDate.now(clock);
    }
}
