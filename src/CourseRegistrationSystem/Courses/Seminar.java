package CourseRegistrationSystem.Courses;

import CourseRegistrationSystem.Participants.Worker;

public class Seminar extends Course{
    public Seminar(String courseName, int courseNum, int maxStudents, Worker lecturer){
        super(courseName, courseNum, maxStudents, lecturer);
    }
}
