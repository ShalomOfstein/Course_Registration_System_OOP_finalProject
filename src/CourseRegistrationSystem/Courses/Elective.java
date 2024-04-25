package CourseRegistrationSystem.Courses;

import CourseRegistrationSystem.Participants.Worker;

public class Elective extends Course{
    public Elective(String courseName, int courseNum, int maxStudents, Worker lecturer){
        super(courseName, courseNum, maxStudents, lecturer);
    }
}
