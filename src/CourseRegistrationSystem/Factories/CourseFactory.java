package CourseRegistrationSystem.Factories;

import CourseRegistrationSystem.Courses.Compulsory;
import CourseRegistrationSystem.Courses.Course;
import CourseRegistrationSystem.Courses.Elective;
import CourseRegistrationSystem.Courses.Seminar;

import java.util.HashMap;
import java.util.Scanner;

public class CourseFactory {
    private static final HashMap<Integer, Course> allCourses = new HashMap<Integer, Course>();

    public static Course createCourse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the course type (Compulsory, Elective, Seminar): ");
        String Type = scanner.nextLine();
        System.out.println("Enter the course name: ");
        String courseName = scanner.nextLine();
        System.out.println("Enter the course number: ");
        int courseID = scanner.nextInt();
        System.out.println("Enter the maximum number of students: ");
        int maxStudents = scanner.nextInt();
        Course c = null;
        try {
            c = createCourse(Type, courseName, courseID, maxStudents);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return c;
    }
    public static Course createCourse(String Type, String courseName, int courseNumber, int maxStudents) {

        if (allCourses.containsKey(courseNumber)) {
            return allCourses.get(courseNumber);
        }
        switch (Type) {
            case "Compulsory" -> {
                Course course = new Compulsory(courseName, courseNumber, maxStudents);
                allCourses.put(courseNumber, course);
                return course;
            }
            case "Elective" -> {
                Course course = new Elective(courseName, courseNumber, maxStudents);
                allCourses.put(courseNumber, course);
                return course;
            }
            case "Seminar" -> {
                Course course = new Seminar(courseName, courseNumber, maxStudents);
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



}
