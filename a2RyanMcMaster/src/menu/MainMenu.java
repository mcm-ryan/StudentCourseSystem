package menu;

import models.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class responsible for entire program.
 * @see CourseMenu
 * @see StudentMenu
 */
public class MainMenu {


    // Variable responsible to enable user to enter input from the keyboard.
    public static Scanner input = new Scanner(System.in);

    // ArrayList to store all the courses created.
    public static ArrayList<Course> courses = new ArrayList<>();

    // ArrayList to store all the students created.
    public static ArrayList<Student> students = new ArrayList<>();


    // Constructor to disable objects from being created from the class.
    private MainMenu() {

    }

    /**
     * Main menu.
     * @param args command line arguments.
     */
    public static void main(String[] args) {

        //variable that holds value user input
        int response;

        // Loop that continues to prompt user for option
        do {

            // sets "response" to a function call "mainMenu()" that displays a menu
            response = mainMenu();

            // switch used to check value of "response" and redirect to corresponding option output
            switch (response) {
                case 1: //if user inputs '1' send them to the course menu
                    CourseMenu.menu();
                    break;

                case 2: //if user inputs '2' send them to the student menu
                    StudentMenu.menu();
                    break;

                case 3: //if user inputs '3' provide them a summary
                    summary();
                    break;

                case 4: //if user inputs '4' exit the program
                    System.out.println();
                    System.out.println("* * * * * * * * * * * * * * * * * * * *");
                    System.out.println("END OF PROGRAM");
                    System.out.println("* * * * * * * * * * * * * * * * * * * *");
                    break;
            }
        } while (response != 4);
    }

    /**
     * Method to display the main menu.
     * The method checks if a user's input is a valid menu option.
     * If it isn't, the program keeps asking the user for an option until they enter something valid.
     *
     * @return the valid option selected by the user back to the main method to determine the next course of action.
     */
    private static int mainMenu() {

        // prints the menu banner
        System.out.println();
        System.out.println("* * * * * * * * * * * * * * * * * * * *");
        System.out.println("MAIN MENU");
        System.out.println("* * * * * * * * * * * * * * * * * * * *");

        // prompts user for their input (1-4)
        System.out.println("OPTIONS: (1) Course menu, (2) Student menu, (3) Summary, (4) Exit");
        System.out.print("Enter option: ");
        int opt = input.nextInt();

        // Loop to keep asking user for valid input while input is invalid
        while(opt > 4 || opt < 1) {
            System.out.print("Enter option (1 - 4): ");
            opt = input.nextInt();
        }

        return opt;
    }

    /**
     * Method to display the summary of courses and students.
     */
    private static void summary() {

        System.out.println("\n- - - - - - - - - - - - - - - - - - - -");
        System.out.println("COURSE SUMMARY");

        // For each course, displays the course name, students enrolled vs maximum course size, the student ID and student name
        for(int i = 0; i < courses.size(); i++) {
            Course currentCourse = courses.get(i);
            ArrayList<Student> stud = currentCourse.getCourseStudentList();
            System.out.println("*****" + currentCourse.getName() + ": (" + stud.size() + "/" + Course.MAX + ")");
            for(int d = 0; d < stud.size(); d++) {
                System.out.println(stud.get(d).getId() + " - " + stud.get(d).getName());
            }
        }
        System.out.println("- - - - - - - - - - - - - - - - - - - -");

        // prints out course size
        System.out.println("Course Total: " + courses.size());
        System.out.println("Student Total: " + students.size());
    }
}
