package models;
import menu.MainMenu;
import java.util.ArrayList;


public class Student {

    //Student ID.
    private int id;

    //Student name.
    private String name;

    //Static variable that gets incremented by 1 everytime a new student is created.
    private static int count;

    // Courses a student is enrolled in.
    private ArrayList<Course> studentCourseList;

    /**
     * Method to create a student.
     * @param name student name.
     */
    public Student(String name) {
        Student.count++;
        studentCourseList = new ArrayList<Course>();
        setName(name);
        this.id = Student.count;
        MainMenu.students.add(this);
    }

    /**
     * Method to retrieve the current student ID.
     * @return current student ID.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Method to retrieve the current student name.
     * @return current student name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method to set the student name.
     * @param name current student name.
     */
    public void setName(String name) {
        // checks if the student name is null or an empty string
        if(name == null || name.equals("")){
            this.name = "Nothing";
        } else {
            this.name = name;
        }
    }

    /**
     * Method to enroll a student in a course.
     * @param course course student is enrolling in.
     * @return string value describing the result of the operation.
     */
    public String add(Course course) {

        // string used to hold value of operation status
        String result = null;

        // checks to make sure user entered a valid course value
        if (course != null) {

            // defaults "duplicateCourse" to false
            boolean duplicateCourse = false;

            // loops through to find a course that matches course parameter
            for(Course i: studentCourseList){
                duplicateCourse = i.getName().equalsIgnoreCase(course.getName());
                result = "Duplicate course!";
            }

            // If no duplicate course, student is added to course
            if(!duplicateCourse){
                result = course.addStudent( this );

                // If adding student to course succeeds, the course is added to the student course list
                if(result.equals("Student enrolled!")){
                    studentCourseList.add(course);
                }
            }

        } else {
            result = "Non existing course!";
        }
        return result;
    }

    /**
     * Method to drop a student from a course.
     * @param course course to withdraw a student from.
     * @return string value describing the result of the operation.
     */
    public String drop(Course course) {
        String result = null;

        // checks if user entered a valid course
        if (course != null) {
            // checks if student has no courses
            if(studentCourseList.size() == 0) {
                result = "Empty course!";
            } else {
                // removes course from student course list
                studentCourseList.remove(course);
                result = "Student dropped!";
            }
        } else {
            result = "Error withdrawing!";
        }
        return result;
    }

    /**
     * Method that gives you access to the courses a student is enrolled in.
     * @return ArrayList containing all the courses a student is enrolled in.
     */
    public ArrayList<Course> getStudentCourseList() {
        return studentCourseList;
    }

    /**
     * Method to return all the courses a student is enrolled in.
     * Example: If Jane isn't enrolled in any course, return "Jane courses: []".
     * Example: If Jane is enrolled in Java, return "Jane courses: [Java]".
     * Example: If Jane is enrolled in Java and Python, return "Jane courses: [Java, Python]".
     *
     * @return string value of all the courses a student is enrolled in.
     */
    public String getStudentCourseListString() {

        String result = "";

        // checks if the student has no courses
        if (studentCourseList.size() == 0){
            result = getName() + " courses: []";
        } else {
            // uses a string builder to construct proper output format
            StringBuilder x = new StringBuilder();
            x.append("[");
            for(int i = 0; i < studentCourseList.size(); i++) {
                x.append(studentCourseList.get(i).getName());
                if(studentCourseList.size() > i + 1){
                    x.append(", ");
                }
            }
            x.append("]");
            result = getName() + " courses: " + x;
        }
        return result;
    }
}