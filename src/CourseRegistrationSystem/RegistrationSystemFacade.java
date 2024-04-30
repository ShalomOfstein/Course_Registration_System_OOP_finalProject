package CourseRegistrationSystem;

public class RegistrationSystemFacade {
    public final int MAXUSERS = 100;
    private final RegistrationSystem registrationSystem;

    public RegistrationSystemFacade(){

        registrationSystem = RegistrationSystem.getInstance();
    }
    public RegistrationSystem getRegistrationSystemInstance(){

        return registrationSystem;
    }



    public void register(int userID, int courseNumber){
        registrationSystem.registerToCourse(userID, courseNumber);

    }

    public void unregister(int userID, int courseNumber){
        registrationSystem.unregisterFromCourse(userID, courseNumber);
    }

    public void addCourseToCart(int studentID, int courseID){
        registrationSystem.addCourseToCart(studentID, courseID);
    }
    public void removeCourseFromCart(int studentID, int courseID){
        registrationSystem.removeCourseFromCart(studentID, courseID);
    }
    public void registerToCoursesInCart(int studentID){
        registrationSystem.registerToCoursesInCart(studentID);
    }

}
