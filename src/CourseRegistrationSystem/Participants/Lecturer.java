package CourseRegistrationSystem.Participants;

public class Lecturer extends Worker{

    public Lecturer(String name, int ID){
            super(name, ID);
        }

    public String toString(){
        return super.toString()+ ", Job: Lecturer";
    }
}
