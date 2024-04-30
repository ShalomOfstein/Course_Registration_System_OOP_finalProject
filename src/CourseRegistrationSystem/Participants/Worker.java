package CourseRegistrationSystem.Participants;

import CourseRegistrationSystem.Courses.Course;

public abstract class Worker extends Participant{

    protected Worker(String name, int ID){

        super(name, ID);
    }
    public abstract Course createCourse();

//    public Course createCourse() {
//
//        return CourseFactory.createCourse();
//    }
//    public Course createCourse(String courseType, String courseName, int courseID, int maxStudents){
//       return CourseFactory.createCourse(courseType, courseName, courseID, maxStudents, this);
//    }
//    public void cancelCourse(int courseNumber){
//    CourseFactory.removeCourse(courseNumber);
//    }
//
//    public String toString(){
//        return super.toString();
//    }


}
