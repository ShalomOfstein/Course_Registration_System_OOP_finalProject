package CourseRegistrationSystem.Factories;

import CourseRegistrationSystem.Courses.*;

import java.util.ArrayList;
import java.util.Scanner;

public class CourseFactory {
    private static ArrayList<Course> allCourses;

    public static Course createCourse() {
        if (allCourses == null) {
            allCourses = new ArrayList<Course>();
        }
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
    public static Course createCourse(String Type, String courseName, int courseID, int maxStudents) {
        if (allCourses == null) {
            allCourses = new ArrayList<Course>();
        }
        for(Course c : allCourses){
            if(c.getCourseNumber() == courseID) {
                System.out.println("Course with this ID already exists");
                return c;
            }
        }
        switch (Type) {
            case "Compulsory" -> {
                Course course = new Compulsory(courseName, courseID, maxStudents);
                allCourses.add(course);
                return course;
            }
            case "Elective" -> {
                Course course = new Elective(courseName, courseID, maxStudents);
                allCourses.add(course);
                return course;
            }
            case "Seminar" -> {
                Course course = new Seminar(courseName, courseID, maxStudents);
                allCourses.add(course);
                return course;
            }
            default -> {
                throw new IllegalArgumentException("Invalid course type");
            }
        }
    }

    public static Course assertNewCourse(int ID) {
        for(Course c : allCourses){
            if(c.getCourseNumber() == ID){ //TODO: Check if this is the correct method to compare courses
                return c;
            }
        }
        return null;
    }

}
