package CourseRegistrationSystem;
import CourseRegistrationSystem.Factories.UserFactory;
import CourseRegistrationSystem.Participants.Participant;

import java.util.ArrayList;

public class RegistrationSystem {
    private RegistrationSystem instance = null;
    private final ArrayList<Participant> users;
    private final int numUsers;

    private RegistrationSystem(){
        this.users = new ArrayList<Participant>();
        this.numUsers = 100;
    }
    public RegistrationSystem getInstance(){
        if(instance == null){
            instance = new RegistrationSystem();
        }
        return instance;
    }

    public void createUser(){
        if(numUsers >= 100){
            System.out.println("Maximum number of users reached");
            return;
        }
        try{
            Participant user = UserFactory.createUser();
            users.add(user);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }
    public void removeUser(int ID){
        Participant user = null;
        try{
            user = UserFactory.getUser(ID);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }if(user != null){
            users.remove(user);
            UserFactory.removeUser(ID);
        }
    }





}
