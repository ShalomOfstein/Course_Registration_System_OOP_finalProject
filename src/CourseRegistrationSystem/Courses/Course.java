package CourseRegistrationSystem.Courses;

import CourseRegistrationSystem.Observer_Design_Pattern.Notification;
import CourseRegistrationSystem.Observer_Design_Pattern.Observer;
import CourseRegistrationSystem.Observer_Design_Pattern.Subject;
import CourseRegistrationSystem.Participants.Lecturer;
import CourseRegistrationSystem.Participants.Student;
import CourseRegistrationSystem.Participants.Worker;

import java.util.ArrayList;

public abstract class Course implements Subject {
    protected final String courseName;
    protected final int courseNumber;
    protected int maxStudents;
    protected int numStudents;
    protected ArrayList<Student> students;
    protected Lecturer lecturer;
    protected ArrayList<Worker> staff;
    protected ArrayList<Observer> observers;
    public boolean cancelled = false;

    /**
     * Constructor for the Course class
     * @param CourseName the name of the course
     * @param CourseNumber the ID number of a course (no 2 courses can have the same ID)
     * @param MaxStudents the maximum number of students that can be enrolled in the course
     * @param lecturer the lecturer teaching the course
     */

    public Course(String CourseName, int CourseNumber, int MaxStudents, Lecturer lecturer) {
        this.courseName = CourseName;
        this.courseNumber = CourseNumber;
        this.maxStudents = MaxStudents;
        this.numStudents = 0;
        this.students = new ArrayList<Student>();
        this.observers = new ArrayList<Observer>();
        this.lecturer = lecturer;
        this.staff = new ArrayList<Worker>();

    }

    /***************************************************************************************************
     * the methods to make the course observable
     */
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
            String studentName = ((Student)observer).getName();
            Notification notification = new Notification(message,courseName,studentName);
            observer.update(notification);
        }
    }
    /***************************************************************************************************
     * the methods to update students in the course with messages
     */
    public void notifyStudents(String message) {
        for(Student student: students){
            Notification notification = new Notification(message,courseName,student.getName());
            student.update(notification);
        }
    }

    /***************************************************************************************************
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
    public ArrayList<Student> getStudents(){
        return students;
    }
    public Lecturer getLecturer(){
        return lecturer;
    }
    public ArrayList<Worker> getStaff(){
        return staff;
    }
    public boolean isCancelled(){
        return cancelled;
    }
    public int getNumStudents(){
        return numStudents;
    }
    public int getMaxStudents(){
        return maxStudents;
    }
    /***************************************************************************************************
     * the methods to  remove students, observers and staff from the course
     */
    public void clearStudents(){
        students.clear();
    }
    public void clearStaff(){
        staff.clear();
    }
    public void clearObservers(){
        observers.clear();
    }
    /***************************************************************************************************
     * the methods to add and remove students and staff to the course
     */
    public void addStudent (Student student) {
        students.add(student);
        numStudents++;
    }
    public void removeStudent (Student student) {
        students.remove(student);
        numStudents--;
    }
    public void addStaff(Worker staffMember){
        staff.add(staffMember);
    }
    public void removeStaff(Worker staffMember){
        staff.remove(staffMember);
    }
    /***************************************************************************************************
     * the methods to cancel the course
     */
    public void cancelCourse() {
        cancelled = true;
    }






}
//    public void addStudent (Student student) {
//        if ((getVacancies()>0)&&(!students.contains(student))) {
//            students.add(student);
//            student.registerToCourse(this);
//            numStudents++;
//        } else if(getVacancies()==0&&!cancelled){
//            System.out.println("The course is full. would you like to receive a notification when a spot opens up? (y/n)");
//            Scanner scanner = new Scanner(System.in);
//            String response = scanner.nextLine();
//            if(response.equals("y")||response.equals("Y")){
//                this.addObserver(student);
//            }
//        }
//    }
//    public void removeStudent (Student student) {
//        if (students.contains(student)) {
//            students.remove(student);
//            student.unregisterCourses(this);
//            numStudents--;
//        }
//        if(numStudents== maxStudents-1&&!cancelled){
//            String message = "Course " + courseName + "(Course Number: "+ courseNumber+ ") is now available";
//            this.notifyObservers(message);
//        }
//    }
//    public ArrayList<Student> getStudents(){
//        return students;
//    }
//
//    public void cancelCourse(){
//        if(!cancelled){
//            String message = "Course " + courseName + "(Course Number: "+ courseNumber+ ") has been cancelled";
//            this.notifyStudents(message);
//            cancelled = true;
//            students.clear();
//
//        }
//    }
//    public void addStaff(Worker staffMember){
//        if(!staff.contains(staffMember)){
//            staff.add(staffMember);
//        }
//    }
//    public void removeStaff(Worker staffMember){
//        staff.remove(staffMember);
//    }
//
//    /**
//     * Getters
//     */
//    public String getCourseName() {
//        return courseName;
//    }
//    public int getCourseNumber() {
//        return courseNumber;
//    }
//    public int getVacancies() {
//        return maxStudents - numStudents;
//    }
//
//    @Override
//    public void addObserver(Observer observer) {
//        observers.add(observer);
//    }
//    @Override
//    public void removeObserver(Observer observer) {
//        observers.remove(observer);
//    }
//    @Override
//    public void notifyObservers(String message) {
//        for(Observer observer: observers){
//            Notification notification = new Notification(message,this,observer);
//            observer.update(notification);
//        }
//    }
//    public void notifyStudents(String message){
//        for(Student student: students){
//            Notification notification = new Notification(message,this,student);
//            student.update(notification);
//        }
//    }
//    public String toString(){
//        return "Course Name: " + courseName + ", Course Number: " + courseNumber;
//    }
//    public String getName(){
//        return courseName+" Course";
//    }

