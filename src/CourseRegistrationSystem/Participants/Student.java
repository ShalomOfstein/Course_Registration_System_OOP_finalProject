package CourseRegistrationSystem.Participants;

import CourseRegistrationSystem.Courses.Course;
import CourseRegistrationSystem.Observer_Design_Pattern.Observer;
import CourseRegistrationSystem.ShoppingCart;
import CourseRegistrationSystem.Observer_Design_Pattern.Notification;

import java.util.ArrayList;
import java.util.Scanner;

public class Student extends Participant implements Observer {

    private ShoppingCart shoppingCart ;
    private ArrayList<Course> courses;
    private ArrayList<Notification> notifications;

    public Student(String name, int ID){
        super(name, ID);
        shoppingCart = new ShoppingCart();
        courses = new ArrayList<Course>();

    }

    public void addCourseToCart(Course course){
        try {
            shoppingCart.addCourse(course);
        } catch (IllegalArgumentException e) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Course already in cart\n" +
                    "Do you want to get a notification when the course is available? (Y/N)");
            String response = scanner.nextLine();
            if(response.equals("Y")||response.equals("y")) {
                course.addObserver(this);
            }

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

    public void unregisterCourses(Course course){
        course.removeStudent(this);
        courses.remove(course);
    }

    @Override
    public void update(Notification note) {
        notifications.add(note);
    }




}
