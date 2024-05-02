package CourseRegistrationSystem.Participants;

import CourseRegistrationSystem.Courses.Course;
import CourseRegistrationSystem.Factories.CourseFactory;

import java.util.ArrayList;

public class Lecturer extends Worker implements AdminInterface{
    private final ArrayList<Course> coursesTaught;

    public Lecturer(String name, int ID){
        super(name, ID);
        coursesTaught = new ArrayList<Course>();
        }

    public String toString(){
        return super.toString()+ ", Job: Lecturer";
    }

    public Course createCourse(String courseType, String courseName, int courseID, int maxStudents) {
        return CourseFactory.createCourse(courseType, courseName, courseID, maxStudents, this);
    }
    @Override
    public Course createCourse() {
        Course course = CourseFactory.createCourse();
        coursesTaught.add(course);
        return course;
    }

    public void addCourse(Course course){
        coursesTaught.add(course);
    }
    public ArrayList<Course> getCoursesTaught(){
        return coursesTaught;
    }
}
