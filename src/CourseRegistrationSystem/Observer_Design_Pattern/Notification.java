package CourseRegistrationSystem.Observer_Design_Pattern;

public class Notification {
    private String message;
    private Subject sender;
    private Observer receiver;

    public Notification(String message, Subject sender, Observer receiver){
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
    public Subject getSender(){
        return sender;
    }
    public Observer getReceiver(){
        return receiver;
    }

    public String toString(){

        String ans = "Notification from: " + sender.getName() + " To: " + receiver.getName() + " Message: " + message;
        return ans;
    }



}
