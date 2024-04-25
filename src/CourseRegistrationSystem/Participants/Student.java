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
    public String getName(){
        return super.getName();
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

        shoppingCart.clearCart();
    }
    public void registerToCourse(Course course){
        if(!courses.contains(course)){
            courses.add(course);
            course.addStudent(this);
        }
    }

    public void unregisterCourses(Course course){
        courses.remove(course);
        course.removeStudent(this);
    }

    @Override
    public void update(Notification note) {
        notifications.add(note);
    }
    public ArrayList<Notification> getNotifications(){
        return notifications;
    }

    public ArrayList<Course> getCourses(){
        return courses;
    }

    public String toString(){
        return "Student: "+ super.toString();
    }




}
