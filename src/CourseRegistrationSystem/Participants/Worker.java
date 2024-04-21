package CourseRegistrationSystem.Participants;

import CourseRegistrationSystem.Factories.CourseFactory;
import CourseRegistrationSystem.Courses.Course;

public abstract class Worker extends Participant{

    protected Worker(String name, int ID){
        super(name, ID);
    }

    public void createCourse() {
        CourseFactory.createCourse();
    }
    public Course createCourse(String courseType, String courseName, int courseID, int maxStudents){
       return CourseFactory.createCourse(courseType, courseName, courseID, maxStudents);
    }


}
