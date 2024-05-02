//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import CourseRegistrationSystem.Factories.UserFactory;
import CourseRegistrationSystem.Participants.Participant;
import CourseRegistrationSystem.RegistrationSystem;
import CourseRegistrationSystem.RegistrationSystemFacade;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RegistrationSystemFacade facade = new RegistrationSystemFacade();
        RegistrationSystem system = facade.getRegistrationSystemInstance();
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                            Welcome to the Course Registration System!
                            do you want to load pre-existing data? (y/n)""");
        String response = scanner.nextLine();
        if(response.equals("y")||response.equals("Y")){
            loadData(facade, system);
        }

        int choice = -1;
        while (choice != 0) {
            System.out.println("""
                            
                            Do you want to create a new user or sign in?
                                1. Create a new user
                                2. Sign in
                                To exit the system, enter 0""");
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                registerNewUser(facade, system);
            } else if (choice == 2) {
                signIn(facade, system);
            } else if (choice == 0) {
                System.out.println("Exiting System!");
                return;
            } else {
                System.out.println("Invalid input");
            }
        }


//        system.displayUsers();


//        system.addCourseToCart(1, 102);
//        system.addCourseToCart(1, 103);
//        system.addCourseToCart(1, 104);
//        system.addCourseToCart(1, 105);
//        system.registerCoursesInCart(1);


//        system.displayNotifications(1);
//        system.displayNotifications(2);
//        system.displayNotifications(3);
//        system.displayNotifications(4);
//        system.displayNotifications(5);

//        system.printStudentsCourses(1);

    }

    public static void loadData(RegistrationSystemFacade facade, RegistrationSystem system){
        System.out.println("Loading pre-existing data");
        System.out.println("-> Adding 101 students");
        System.out.println("   this should fail as the system can only hold 100 students");
        for(int i=0 ; i<101 ; i++){
            system.registerNewUser("Student", "Student"+i, i);
        }
        System.out.println("-> Removing 101 students");
        System.out.println("   this should fail as the last student was not created");
        for(int i=0; i<101 ; i++){
            system.removeUser(i);
        }

        system.registerNewUser("Student","Alice",1); //#1
        system.registerNewUser("Student", "Bob", 2); //#2
        system.registerNewUser("Student", "Charlie", 3); //#3
        system.registerNewUser("Student", "David", 4); //#4
        system.registerNewUser("Student", "Eve", 5); //#5
        System.out.println("-> Added 5 students: Alice (ID: 1), Bob (ID: 2), Charlie (ID: 3), David (ID: 4), Eve (ID: 5)");

        //create the staff members
        system.registerNewUser("Lecturer", "Ivan",6); //#6
        system.registerNewUser("Lecturer", "John",7); //#7
        system.registerNewUser("Lecturer", "Kate",8); //#8
        system.registerNewUser("Assistant", "Linda",9); //#9
        system.registerNewUser("Assistant", "Mike",10); //#10
        System.out.println("-> Added 3 lecturers: Ivan (ID: 6), John (ID: 7), Kate (ID: 8) and 2 assistants: Linda (ID: 9), Mike (ID: 10)");

        system.createCourse("Elective", "Math", 101, 3,6);
        system.createCourse("Seminar", "Science", 102, 10,7);
        system.createCourse("Compulsory", "History", 103, 10,8);
        system.createCourse("Elective", "English", 104, 10,6);
        system.createCourse( "Seminar", "Art", 105, 10,7);
        System.out.println("-> Added 5 courses: Math 101, Science 102, History 103, English 104, Art 105");

        facade.register(1, 101);
        facade.register(2, 101);
        facade.register(3, 101);
//        System.out.println("********* Registering David to course 101. This should fail as the course is full *********");
        facade.register(4, 101, true);
//        System.out.println("********* Registering Eve to course 101. This should fail as the course is full *********");
        facade.register(5, 101, true);
        System.out.println(" ! Math 101 is full, only Alice, Bob and Charlie are registered to it");
        System.out.println(" ! David and Eve tried to sign up to Math 101 but it was full and they opted to receive a notification when there is room");


        facade.register(2, 102);
        facade.register(3, 103);
        facade.register(4, 104);
        facade.register(5, 105);
        System.out.println("-> Bob is registered to Science 102, Charlie is registered to History 103, David is registered to English 104, Eve is registered to Art 105");


        facade.unregister(1, 101);
        system.removeCourse(6,102);
        system.removeCourse(9,103);
        System.out.println(" ! Alice unregistered from Math 101, So David and Eve should have received a notification");
        System.out.println(" ! Ivan removed Science 102, John removed History 103, so Bob and Charlie should have received a notification");


        system.createCourse("Seminar", "Science", 102, 10,7);
        system.createCourse("Compulsory", "History", 103, 10,8);
        facade.register(2, 102);
        facade.register(3, 103);
        System.out.println("-> John added Science 102 and Kate added History 103, Bob and Charlie re-registered to them");

    }
    public static void registerNewUser(RegistrationSystemFacade facade, RegistrationSystem system){
        Scanner scanner = new Scanner(System.in);
        int userType = -1;
        while(userType!=0){
            System.out.println("""
                            
                            Register new user page:
                                What type of user would you like to create?
                                1. Student
                                2. Lecturer
                                3. Assistant
                                To return to the main menu, enter 0""");
            userType = scanner.nextInt();
            scanner.nextLine();
            if(userType==0){
                return;
            }
            System.out.println("Enter your name: ");
            String name = scanner.nextLine();
            System.out.println("Enter your ID: ");
            int ID = scanner.nextInt();
            scanner.nextLine();
            switch (userType) {
                case 1 -> {
                    system.registerNewUser("Student", name, ID);
                    System.out.println("Welcome " + name);
                    studentMenu(facade, system, ID);
                }
                case 2 -> {
                    system.registerNewUser("Lecturer", name, ID);
                    System.out.println("Welcome " + name);
                    lecturerMenu(facade, system, ID);
                }
                case 3 -> {
                    system.registerNewUser("Assistant", name, ID);
                    System.out.println("Welcome " + name);
                    assistantMenu(facade, system, ID);
                }
                default -> {
                    System.out.println("Invalid input");
                }
            }
        }
    }
    public static void signIn(RegistrationSystemFacade facade,RegistrationSystem system){
        Scanner scanner = new Scanner(System.in);
        int userType = -1;
        while(userType!=0) {
            System.out.println("""
                        
                   Sign in page:
                        How would you like to sign in?
                        1. Student
                        2. Lecturer
                        3. Assistant
                    To return to the main menu, enter 0""");
            userType = scanner.nextInt();
            scanner.nextLine();
            if(userType==0){
                return;
            }
            System.out.println("Enter your name: ");
            String name = scanner.nextLine();
            System.out.println("Enter your ID: ");
            int ID = scanner.nextInt();
            scanner.nextLine();
            try {
                Participant user = UserFactory.getUser(ID);
                if (user.getName().equals(name)) {
                    System.out.println("Welcome " + name);
                    switch (userType) {
                        case 1 -> {
                            studentMenu(facade, system, ID);
                        }
                        case 2 -> {
                            lecturerMenu(facade, system, ID);
                        }
                        case 3 -> {
                            assistantMenu(facade, system, ID);
                        }
                        default -> {
                            System.out.println("Invalid input");
                        }
                    }
                } else {
                    System.out.println("Invalid ID or name");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }

        }
    }
    public static void studentMenu(RegistrationSystemFacade facade, RegistrationSystem system, int ID){
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {
            System.out.println("""
                
                Welcome to the student menu! What would you like to do?
                    1. Add a course to your cart
                    2. Remove a course from your cart
                    3. Register for courses in your cart
                    4. View your courses
                    5. View all courses
                    6. View notifications
                    0. Exit""");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> {
                    System.out.println("Enter the course number: ");
                    int courseNumber = scanner.nextInt();
                    scanner.nextLine();
                    facade.addCourseToCart(ID, courseNumber);
                }
                case 2 -> {
                    System.out.println("Enter the course number: ");
                    int courseNumber = scanner.nextInt();
                    scanner.nextLine();
                    facade.removeCourseFromCart(ID, courseNumber);
                }
                case 3 -> {
                    facade.registerToCoursesInCart(ID);
                }
                case 4 -> {
                    system.displayStudentsCourses(ID);
                }
                case 5 -> {
                    system.displayAllCourses();
                }
                case 6 -> {
                    system.displayNotifications(ID);
                }
                case 0 -> {
                    return;
                }
                default -> {
                    System.out.println("Invalid input");
                }
            }
        }

    }
    public static void lecturerMenu(RegistrationSystemFacade facade, RegistrationSystem system, int ID){
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {
            System.out.println("""
                
                Welcome to the lecturer menu! What would you like to do?
                    1. Create a course
                    2. Remove a course
                    3. View your courses
                    4. View all courses
                    0. Exit""");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> {
                    createCourse(facade, system, ID);
                }
                case 2 -> {
                    System.out.println("Enter the course number: ");
                    int courseNumber = scanner.nextInt();
                    scanner.nextLine();
                    system.removeCourse(ID, courseNumber);
                }
                case 3 -> {
                    system.displayLecturersCourses(ID);
                }
                case 4 -> {
                    system.displayAllCourses();
                }
                case 0 -> {
                    return;
                }
                default -> {
                    System.out.println("Invalid input");
                }
            }
        }
    }
    public static void assistantMenu(RegistrationSystemFacade facade, RegistrationSystem system, int ID){
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {
            System.out.println("""
                
                Welcome to the assistant menu! What would you like to do?
                    1. Create a course
                    2. Remove a course
                    3. Add yourself as an assistant to a course
                    4. Remove yourself as an assistant from a course
                    5. View all courses
                    0. Exit""");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> {
                    createCourse(facade, system, ID);
                }
                case 2 -> {
                    System.out.println("Enter the course number: ");
                    int courseNumber = scanner.nextInt();
                    scanner.nextLine();
                    system.removeCourse(ID, courseNumber);
                }
                case 3 -> {
                    System.out.println("Enter the course number: ");
                    int courseNumber = scanner.nextInt();
                    scanner.nextLine();
                    system.addAssistantToCourse(ID, courseNumber);
                }
                case 4 -> {
                    System.out.println("Enter the course number: ");
                    int courseNumber = scanner.nextInt();
                    scanner.nextLine();
                    system.removeAssistantFromCourse(ID, courseNumber);
                }
                case 5 -> {
                    system.displayAllCourses();
                }
                case 0 -> {
                    return;
                }
                default -> {
                    System.out.println("Invalid input");
                }
            }
        }

    }
    public static void createCourse(RegistrationSystemFacade facade, RegistrationSystem system, int ID){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the course type (Compulsory, Elective, Seminar): ");
        String courseType = scanner.nextLine();
        System.out.println("Enter the course name: ");
        String courseName = scanner.nextLine();
        System.out.println("Enter the course number: ");
        int courseNumber = scanner.nextInt();
        System.out.println("Enter the maximum number of students: ");
        int maxStudents = scanner.nextInt();
        scanner.nextLine();
        system.createCourse(courseType, courseName, courseNumber, maxStudents, ID);
    }
}