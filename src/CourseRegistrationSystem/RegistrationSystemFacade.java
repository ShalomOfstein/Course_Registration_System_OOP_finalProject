package CourseRegistrationSystem;

public class RegistrationSystemFacade {
    public final int MAXUSERS = 100;
    private final RegistrationSystem registrationSystem;

    public RegistrationSystemFacade(){
        registrationSystem = RegistrationSystem.getInstance(MAXUSERS);
    }
    public RegistrationSystem getRegistrationSystemInstance(){
        return registrationSystem;
    }
    public void register(int userID, int courseNumber){
        registrationSystem.registerToCourse(courseNumber, userID);

    }

    public void unregister(int userID, int courseNumber){
        registrationSystem.unregisterFromCourse(courseNumber, userID);
    }


}
