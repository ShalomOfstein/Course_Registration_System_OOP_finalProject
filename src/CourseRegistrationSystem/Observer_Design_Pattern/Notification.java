package CourseRegistrationSystem.Observer_Design_Pattern;

public class Notification {
    private final String message;
    private final String sender;
    private final String receiver;

    public Notification(String message, String sender, String receiver){
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
    }
    public Notification(Notification notification){
        this.message = notification.getMessage();
        this.sender = notification.getSender();
        this.receiver = notification.getReceiver();
    }

    public String getMessage(){
        return message;
    }
    public String getSender(){
        return sender;
    }
    public String getReceiver(){
        return receiver;
    }

    public String toString(){
        return "Notification from: " + sender+ " To: " + receiver + " Message: " + message;
    }



}
