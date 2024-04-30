package CourseRegistrationSystem.Factories;

import CourseRegistrationSystem.Courses.Compulsory;
import CourseRegistrationSystem.Courses.Course;
import CourseRegistrationSystem.Courses.Elective;
import CourseRegistrationSystem.Courses.Seminar;
import CourseRegistrationSystem.Participants.Lecturer;
import CourseRegistrationSystem.Participants.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CourseFactory {
    private static final HashMap<Integer, Course> allCourses = new HashMap<Integer, Course>();

    public static Course createCourse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the Lecturer teaching this course: ");
        int ID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the course type (either: Compulsory, Elective, Seminar): ");
        String Type = scanner.nextLine();
        System.out.println("Enter the name of the course: ");
        String courseName = scanner.nextLine();
        System.out.println("Enter the course number: ");
        int courseID = scanner.nextInt();
        System.out.println("Enter the maximum number of students in the course: ");
        int maxStudents = scanner.nextInt();
        scanner.nextLine();
        Lecturer staffMember1 = (Lecturer) UserFactory.getUser(ID);
        Course c = null;
        try {
            c = createCourse(Type, courseName, courseID, maxStudents, staffMember1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return c;
    }
    public static Course createCourse(String Type, String courseName, int courseNumber, int maxStudents, Lecturer lecturer) {

        if (allCourses.containsKey(courseNumber)) {
            return allCourses.get(courseNumber);
        }
        switch (Type) {
            case "Compulsory" -> {
                Course course = new Compulsory(courseName, courseNumber, maxStudents, lecturer);
                allCourses.put(courseNumber, course);
                lecturer.addCourse(course);
                return course;
            }
            case "Elective" -> {
                Course course = new Elective(courseName, courseNumber, maxStudents, lecturer);
                allCourses.put(courseNumber, course);
                lecturer.addCourse(course);
                return course;
            }
            case "Seminar" -> {
                Course course = new Seminar(courseName, courseNumber, maxStudents, lecturer);
                allCourses.put(courseNumber, course);
                lecturer.addCourse(course);
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
            course.clearObservers();
            for(Student student: course.getStudents()){
                student.unregisterFromCourse(course);
            }
            course.notifyStudents("Course has been cancelled");
            course.clearStaff();
            course.clearStudents();
            course.cancelCourse();
            allCourses.remove(courseNumber);
        } else {
            throw new IllegalArgumentException("Course does not exist");
        }
    }

    public static ArrayList<Course> getAllCourses() {
        return new ArrayList<Course>(allCourses.values());
    }

}
