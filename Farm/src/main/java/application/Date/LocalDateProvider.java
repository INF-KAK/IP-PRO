package application.Date;

import java.time.Clock;
import java.time.LocalDate;

public interface LocalDateProvider {
    LocalDate now(Clock clock);
}
