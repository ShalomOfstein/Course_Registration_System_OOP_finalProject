package CourseRegistrationSystem;
import CourseRegistrationSystem.Courses.Course;
import java.util.ArrayList;

public class ShoppingCart {
    private final ArrayList<Course> courses;

    public ShoppingCart(){
        courses = new ArrayList<Course>();
    }

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

    public ArrayList<Course> getCourses(){
        return courses;
    }

    public void clearCart(){
        courses.clear();
    }



}
