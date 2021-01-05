package application.Notification;

import application.Exhibition.Exhibition;
import application.User.User;

import java.util.List;

public interface Calendar {
    List<Exhibition> getInfoFromCalendar(User user);
    void addToCalendar(Notification notification);
}
