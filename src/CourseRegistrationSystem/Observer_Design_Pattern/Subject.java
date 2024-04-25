package CourseRegistrationSystem.Observer_Design_Pattern;

public interface Subject {
    String getName();
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String message);
}
