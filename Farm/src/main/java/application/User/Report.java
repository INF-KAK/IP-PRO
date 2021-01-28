package application.User;

import application.Machine.Machine;

public class Report {
    private int id;
    private User sender;
    private User receiver;
    private String text;
    private int senderR;
    private int receiverR;

    public Report(int sender, int receiver, String text) {

        this.senderR = sender;
        this.receiverR = receiver;
        this.text = text;
    }

    public static Report create(int sender, int receiver, String text){
        Report report = new Report(sender,receiver,text);

        return report;
    }

    public Report(User sender, User receiver, String text) {

        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
    }

    public Report() {
    }

    public int getId() {
        return id;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public int getSenderR() {
        return senderR;
    }

    public void setSenderR(int senderR) {
        this.senderR = senderR;
    }

    public int getReceiverR() {
        return receiverR;
    }

    public void setReceiverR(int receiverR) {
        this.receiverR = receiverR;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
