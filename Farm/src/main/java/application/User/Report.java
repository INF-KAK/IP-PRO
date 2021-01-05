package application.User;

public class Report {
    private User user;
    private String text;

    public Report(User user, String text) {

        this.user = user;
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public String getText() {
        return text;
    }
}
