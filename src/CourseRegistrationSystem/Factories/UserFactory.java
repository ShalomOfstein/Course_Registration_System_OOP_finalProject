package CourseRegistrationSystem.Factories;

import CourseRegistrationSystem.Participants.*;

import java.util.Scanner;

public class UserFactory {
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
        if(type.equals("Student")){
            return new Student(name, ID);
        }
        else if(type.equals("Assistant")){
            return new Assistant(name, ID);
        }
        else if(type.equals("Lecturer")){
            return new Lecturer(name, ID);
        }
        throw new IllegalArgumentException("Invalid user type");
    }

}
