package CourseRegistrationSystem.Participants;

import CourseRegistrationSystem.Courses.Course;
import CourseRegistrationSystem.Observer_Design_Pattern.Notification;
import CourseRegistrationSystem.Observer_Design_Pattern.Observer;
import CourseRegistrationSystem.ShoppingCart;

import java.util.ArrayList;

public class Student extends Participant implements Observer {

    private final ShoppingCart shoppingCart ;
    private final ArrayList<Course> courses;
    private final ArrayList<Notification> notifications;

    public Student(String name, int ID){
        super(name, ID);
        shoppingCart = new ShoppingCart();
        courses = new ArrayList<Course>();
        notifications = new ArrayList<Notification>();

    }

    public void addCourseToCart(Course course){
        try {
            shoppingCart.addCourse(course);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeCourseFromCart(Course courseName){
        shoppingCart.removeCourse(courseName);
    }

    public void registerCourses(){
        for(Course course: shoppingCart.getCourses()){
            course.addStudent(this);
        }
        courses.addAll(shoppingCart.getCourses());
        shoppingCart.clearCart();
    }
    public void registerToCourse(Course course){
        if(!courses.contains(course)){
            course.addStudent(this);
            courses.add(course);
        }
    }

    public void unregisterCourses(Course course){
        course.removeStudent(this);
        courses.remove(course);
    }

    @Override
    public void update(Notification note) {
        notifications.add(note);
    }
    public ArrayList<Notification> getNotifications(){
        return notifications;
    }




}
