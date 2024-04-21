package CourseRegistrationSystem;
import CourseRegistrationSystem.Factories.UserFactory;
import CourseRegistrationSystem.Participants.Participant;

import java.util.ArrayList;

public class RegistrationSystem {
    private RegistrationSystem instance = null;
    private ArrayList<Participant> users;

    private RegistrationSystem(){
        this.users = new ArrayList<Participant>();
    }
    public RegistrationSystem getInstance(){
        if(instance == null){
            instance = new RegistrationSystem();
        }
        return instance;
    }

    public void createUser(){
        try{
            Participant user = UserFactory.createUser();
            users.add(user);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }


}
