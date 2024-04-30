package CourseRegistrationSystem;
import CourseRegistrationSystem.Courses.Course;
import java.util.ArrayList;

public class ShoppingCart {
    private final ArrayList<Course> courses;

    /**
     * Constructor
     */
    public ShoppingCart(){
        courses = new ArrayList<Course>();
    }

    /***********************************************************************************************
     * methods to add and remove courses from the shopping cart
     */

    public void addCourse(Course course){
        if(!courses.contains(course)){
            courses.add(course);
        }else{
            throw new IllegalArgumentException("Course already in cart");
        }
    }
    public void removeCourse(Course course){
        courses.remove(course);
    }

    /***********************************************************************************************
     * getters
     */
    public ArrayList<Course> getCourses(){
        return courses;
    }

    public boolean isEmpty(){
        return courses.isEmpty();
    }




    public void clearCart(){
        courses.clear();
    }



}
