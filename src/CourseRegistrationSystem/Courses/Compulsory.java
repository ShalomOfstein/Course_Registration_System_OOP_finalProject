package CourseRegistrationSystem.Courses;

import CourseRegistrationSystem.Participants.Lecturer;

public class Compulsory extends Course{
    public Compulsory(String courseName, int courseNum, int maxStudents, Lecturer lecturer){

        super(courseName, courseNum, maxStudents, lecturer);
    }
}
