package CourseRegistrationSystem.Participants;

public class Assistant extends Worker{


    public Assistant(String name, int ID){
        super(name, ID);
    }

    public String toString(){
        return super.toString()+ ", Job: Assistant";
    }

}
