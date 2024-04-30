package CourseRegistrationSystem;

import CourseRegistrationSystem.Courses.Course;
import CourseRegistrationSystem.Factories.CourseFactory;
import CourseRegistrationSystem.Factories.UserFactory;
import CourseRegistrationSystem.Participants.*;

import java.util.Scanner;

public class RegistrationSystem {
    private static RegistrationSystem instance = null;



    /**
     * Constructor
     * the constructor is private to ensure that only one instance of the class is created
     * this utilizes the singleton design pattern
     * to access the instance of the class, the getInstance method must be called
     */
    private RegistrationSystem(){

    }
    public static RegistrationSystem getInstance(){
        if(instance == null){
            instance = new RegistrationSystem();
        }
        return instance;
    }

    /**************************************************************************************************
     * Methods to register and unregister students to courses
     */
    public void registerToCourse(int userID, int courseNumber){
        try {
            Participant user = UserFactory.getUser(userID);
            Course course = CourseFactory.getCourse(courseNumber);
            if(user instanceof Student student){
                if(course.getVacancies()>0) {
                    student.registerToCourse(course);
                    course.addStudent(student);
                }else{
                    System.out.println("Course is full");
                    System.out.println("""
                            Would you like to be added to the wait list?
                            You will be notified when a spot opens up (Y/N)
                            """);
                    Scanner scanner = new Scanner(System.in);
                    String response = scanner.nextLine();
                    if(response.equals("Y")||response.equals("y")){
                        course.addObserver(student);
                    }
                }
            }else {
                throw new IllegalArgumentException("User is not a student");
            }
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void unregisterFromCourse(int userID, int courseNumber){
        try {
            Participant user = UserFactory.getUser(userID);
            Course course = CourseFactory.getCourse(courseNumber);
            if(user instanceof Student student){
                if(course.getVacancies()<1){
                    String message = "Course " + course.getCourseName() + "(Course Number: "+ course.getCourseNumber()+ ") is now available";
                    course.notifyObservers(message);
                }
                student.unregisterFromCourse(course);
                course.removeStudent(student);
            }else {
                throw new IllegalArgumentException("User is not a student");
            }
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    /**************************************************************************************************
     * Methods to register and remove users
     */
    public void registerNewUser(String type, String name, int ID){
        try {
            UserFactory.createUser(type, name, ID);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    public void removeUser(int ID){
        try {
            Participant user = UserFactory.getUser(ID);
            if(user instanceof Student student){
                for(Course course: student.getCourses()){
                    course.removeStudent(student);
                }
            } else if (user instanceof Lecturer lecturer){
                for(Course course: lecturer.getCoursesTaught()){
                    CourseFactory.removeCourse(course.getCourseNumber());
                }
            }
            UserFactory.removeUser(ID);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    /**************************************************************************************************
     * Methods to create and remove courses
     */
    public void createCourse(String courseType, String courseName, int courseID, int maxStudents, int lecturerID) {
        try {
            Participant user = UserFactory.getUser(lecturerID);
            if (user instanceof Lecturer lecturer) {
                CourseFactory.createCourse(courseType, courseName, courseID, maxStudents, lecturer);

            } else if (user instanceof Assistant assistant) {
                System.out.println("Please enter a Lecturer ID to teach the course");
                Scanner scanner = new Scanner(System.in);
                int newLecturerID = scanner.nextInt();
                scanner.nextLine();
                Lecturer newLecturer = (Lecturer) UserFactory.getUser(newLecturerID);
                CourseFactory.createCourse(courseType, courseName, courseID, maxStudents, newLecturer);
            } else {
                throw new IllegalArgumentException("User is not a lecturer, and cannot create a course");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    public void removeCourse(int workerID , int courseNumber){
        try {
            Participant user = UserFactory.getUser(workerID);
            if (user instanceof Lecturer lecturer || user instanceof Assistant) {
                {

                    try {
                        CourseFactory.removeCourse(courseNumber);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**************************************************************************************************
     * Methods to handle the shopping cart
     */
    public void addCourseToCart(int studentID, int courseID){
        try{
            Participant user = UserFactory.getUser(studentID);
            if(user instanceof Student student){
                Course c = CourseFactory.getCourse(courseID);
                try{
                    student.addCourseToCart(c);
                    System.out.println("Course " + c.getCourseName() + " added to cart");
                }
                catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }else {
                throw new IllegalArgumentException("User is not a student");
            }
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    public void removeCourseFromCart(int studentID, int courseID){
        try{
            Participant user = UserFactory.getUser(studentID);
            if(user instanceof Student student){
                Course c = CourseFactory.getCourse(courseID);
                student.removeCourseFromCart(c);
                System.out.println("Course " + c.getCourseName() + " removed from cart");
            }else {
                throw new IllegalArgumentException("User is not a student");
            }
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    public void registerToCoursesInCart(int studentID){
        try{
            Participant user = UserFactory.getUser(studentID);
            if(user instanceof Student student){
                student.registerToCoursesInCart();
            }else {
                throw new IllegalArgumentException("User is not a student");
            }
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    /**************************************************************************************************
     * Methods to add and remove Assistants from a course
     */

    public void addAssistantToCourse(int assistantID, int courseNumber){
        try{
            Participant user = UserFactory.getUser(assistantID);
            if(user instanceof Worker worker){
                Course course = CourseFactory.getCourse(courseNumber);
                course.addStaff(worker);

            }else {
                throw new IllegalArgumentException("User is not a worker");
            }
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    public void removeAssistantFromCourse(int assistantID, int courseNumber){
        try{
            Participant user = UserFactory.getUser(assistantID);
            if(user instanceof Worker worker){
                Course course = CourseFactory.getCourse(courseNumber);
                course.removeStaff(worker);

            }else {
                throw new IllegalArgumentException("User is not a worker");
            }
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    /**************************************************************************************************
     * Methods to display various information
     */
    public void displayNotifications(int observerID){
        try{
            Participant user = UserFactory.getUser(observerID);
            if(user instanceof Student student){
                student.displayNotifications();
            }else {
                throw new IllegalArgumentException("User is not a student");
            }
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void displayStudentsCourses(int studentID){
        try{
            Participant user = UserFactory.getUser(studentID);
            if(user instanceof Student student){
                for(Course course: student.getCourses()){
                    System.out.println(course);
                }
            }else {
                throw new IllegalArgumentException("User is not a student");
            }
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void displayLecturersCourses(int lecturerID){
        try{
            Participant user = UserFactory.getUser(lecturerID);
            if(user instanceof Lecturer lecturer){
                for(Course course: lecturer.getCoursesTaught()){
                    System.out.println(course);
                }
            }else {
                throw new IllegalArgumentException("User is not a lecturer");
            }
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }
    public void displayAllCourses(){
        for(Course course: CourseFactory.getAllCourses()){
            System.out.println(course);
        }
    }
}
