package CourseRegistrationSystem.Factories;

import CourseRegistrationSystem.Courses.Compulsory;
import CourseRegistrationSystem.Courses.Course;
import CourseRegistrationSystem.Courses.Elective;
import CourseRegistrationSystem.Courses.Seminar;
import CourseRegistrationSystem.Participants.Worker;

import java.util.HashMap;
import java.util.Scanner;

public class CourseFactory {
    private static final HashMap<Integer, Course> allCourses = new HashMap<Integer, Course>();

    public static Course createCourse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the Worker creating this course: ");
        int ID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the course type (Compulsory, Elective, Seminar): ");
        String Type = scanner.nextLine();
        System.out.println("Enter the course name: ");
        String courseName = scanner.nextLine();
        System.out.println("Enter the course number: ");
        int courseID = scanner.nextInt();
        System.out.println("Enter the maximum number of students: ");
        int maxStudents = scanner.nextInt();
        scanner.nextLine();
        Worker staffMember1 = (Worker) UserFactory.getUser(ID);
        Course c = null;
        try {
            c = createCourse(Type, courseName, courseID, maxStudents, staffMember1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return c;
    }
    public static Course createCourse(String Type, String courseName, int courseNumber, int maxStudents, Worker staffMember1) {

        if (allCourses.containsKey(courseNumber)) {
            return allCourses.get(courseNumber);
        }
        switch (Type) {
            case "Compulsory" -> {
                Course course = new Compulsory(courseName, courseNumber, maxStudents, staffMember1);
                allCourses.put(courseNumber, course);
                return course;
            }
            case "Elective" -> {
                Course course = new Elective(courseName, courseNumber, maxStudents, staffMember1);
                allCourses.put(courseNumber, course);
                return course;
            }
            case "Seminar" -> {
                Course course = new Seminar(courseName, courseNumber, maxStudents, staffMember1);
                allCourses.put(courseNumber, course);
                return course;
            }
            default -> {
                throw new IllegalArgumentException("Invalid course type");
            }
        }
    }

    public static Course getCourse(int courseNumber) {
        if (allCourses.containsKey(courseNumber)) {
            return allCourses.get(courseNumber);
        }
        throw new IllegalArgumentException("Course does not exist");
    }

    public static void removeCourse(int courseNumber) {
        if (allCourses.containsKey(courseNumber)) {
            Course course = allCourses.get(courseNumber);
            course.cancelCourse();
            allCourses.remove(courseNumber);

        } else {
            throw new IllegalArgumentException("Course does not exist");
        }
    }



}
