package models;
import menu.MainMenu;
import java.util.ArrayList;


public class Course {

    // Course name.
    private String name;

    //Students enrolled in a course.
    private ArrayList<Student> courseStudentList;

    //Maximum number of students that can be enrolled in a course.
    public static final int MAX = 3;

    /**
     * Method to create a course.
     * @param name course name.
     */
    public Course(String name) {
        courseStudentList = new ArrayList<Student>();
        setName(name);
        MainMenu.courses.add(this);
    }

    /**
     * Method to retrieve the current course name.
     *
     * @return current course name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method to set the course name.
     * If the course name is null or an empty string, set the name to "Nothing".
     *
     * @param name current course name.
     */
    public void setName(String name) {

        // checks if user failed to enter anything
        if(name == null || name.equals("")){
            this.name = "Nothing";
        } else {
            this.name = name;
        }
    }

    /**
     * Method to add a student to the course.
     * If the course is full, do not add the student to the course but return the string "Full course!".
     * If not full, add the student to the course by adding them to the courseStudentList and return "Student enrolled!".
     *
     * @param student student being added to the course.
     * @return string value describing the result of the operation.
     */
    public String addStudent(Student student) {

        String result = "";

        // checks to see if course is full
        if (courseStudentList.size() >= MAX) {
            result = "Full course!";
        }
        // if not, the student is added to the course student list
        else if (courseStudentList.size() < MAX) {
            courseStudentList.add(student);
            result = "Student enrolled!";
        }
        return result;
    }

    /**
     * Method to drop a student from the course.
     * If the course is empty, do not drop the student from the course but return the string "Empty course!".
     * If not empty, drop the student from the course and return the string "Student dropped!".
     *
     * @param student student being dropped from the course.
     * @return string value describing the result of the operation.
     */
    public String dropStudent(Student student) {

        String result = "";

        // check if course is empty or no match is found
        if(courseStudentList.size() == 0 || !courseStudentList.contains(student)) {
            result = "Empty course!";
        } else {
            student.drop(this);
            courseStudentList.remove(student);
            result = "Student dropped!";
        }
        return result;
    }

    /**
     * Method that gives you access to the students enrolled in the course.
     * @return ArrayList containing all the students enrolled in the course.
     */
    public ArrayList<Student> getCourseStudentList() {
        return courseStudentList;
    }
}