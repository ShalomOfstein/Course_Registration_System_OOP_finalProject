package CourseRegistrationSystem.Courses;

import CourseRegistrationSystem.Observer_Design_Pattern.Notification;
import CourseRegistrationSystem.Observer_Design_Pattern.Observer;
import CourseRegistrationSystem.Observer_Design_Pattern.Subject;
import CourseRegistrationSystem.Participants.Student;
import CourseRegistrationSystem.Participants.Worker;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Course implements Subject {
    protected final String courseName;
    protected final int courseNumber;
    protected int maxStudents;
    protected int numStudents;
    protected ArrayList<Student> students;
    protected ArrayList<Worker> staff;
    protected ArrayList<Observer> observers ;
    public boolean cancelled = false;

    public Course(String CourseName, int CourseNumber, int MaxStudents, Worker staffMember1) {
        this.courseName = CourseName;
        this.courseNumber = CourseNumber;
        this.maxStudents = MaxStudents;
        this.numStudents = 0;
        this.students = new ArrayList<Student>();
        this.observers = new ArrayList<Observer>();
        this.staff = new ArrayList<Worker>();
        staff.add(staffMember1);

    }
    public void addStudent (Student student) {
        if ((getVacancies()>0)&&(!students.contains(student))) {
            students.add(student);
            student.registerToCourse(this);
            numStudents++;
        } else if(getVacancies()==0&&!cancelled){
            System.out.println("The course is full. would you like to receive a notification when a spot opens up? (y/n)");
            Scanner scanner = new Scanner(System.in);
            String response = scanner.nextLine();
            if(response.equals("y")||response.equals("Y")){
                this.addObserver(student);
            }
        }
    }
    public void removeStudent (Student student) {
        if (students.contains(student)) {
            students.remove(student);
            student.unregisterCourses(this);
            numStudents--;
        }
        if(numStudents== maxStudents-1&&!cancelled){
            String message = "Course " + courseName + "(Course Number: "+ courseNumber+ ") is now available";
            this.notifyObservers(message);
        }
    }
    public ArrayList<Student> getStudents(){
        return students;
    }

    public void cancelCourse(){
        if(!cancelled){
            String message = "Course " + courseName + "(Course Number: "+ courseNumber+ ") has been cancelled";
            this.notifyStudents(message);
            cancelled = true;
            students.clear();

        }
    }
    public void addStaff(Worker staffMember){
        if(!staff.contains(staffMember)){
            staff.add(staffMember);
        }
    }
    public void removeStaff(Worker staffMember){
        staff.remove(staffMember);
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
    public void notifyStudents(String message){
        for(Student student: students){
            Notification notification = new Notification(message,this,student);
            student.update(notification);
        }
    }
    public String toString(){
        return "Course Name: " + courseName + ", Course Number: " + courseNumber;
    }
    public String getName(){
        return courseName+" Course";
    }



}
