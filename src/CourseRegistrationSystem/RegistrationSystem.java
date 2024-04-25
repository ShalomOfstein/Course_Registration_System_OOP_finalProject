package CourseRegistrationSystem;

import CourseRegistrationSystem.Courses.Course;
import CourseRegistrationSystem.Factories.CourseFactory;
import CourseRegistrationSystem.Factories.UserFactory;
import CourseRegistrationSystem.Observer_Design_Pattern.Notification;
import CourseRegistrationSystem.Participants.Participant;
import CourseRegistrationSystem.Participants.Student;
import CourseRegistrationSystem.Participants.Worker;

import java.util.ArrayList;

public class RegistrationSystem {
    private static RegistrationSystem instance = null;
    private final ArrayList<Participant> users;
    private final int maxUsers;
    private final int numUsers;

    private RegistrationSystem(int maxUsers){
        this.users = new ArrayList<Participant>();
        this.maxUsers = maxUsers;
        this.numUsers = 0;
    }
    public static RegistrationSystem getInstance(int maxUsers){
        if(instance == null){
            instance = new RegistrationSystem(maxUsers);
        }
        return instance;
    }

    public void createUser(){
        if(numUsers >= this.maxUsers){
            System.out.println("Maximum number of users reached");
            return;
        }
        try{
            Participant user = UserFactory.createUser();
            users.add(user);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    public void createUser(String type,String name, int ID){
        if(numUsers >= this.maxUsers){
            System.out.println("Maximum number of users reached");
            return;
        }
        try{
            Participant user = UserFactory.createUser(type, name, ID);
            users.add(user);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    public void removeUser(int ID){
        Participant user = null;
        try{
            user = UserFactory.getUser(ID);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }if(user != null){
            users.remove(user);
            UserFactory.removeUser(ID);
        }
    }
    public void displayUsers(){
        for(Participant user: users){
            System.out.println(user);
        }
    }
    public void displayUser(int ID){
        Participant user = null;
        try{
            user = UserFactory.getUser(ID);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }if(user != null){
            System.out.println(user);
        }
    }
    public Participant getUser(int ID){
        Participant user = null;
        try{
            user = UserFactory.getUser(ID);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        return user;
    }

    public void addCourse(Worker staffMember1, String courseType, String courseName, int courseNumber, int maxStudents){
        staffMember1.createCourse(courseType, courseName, courseNumber, maxStudents);
    }
    public void removeCourse(int courseNumber){
        Course course = CourseFactory.getCourse(courseNumber);
        if(course != null){
            CourseFactory.removeCourse(courseNumber);
        }
    }
    public void registerToCourse(int courseNumber, int studentID){
        Course course = CourseFactory.getCourse(courseNumber);
        Participant user = UserFactory.getUser(studentID);
        if(course != null && user instanceof Student student){
            course.addStudent(student);
        }
    }
    public Course getCourse(int courseNumber){
        return CourseFactory.getCourse(courseNumber);
    }
    public void unregisterFromCourse(int courseNumber, int studentID){
        Course course = CourseFactory.getCourse(courseNumber);
        Participant user = UserFactory.getUser(studentID);
        if(course != null && user instanceof Student student){
            course.removeStudent(student);
        }
    }

    public void addCourseToCart(int studentID, int courseNumber){
        Course course = CourseFactory.getCourse(courseNumber);
        Participant user = UserFactory.getUser(studentID);
        if(course != null && user instanceof Student student){
            student.addCourseToCart(course);
        }
    }
    public void registerCoursesInCart(int studentID){
        Participant user = UserFactory.getUser(studentID);
        if(user instanceof Student student){
            student.registerCourses();
        }
    }
    public void removeCourseFromCart(int courseNumber, int studentID){
        Course course = CourseFactory.getCourse(courseNumber);
        Participant user = UserFactory.getUser(studentID);
        if(course != null && user instanceof Student student){
            student.removeCourseFromCart(course);
        }
    }

    public void displayNotifications(int studentID) {
        Participant user = UserFactory.getUser(studentID);
        if (user instanceof Student student) {
           ArrayList<Notification> list = student.getNotifications();
           if(list.isEmpty()){
               System.out.println(student.getName() +" has no notifications");
               return;
           }
           System.out.println();
           System.out.println("Notifications for "+student.getName()+":");
           for (Notification note : list) {
               System.out.println(note);
           }
        }
    }

    public void printStudentsCourses(int studentID){
        Participant user = UserFactory.getUser(studentID);
        if(user instanceof Student student){
            System.out.println(student.getName()+"'s courses:");
            for(Course course: student.getCourses()){
                System.out.println(course.getCourseName());
            }
        }
    }

}
