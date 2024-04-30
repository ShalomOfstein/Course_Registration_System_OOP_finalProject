package CourseRegistrationSystem.Courses;

import CourseRegistrationSystem.Participants.Lecturer;

public class Seminar extends Course{
    public Seminar(String courseName, int courseNum, int maxStudents, Lecturer lecturer){
        super(courseName, courseNum, maxStudents, lecturer);
    }
}
