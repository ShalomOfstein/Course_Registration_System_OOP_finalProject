package CourseRegistrationSystem.Courses;

import CourseRegistrationSystem.Participants.Worker;

public class Compulsory extends Course{
    public Compulsory(String courseName, int courseNum, int maxStudents, Worker lecturer){

        super(courseName, courseNum, maxStudents, lecturer);
    }
}
