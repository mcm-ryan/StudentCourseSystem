package menu;

import com.sun.tools.javac.Main;
import models.*;

/**
 * Student menu.
 * @see MainMenu
 * @see Student
 */
public class StudentMenu {


    // Constructor to disable objects from being created from the class.
    private StudentMenu() {

    }

    /**
     * Method to display the main student menu.
     */
    public static void menu() {

        // prints student menu banner
        System.out.println("\n* * * * * * * * * * * * * * * * * * * *");
        System.out.println("STUDENT MENU");
        System.out.println("* * * * * * * * * * * * * * * * * * * *");

        // variable used to hold user option
        int response;

        // do/while loop that repeatedly prompts user to select an option
        do {
            System.out.println("OPTIONS: (1) Enroll Student, (2) Withdraw Student, (3) View Student, (4) Exit");
            System.out.print("Enter option: ");
            response = MainMenu.input.nextInt();

            // re-prompts user until valid option is entered
            while (response > 4 || response < 1) {
                System.out.print("Enter option (1 - 4): ");
                response = MainMenu.input.nextInt();
            }

            // switch used to redirect user to corresponding function
            switch (response) {
                case 1: // if user enters '1' call "enroll()"
                    enroll();
                    break;

                case 2: // if user enters '2' call "withdraw()"
                    withdraw();
                    break;

                case 3: // if user enters '3' call "viewCourses()"
                    viewCourses();
                    break;

                case 4: // if user enters '4' student menu is exited and user is sent back to main menu
                    break;
            }
        } while (response != 4);
    }


    /**
     * Method to enroll a student in a course.
     */
    private static void enroll() {
        // variable used to store name of student
        String studName;
        // String variable used to store final status of enrollment operation
        String response;

        int index = 0;
        int studID = 0;
        int studentIndex = 0;
        int courseIndex = 0;
        String courseEnr;
        boolean studResult = false;
        boolean courseResult = false;
        boolean courseEnrResult = false;
        boolean duplicateStud = false;

        // checks if there are no courses
        if (MainMenu.courses.size() < 1) {
            System.out.println("No courses available");
            System.out.println();
        } else {
            // asks user if it is a new student
            System.out.print("New student (y/n): ");
            response = MainMenu.input.next();

            // if user inputs 'y', prompts user for student name and course name
            if (response.equalsIgnoreCase("y")) {
                System.out.print("New student name: ");
                studName = MainMenu.input.next();
                System.out.print("Course name: ");
                courseEnr = MainMenu.input.next();

                // loops through courses to see if given course exists, sets "index" to matching course ArrayList index
                for (Course i : MainMenu.courses) {
                    if (courseEnr.equalsIgnoreCase(i.getName())) {
                        index = MainMenu.courses.indexOf(i);
                        courseResult = true;
                        break;
                    }
                }

                // if the course exists, then loops to see if the given student name already is taking this course
                if (courseResult) {
                    for (Student i : MainMenu.courses.get(index).getCourseStudentList()) {
                        if (i.getName().equalsIgnoreCase(studName)) {
                            duplicateStud = true;
                            break;
                        }
                    }

                    // provides message if course is a duplicate
                    if (duplicateStud) {
                        System.out.println("Duplicate course!");
                        System.out.println();

                    // provides message if the course is full
                    } else if (MainMenu.courses.get(index).getCourseStudentList().size() >= Course.MAX) {
                        System.out.println("Course is full!");
                        System.out.println();

                    // if course is not full and not a duplicate, student is enrolled
                    } else {
                        MainMenu.courses.get(index).addStudent(new Student(studName));
                        System.out.println("Student enrolled!");
                        System.out.println();
                    }
                } else {
                    System.out.println("Non existing course!");
                    System.out.println();
                }

            // if the user inputs 'n' to indicate the student already exists...
            } else if (response.equalsIgnoreCase("n")) {

                // user is prompted for a student id
                System.out.print("Student ID: ");
                studID = MainMenu.input.nextInt();

                // loops through to find a student whith a matching student id
                for (Student i : MainMenu.students) {
                    if (studID == (i.getId())) {
                        studentIndex = MainMenu.students.indexOf(i);
                        studResult = true;
                        break;
                    }
                }

                // if the student exists, prompts user for for a course name for enrollment
                if (studResult) {
                    System.out.print("Course name: ");
                    courseEnr = MainMenu.input.next();

                    // loops through to see if the course already exists
                    for (Course i : MainMenu.courses) {
                        if (courseEnr.equalsIgnoreCase(i.getName())) {
                            courseIndex = MainMenu.courses.indexOf(i);
                            courseEnrResult = true;
                            break;
                        }
                    }

                    // if the course already exists, the course's student list is searched for a match with the given student id
                    if (courseEnrResult) {
                        for (Student i : MainMenu.courses.get(courseIndex).getCourseStudentList()) {
                            if (i.getId() == studID) {
                                duplicateStud = true;
                                break;
                            }
                        }

                        // if a match is found, provides user with message that its a duplicate course
                        if (duplicateStud) {
                            System.out.println("Duplicate course!");
                            System.out.println();

                        // provides user with message if the course is full
                        } else if (MainMenu.courses.get(courseIndex).getCourseStudentList().size() >= Course.MAX) {
                            System.out.println("Course is full!");
                            System.out.println();

                        // if not a duplicate and not full, existsing student is enrolled
                        } else {
                            MainMenu.courses.get(courseIndex).addStudent(MainMenu.students.get(studentIndex));
                            System.out.println("Student enrolled!");
                            System.out.println();
                        }
                    } else {
                        System.out.println("Course not found!");
                    }
                } else {
                    System.out.println("Student not found!");
                    System.out.println();
                }
            } else {
                System.out.println("Invalid response!");
            }
        }
    }


