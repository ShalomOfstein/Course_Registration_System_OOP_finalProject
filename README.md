# Course Registration System

## Overview

The Course Registration System is a comprehensive software application designed to manage the registration process for university courses. The system allows students to register for courses, manage their course carts, and receive notifications about course availability. Lecturers can create and manage courses, and assistants can be added to or removed from courses. The system ensures that the maximum number of users is controlled and integrates several design patterns to maintain a clean and efficient codebase.

## Features

1. **User Registration**:
    - Register new users (Students, Lecturers, Assistants).
    - Remove users from the system.

2. **Course Management**:
    - Create and remove courses.
    - Register and unregister students from courses.
    - Add and remove assistants from courses.

3. **Shopping Cart**:
    - Add courses to a student's cart.
    - Remove courses from the cart.
    - Register to all courses in the cart.

4. **Notifications**:
    - Students can receive notifications when a course becomes available.

5. **Display Information**:
    - Display courses a student is registered for.
    - Display courses taught by a lecturer.
    - Display all available courses.

## Object-Oriented Principles and Design Patterns

### Singleton Pattern
- **Class**: `RegistrationSystem`
- **Purpose**: Ensures that only one instance of the `RegistrationSystem` exists throughout the application.
- **Usage**: The `getInstance` method provides access to the single instance of `RegistrationSystem`.

### Factory Pattern
- **Classes**: `CourseFactory`, `UserFactory`
- **Purpose**: Encapsulates the creation logic of objects, allowing for easy creation of various types of courses and users.
- **Usage**: `CourseFactory.createCourse`, `UserFactory.createUser`

### Observer Pattern
- **Class**: `Course`, `Student`
- **Purpose**: Allows students to receive notifications when a course they are interested in becomes available.
- **Usage**: `Course.addObserver`, `Course.notifyObservers`

### Facade Pattern
- **Class**: `RegistrationSystemFacade`
- **Purpose**: Provides a simplified interface to the complex subsystem of course registration, making it easier for the client code to interact with the system.
- **Usage**: Methods like `register`, `unregister`, `createCourse` wrap the corresponding methods in `RegistrationSystem`.

## Class Structure

### RegistrationSystem
- Manages overall registration process, user and course management.
- Methods include registering/unregistering students to/from courses, adding/removing courses, and managing the shopping cart.

### RegistrationSystemFacade
- Simplifies interaction with the `RegistrationSystem`.
- Provides high-level methods for registering/unregistering courses, managing the cart, and user/course management.

### CourseFactory
- Creates different types of courses based on input parameters.
- Methods include `createCourse`, `getCourse`, `removeCourse`.

### UserFactory
- Creates different types of users (Students, Lecturers, Assistants).
- Methods include `createUser`, `getUser`, `removeUser`.

### Participant
- Base class for all participants in the system (Students, Lecturers, Assistants).

### Course
- Represents a course in the system.
- Manages students registered in the course and assistants assigned to the course.
- Implements the Observer pattern to notify students about course availability.

### Student
- Extends `Participant`.
- Manages the courses a student is registered for and their shopping cart.
- Implements methods to register/unregister from courses and handle notifications.

### Lecturer
- Extends `Participant`.
- Manages the courses taught by the lecturer.

### Assistant
- Extends `Participant`.

## How to Use the Code

### Setup

1. **Clone the Repository**:
    ```sh
    git clone <repository_url>
    cd CourseRegistrationSystem
    ```

2. **Compile the Code**:
    ```sh
    javac CourseRegistrationSystem/*.java CourseRegistrationSystem/Courses/*.java CourseRegistrationSystem/Factories/*.java CourseRegistrationSystem/Participants/*.java
    ```

3. **Run the Application**:
    ```sh
    java CourseRegistrationSystem.Main
    ```

## Running the Java Code from Command Line

### Prerequisites
Before running the Java code, ensure that you have Java Development Kit (JDK) installed on your system. You can download and install JDK from the official [Java SE Downloads page](https://www.oracle.com/java/technologies/javase-downloads.html).

### Steps to Run
1. **Compile the Java Files:**
   Open your terminal or command prompt and navigate to the directory containing the Java files (`Main.java`, `RegistrationSystem.java`, `RegistrationSystemFacade.java`, etc.).
   Use the `javac` command to compile all the Java files. For example:
   ```sh
   javac *.java
    ```
   This command compiles all the Java files in the current directory.

2. **Run the Main Class:**
   After successful compilation, run the `Main` class using the `java` command. For example:
   ```sh
   java Main
   ```
   This command executes the compiled `Main.class` file, starting the execution of the Course Registration System program.
3. **Follow the On-Screen Instructions:**
   Once the program starts, follow the on-screen instructions to interact with the system. You'll be prompted to choose options such as registering a new user, logging in, adding/removing courses, viewing courses, and more.

4. **Manual Testing (Optional):**
   You can also manually test the system by navigating through the menus and entering data as per your requirements.

### Troubleshooting
- If you encounter any errors during execution, ensure that the Java Virtual Machine (JVM) is properly installed on your system and that your classpath is set correctly.
- Double-check that the `Main.class` file was generated successfully during the compilation step and that it exists in the same directory from which you're executing the `java` command.


### Automated Testing
When running the `Main` class, there is a function called `loadData` that automatically loads data, including creating students, various courses, lecturers, assistants, and changing courses so that some users will receive notifications. Additionally, the function initializes 101 users to ensure that the last user created is not persisted (and then deletes them). This function is intended to quickly illustrate some of the system's capabilities. To test all the capabilities, you can also use the system from the terminal by navigating through the various menus and manually entering data.

### User Interaction
Upon running the `Main`, you'll receive a main menu for registering a new user or logging in as an existing user. After login/registration, you'll receive a menu based on the user type (Student/Lecturer/Assistant). Each menu has different options.

- **For Students**:
    - Add or remove courses from the shopping cart.
    - Register for courses in the shopping cart.
    - View their courses or all existing courses.
    - View notifications.

- **For Lecturers**:
    - Add or remove courses.
    - View the courses they teach or all existing courses.

- **For Assistants**:
    - Similar to lecturers, assistants can add or remove courses, view all existing courses, and add or remove themselves as assistants for a course.

### Manual Testing
For comprehensive testing, you can use the system from the terminal, navigate through the menus, and manually input data.


## Conclusion

The Course Registration System provides a robust and flexible framework for managing university course registrations. By leveraging key object-oriented principles and design patterns, the system ensures maintainability, scalability, and ease of use. The facade pattern simplifies the interaction with the system, making it accessible for various clients without exposing the underlying complexity.
