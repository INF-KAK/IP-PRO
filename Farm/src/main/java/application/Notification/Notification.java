package application.Notification;

import application.Exhibition.Exhibition;
import application.User.User;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Notification {
    private User user;
    private Exhibition exhibition;

    public Notification(User user, Exhibition exhibition) {
        this.user = user;
        this.exhibition = exhibition;
    }

    public User getUser() {
        return user;
    }

    public Exhibition getExhibition() {
        return exhibition;
    }

    public void setNotification(Calendar calendar) {
        calendar.addToCalendar(this);
    }

    public static List<Exhibition> getNotificationsToPublish(User user, Calendar calendar, LocalDate date) {
        List<Exhibition> exhibitions = calendar.getInfoFromCalendar(user);

        return exhibitions.stream().filter(e -> date.plusDays(1).isEqual(e.getDate())).collect(Collectors.toList());
    }
}