    /**
     * Method to withdraw a student from a course.
     */
    private static void withdraw() {
        // variable used to store student id
        int studID = 0;
        // String variable that holds the course intended to be deleted
        String dropCourse;
        // variable used to store the index of student being withdrawn
        int studentIndex = 0;
        // boolean variable used to hold the value of whether the student exists or not
        boolean studResult = false;
        // variable use to store the index of the course being withdrwan from
        int droppedIndex = 0;
        // boolean variable used to store whether the course exists or not
        boolean dropCourseResult = false;
        // boolean variable used to store whether the course given by user
        boolean containsStudent = false;
        // variable used to store the index of student within the list of students enrolled in course given by user
        int studIndex = 0;

        // first checks if there are any courses and then if there are any students
        if (MainMenu.courses.size() < 1) {
            System.out.println("No courses available");
            System.out.println();
        } else {
            if (MainMenu.students.size() < 1) {
                System.out.println("No students available!");
                System.out.println();
            // if so, proceeds with withdrawl process
            } else {

                // prompts user for student id
                System.out.print("Student ID: ");
                studID = MainMenu.input.nextInt();

                // loops through students to find matching student to withdraw
                for (Student i : MainMenu.students) {
                    if (studID == (i.getId())) {
                        studentIndex = MainMenu.students.indexOf(i);
                        studResult = true;
                        break;
                    }
                }

                // if student exists, prompts user for course name
                if (studResult) {
                    System.out.print("Course name: ");
                    dropCourse = MainMenu.input.next();

                    // loops through courses to find matching course name
                    for (Course i : MainMenu.courses) {
                        if (dropCourse.equalsIgnoreCase(i.getName())) {
                            droppedIndex = MainMenu.courses.indexOf(i);
                            dropCourseResult = true;
                            break;
                        }
                    }

                    // loops through that course's students to verify student is in that course
                    for (Student i : MainMenu.courses.get(droppedIndex).getCourseStudentList()) {
                        if (i.getId() == studID) {
                            containsStudent = true;
                            studIndex = MainMenu.courses.get(droppedIndex).getCourseStudentList().indexOf(i);
                            break;
                        }
                    }

                    // if the course exists and contains the student, withdraw student from course
                    if (dropCourseResult && containsStudent) {
                        MainMenu.courses.get(droppedIndex).dropStudent(MainMenu.courses.get(droppedIndex).getCourseStudentList().get(studIndex));
                        System.out.println("Student dropped!");
                        System.out.println();

                    } else {
                        System.out.println("Course not found!");
                        System.out.println();
                    }
                } else {
                    System.out.println("Student not found!");
                    System.out.println();
                }
            }
        }
    }


    /**
     * Method to view the courses a student is enrolled in.
     */
    private static void viewCourses() {

        // boolean variable used to represent whether there is a mtaching student
        boolean validStud = false;

        // varibale used to store student id
        int id = 0;

        // variable used to store index of student within "MainMenu.students"
        int studIndex = 0;

        // checks if no students have been created
        if (MainMenu.students.size() <= 0) {
            System.out.println("No students available!");
            System.out.println();
        } else {
            System.out.print("Student ID: ");
            id = MainMenu.input.nextInt();

            // loops through to find student with matching id
            for (Student i: MainMenu.students) {
                if (i.getId() == id) {
                    validStud = true;
                    studIndex = MainMenu.students.indexOf(i);
                    break;
                }
            }

            // if there is a matching id the corresponding courses for that student are listed
            if (validStud) {
                System.out.println(MainMenu.students.get(studIndex).getStudentCourseListString());
                System.out.println();
            } else {
                System.out.println("Student not found!");
                System.out.println();
            }
        }
    }
}