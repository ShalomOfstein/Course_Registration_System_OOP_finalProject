//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import CourseRegistrationSystem.Participants.Worker;
import CourseRegistrationSystem.RegistrationSystem;
import CourseRegistrationSystem.RegistrationSystemFacade;

public class Main {
    public static void main(String[] args) {
        RegistrationSystemFacade facade = new RegistrationSystemFacade();
        RegistrationSystem system = facade.getRegistrationSystemInstance();

        system.createUser("Student","Alice",1); //#1
        system.createUser("Student", "Bob", 2); //#2
        system.createUser("Student", "Charlie", 3); //#3
        system.createUser("Student", "David", 4); //#4
        system.createUser("Student", "Eve", 5); //#5

        //create the staff members
        system.createUser("Lecturer", "Ivan",6); //#6
        system.createUser("Lecturer", "John",7); //#7
        system.createUser("Lecturer", "Kate",8); //#8
        system.createUser("Assistant", "Linda",9); //#9
        system.createUser("Assistant", "Mike",10); //#10



        system.displayUsers();

        system.addCourse((Worker) system.getUser(6), "Elective", "Math", 101, 3);
        system.addCourse((Worker) system.getUser(7), "Seminar", "Science", 102, 10);
        system.addCourse((Worker) system.getUser(8), "Compulsory", "History", 103, 10);
        system.addCourse((Worker) system.getUser(9), "Elective", "English", 104, 10);
        system.addCourse((Worker) system.getUser(10), "Seminar", "Art", 105, 10);

        facade.register(1, 101);
        facade.register(2, 101);
        facade.register(3, 101);
        facade.register(4, 101);
        facade.register(5, 101);

        facade.register(2, 102);
        facade.register(3, 103);
        facade.register(4, 104);
        facade.register(5, 105);

        system.addCourseToCart(1, 102);
        system.addCourseToCart(1, 103);
        system.addCourseToCart(1, 104);
        system.addCourseToCart(1, 105);
        system.registerCoursesInCart(1);

        facade.unregister(1, 101);
        system.removeCourse(102);
        system.removeCourse(103);

        system.displayNotifications(1);
        system.displayNotifications(2);
        system.displayNotifications(3);
        system.displayNotifications(4);
        system.displayNotifications(5);

        system.printStudentsCourses(1);

    }
}