package CourseRegistrationSystem.Participants;

import CourseRegistrationSystem.Courses.Course;
import CourseRegistrationSystem.Observer_Design_Pattern.Notification;
import CourseRegistrationSystem.Observer_Design_Pattern.Observer;
import CourseRegistrationSystem.ShoppingCart;

import java.util.ArrayList;

public class Student extends Participant implements Observer, StudentInterface{

    private final ShoppingCart shoppingCart ;
    private final ArrayList<Course> registeredCourses;
    private final ArrayList<Notification> notifications;

    public Student(String name, int ID){
        super(name, ID);
        shoppingCart = new ShoppingCart();
        registeredCourses = new ArrayList<Course>();
        notifications = new ArrayList<Notification>();

    }

    /******************************************************************************************
     * getters
     */
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
    public ArrayList<Course> getCourses() {
        return registeredCourses;
    }
    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    /******************************************************************************************
     * methods to implement the observer pattern
     */

    @Override
    public void update(Notification note) {
        notifications.add(note);
    }
    /******************************************************************************************
     * methods to add and remove courses from the shopping cart
     */
    public void addCourseToCart(Course course) {
        try {
            if(registeredCourses.contains(course)){
                throw new IllegalArgumentException("Already registered to "+course.getCourseName());
            }
            shoppingCart.addCourse(course);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    public void removeCourseFromCart(Course courseName) {
        shoppingCart.removeCourse(courseName);
    }
    /******************************************************************************************
     * methods to register and unregister from courses
     */
    public void registerToCoursesInCart() {
        if(shoppingCart.isEmpty()){
            System.out.println("No courses in cart");
            return;
        }
        System.out.println("Registered to: ");
        for (Course course : shoppingCart.getCourses()) {
            if(registeredCourses.contains(course)){
                System.out.println(course.getCourseName() + " already registered");
                continue;
            }
            course.addStudent(this);
            registeredCourses.add(course);
            System.out.println(course.getCourseName());
        }
        shoppingCart.clearCart();
    }

    /******************************************************************************************
     * methods to register and unregister from courses
     */
    @Override
    public void registerToCourse(Course course){
        if(!registeredCourses.contains(course)){
            registeredCourses.add(course);
        }
    }
    public void unregisterFromCourse(Course course){
        registeredCourses.remove(course);
    }

    /******************************************************************************************
     * method to display notifications
     */
    public void displayNotifications() {
        if(notifications.isEmpty()){
            System.out.println("No notifications for "+getName());
            return;
        }
        System.out.println("Notifications for " + getName() + ":");
        for (Notification note : notifications) {
            System.out.println("From "+note.getSender()+" : "+note.getMessage());
        }
    }

}
