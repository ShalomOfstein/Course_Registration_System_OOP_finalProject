package CourseRegistrationSystem.Courses;
import CourseRegistrationSystem.Observer_Design_Pattern.Notification;
import CourseRegistrationSystem.Observer_Design_Pattern.Observer;
import CourseRegistrationSystem.Observer_Design_Pattern.Subject;
import CourseRegistrationSystem.Participants.Student;
import java.util.ArrayList;

public abstract class Course implements Subject {
    protected final String courseName;
    protected final int courseNumber;
    protected int maxStudents;
    protected int numStudents;
    protected ArrayList<Student> students;

    protected ArrayList<Observer> observers ;

    public Course(String CourseName, int CourseNumber, int MaxStudents) {
        this.courseName = CourseName;
        this.courseNumber = CourseNumber;
        this.maxStudents = MaxStudents;
        this.numStudents = 0;
        this.students = new ArrayList<Student>();
        this.observers = new ArrayList<Observer>();
    }
    public void addStudent (Student student) {
        if (getVacancies()>0) {
            if(students.contains(student)){
                System.out.println("Student already enrolled in this course");
                return;
            }
            students.add(student);
            numStudents++;
        }
    }
    public void removeStudent (Student student) {
        if (students.contains(student)) {
            students.remove(student);
            numStudents--;
        }
        if(numStudents== maxStudents-1){
            String message = "Course " + courseName + "(Course Number: "+ courseNumber+ ") is now available";
            this.notifyObservers(message);
        }
    }

    /**
     * Getters
     */
    public String getCourseName() {
        return courseName;
    }
    public int getCourseNumber() {
        return courseNumber;
    }
    public int getVacancies() {
        return maxStudents - numStudents;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    @Override
    public void notifyObservers(String message) {
        for(Observer observer: observers){
            Notification notification = new Notification(message,this,observer);
            observer.update(notification);
        }
    }


}
