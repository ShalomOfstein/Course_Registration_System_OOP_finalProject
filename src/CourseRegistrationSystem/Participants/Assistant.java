package CourseRegistrationSystem.Participants;

import CourseRegistrationSystem.Courses.Course;
import CourseRegistrationSystem.Factories.CourseFactory;

public class Assistant extends Worker{


    public Assistant(String name, int ID){
        super(name, ID);
    }

    public String toString(){
        return super.toString()+ ", Job: Assistant";
    }

    public Course createCourse(String courseType, String courseName, int courseID, int maxStudents, Lecturer lecturer) {
        Course course = CourseFactory.createCourse(courseType, courseName, courseID, maxStudents, lecturer);
        lecturer.addCourse(course);
        return course;
    }

    @Override
    public Course createCourse() {
        return CourseFactory.createCourse();
    }

}
