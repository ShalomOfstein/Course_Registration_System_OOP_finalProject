package CourseRegistrationSystem.Factories;
import CourseRegistrationSystem.Participants.*;
import java.util.HashMap;
import java.util.Scanner;

public class UserFactory {
    private static final HashMap<Integer, Participant> allUsers = new HashMap<Integer, Participant>();
    public static Participant createUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the type of user you want to create (Student / Assistant / Lecturer): ");
        String type = scanner.nextLine();
        System.out.println("Please enter the name of the user: ");
        String name = scanner.nextLine();
        System.out.println("Please enter the ID of the user: ");
        int ID = scanner.nextInt();
        return createUser(type, name, ID);
    }
    public static Participant createUser(String type, String name, int ID){
        if(allUsers.containsKey(ID)){
            return allUsers.get(ID);
        }
        switch (type) {
            case "Student" -> {
                Student s = new Student(name, ID);
                allUsers.put(ID, s);
                return s;
            }
            case "Assistant" -> {
                Assistant a = new Assistant(name, ID);
                allUsers.put(ID, a);
                return a;
            }
            case "Lecturer" -> {
                Lecturer l = new Lecturer(name, ID);
                allUsers.put(ID, l);
                return l;
            }
        }
        throw new IllegalArgumentException("Invalid user type");
    }

    public static Participant getUser(int ID){
        if(allUsers.containsKey(ID)){
            return allUsers.get(ID);
        }
        throw new IllegalArgumentException("User not found");
    }

    public static void removeUser(int ID){
        allUsers.remove(ID);
    }

}
