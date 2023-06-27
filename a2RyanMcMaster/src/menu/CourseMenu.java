package menu;
import models.*;

/**
 * Course menu.
 * @see MainMenu
 * @see Course
 */
public class CourseMenu {


    // constructor to disable objects from being created from the class
    private CourseMenu() {
    }

    /**
     * Method to display the main course menu.
     */
    public static void menu() {

        // prints course menu banner
        System.out.println("\n* * * * * * * * * * * * * * * * * * * *");
        System.out.println("COURSE MENU");
        System.out.println("* * * * * * * * * * * * * * * * * * * *");

        // variable "response" created to store user input
        int response;

        // do/while used to repeatedly prompt user for menu options until user exits the course menu
        do {

            //prompt user to choose a menu option and sets "response" to the user's input
            System.out.println("OPTIONS: (1) Add course, (2) Delete course, (3) Change course, (4) Exit menu");
            System.out.print("Enter option: ");
            response = MainMenu.input.nextInt();

            // checks for valid input from user
            while(response > 4 || response < 1) {
                System.out.print("Enter option (1 - 4): ");
                response = MainMenu.input.nextInt();
            }

            // switch used to redirect user to corresponding function
            switch (response) {

                case 1: // if user enters '1' call "addCourse()"
                    addCourse();
                    break;

                case 2: // if user enters '2' call "deleteCourse()"
                    deleteCourse();
                    break;

                case 3: // if user enters '3' call "changeCourse()"
                    changeCourse();
                    break;

                case 4: // if user enters '4' loop breaks and user returns to the main menu
                    break;
            }
        } while (response != 4);
    }

    /**
     * Method to add a new course.
     */
    private static void addCourse() {

        // boolean variable used to determine if course is a duplicate
        boolean result = true;

        // prompts user for a course name
        System.out.print("Course Name: ");
        String response = MainMenu.input.next();

        // loops through courses to find whether this course already exists
        for (Course i : MainMenu.courses) {
            if (response.equalsIgnoreCase(i.getName())) {
                // sets "result" to false if the course already exists
                result = false;
                break;
            }
        }

        // checks result and inform user of adding course status (adds course if course does not yet exist)
        if (result) {
            System.out.println(response + " added!\n");
            new Course(response);
        } else {
            System.out.println(response + " already exists!\n");
        }
    }

    /**
     * Method to delete an existing course.
     * If there are no courses available, displays a message letting the user know there is nothing to delete and exits out of the method.
     */
    private static void deleteCourse() {

        // boolean variable used to determine if course exists
        boolean result = false;

        // string used to store name of course intended to be deleted
        String ans;

        int index = 0;

        // prompts user for course name
        System.out.print("Course Name: ");
        ans = MainMenu.input.next();


        if (MainMenu.courses.size() >= 1) {

            // loops through all the courses in the ArrayList and searches for match with "ans"
            for (Course i : MainMenu.courses) {
                // when match is found "result" is set to true and the index of the match within the ArrayList is collected
                if (ans.equalsIgnoreCase(i.getName())) {
                    result = true;
                    index = MainMenu.courses.indexOf(i);
                    break;
                }
            }

            // if match is found, the intended course is deleted
            if (result) {
                System.out.println(ans + " deleted!");
                MainMenu.courses.remove(index);
                // Otherwise, the user is informed the course does not exist
            } else {
                do {
                    System.out.println(ans + " does not exist!");

                    // user is re-prompted for course name
                    System.out.print("Course name: ");
                    ans = MainMenu.input.next();

                    // loops through all the courses in the ArrayList and searches for match with "ans"
                    for (Course i : MainMenu.courses) {
                        if (ans.equalsIgnoreCase(i.getName())) {
                            index = MainMenu.courses.indexOf(i);
                            result = true;
                            break;
                        }
                    }

                    // if match is found, the intended course is deleted
                    if (result) {
                        System.out.println(ans + " deleted!");
                        MainMenu.courses.remove(index);
                    }

                    // loops until a matching course is entered
                } while (!result);
            }
            // else that catches if no courses exist
        } else {
            System.out.println("No courses available!");
        }
        System.out.println();
    }


    /**
     * Method to change an existing course name.
     * If there are no courses available, display a message letting the user know there is nothing to change and exit out of the method.
     * Ask the user for the name of the course they want to change (case doesn't matter).
     * If the course doesn't exist, keep asking them for the course name until they enter an existing course name.
     * Once they enter a valid course name, ask them for the new course name.
     * If they enter a name for an existing course, let them know and give them a chance to enter a new course name.
     * Once they enter a non-existing course name, change the course name and let the user know the course name has been changed.
     * Reference: SAMPLE OUTPUT 7
     */
    private static void changeCourse() {
        int index = 0;
        boolean duplicate = false;
        String response;
        String newName;

        // checks to see if any courses exist
        if (MainMenu.courses.size() < 1) {
            System.out.println("No courses available!");
        } else {

            // do while to keep prompting user for course name until a course matches
            do {
                System.out.print("Old course name: ");
                response = MainMenu.input.next();

                // loops through to find course with matching course name
                for (Course i : MainMenu.courses) {
                    if (response.equalsIgnoreCase(i.getName())) {
                        duplicate = true;
                        index = MainMenu.courses.indexOf(i);
                        break;
                    }
                }

                // if matching course exists, prompts user for new course name
                if (duplicate) {
                    System.out.print("New Course name: ");
                    newName = MainMenu.input.next();
                    MainMenu.courses.get(index).setName(newName);
                    System.out.println(response + " name changed!");
                } else {
                    System.out.println(response + " does not exist!");
                }
            } while (!duplicate);
        }
        System.out.println();
    }
}