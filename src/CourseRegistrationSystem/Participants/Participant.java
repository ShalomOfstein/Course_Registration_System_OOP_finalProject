package CourseRegistrationSystem.Participants;

public abstract class Participant {
    private final String name;
    private final int ID;

    protected Participant(String name, int ID){
        this.name = name;
        this.ID = ID;
    }

}