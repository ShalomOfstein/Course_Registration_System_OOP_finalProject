package CourseRegistrationSystem.Courses;

import CourseRegistrationSystem.Participants.Lecturer;

public class Elective extends Course{
    public Elective(String courseName, int courseNum, int maxStudents, Lecturer lecturer){
        super(courseName, courseNum, maxStudents, lecturer);
    }
}
