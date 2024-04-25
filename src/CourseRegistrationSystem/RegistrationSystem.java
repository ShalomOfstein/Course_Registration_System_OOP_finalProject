package CourseRegistrationSystem;

import CourseRegistrationSystem.Courses.Course;
import CourseRegistrationSystem.Factories.CourseFactory;
import CourseRegistrationSystem.Factories.UserFactory;
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
    public void unregisterFromCourse(int courseNumber, int studentID){
        Course course = CourseFactory.getCourse(courseNumber);
        Participant user = UserFactory.getUser(studentID);
        if(course != null && user instanceof Student student){
            course.removeStudent(student);
        }
    }

    public void addCourseToCart(int courseNumber, int studentID){
        Course course = CourseFactory.getCourse(courseNumber);
        Participant user = UserFactory.getUser(studentID);
        if(course != null && user instanceof Student student){
            student.addCourseToCart(course);
        }
    }
    public void removeCourseFromCart(int courseNumber, int studentID){
        Course course = CourseFactory.getCourse(courseNumber);
        Participant user = UserFactory.getUser(studentID);
        if(course != null && user instanceof Student student){
            student.removeCourseFromCart(course);
        }
    }
}
