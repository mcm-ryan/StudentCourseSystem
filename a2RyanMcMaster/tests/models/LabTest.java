package models;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabTest {

        /**
         * Unit test for the student ID.
         */
        @Disabled
        @Test
        void testStudentID() {
            Student s1 = new Student("Doe");
            assertEquals(1, s1.getId());

            Student s2 = new Student("Doe");
            assertEquals(1, s1.getId());
            assertEquals(2, s2.getId());
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Course class section
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /**
         * Unit test for Course constructor.
         */
        @Test
        void testCourseConstructor() {
            Course c = new Course("Java");
            assertEquals("Java", c.getName());
            assertEquals(0, c.getCourseStudentList().size());
        }

        /**
         * Unit test for Course setName().
         */
        @Test
        void testCourseSetName() {
            Course c = new Course("Java");
            assertEquals("Java", c.getName());
            assertEquals(0, c.getCourseStudentList().size());

            c.setName("");
            assertEquals("Nothing", c.getName());
            assertEquals(0, c.getCourseStudentList().size());

            c.setName(null);
            assertEquals("Nothing", c.getName());
            assertEquals(0, c.getCourseStudentList().size());
        }

        /**
         * Unit test for Course addStudent().
         */
        @Test
        void testCourseAddStudent() {
            Student s1 = new Student("Jane");
            Student s2 = new Student("John");
            Student s3 = new Student("Bill");
            Student s4 = new Student("Jean");

            Course c = new Course("Java");
            assertEquals("Java", c.getName());
            assertEquals(0, c.getCourseStudentList().size());

            assertEquals("Student enrolled!", c.addStudent(s1));
            assertEquals(1, c.getCourseStudentList().size());
            assertEquals("Student enrolled!", c.addStudent(s2));
            assertEquals(2, c.getCourseStudentList().size());
            assertEquals("Student enrolled!", c.addStudent(s3));
            assertEquals(3, c.getCourseStudentList().size());
            assertEquals("Full course!", c.addStudent(s4));
            assertEquals(3, c.getCourseStudentList().size());
        }

        /**
         * Unit test for Course dropStudent().
         */
        @Test
        void testCourseDropStudent() {
            Student s1 = new Student("Jane");
            Student s2 = new Student("John");
            Student s3 = new Student("Bill");
            Student s4 = new Student("Jean");

            Course c = new Course("Java");
            assertEquals("Java", c.getName());
            assertEquals(0, c.getCourseStudentList().size());

            assertEquals("Student enrolled!", c.addStudent(s1));
            assertEquals(1, c.getCourseStudentList().size());
            assertEquals("Student enrolled!", c.addStudent(s2));
            assertEquals(2, c.getCourseStudentList().size());
            assertEquals("Student enrolled!", c.addStudent(s3));
            assertEquals(3, c.getCourseStudentList().size());
            assertEquals("Full course!", c.addStudent(s4));
            assertEquals(3, c.getCourseStudentList().size());

            assertEquals("Empty course!", c.dropStudent(s4));
            assertEquals(3, c.getCourseStudentList().size());
            assertEquals("Student dropped!", c.dropStudent(s3));
            assertEquals(2, c.getCourseStudentList().size());
            assertEquals("Student dropped!", c.dropStudent(s2));
            assertEquals(1, c.getCourseStudentList().size());
            assertEquals("Student dropped!", c.dropStudent(s1));
            assertEquals(0, c.getCourseStudentList().size());
            assertEquals("Empty course!", c.dropStudent(s4));
            assertEquals(0, c.getCourseStudentList().size());
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Student class section
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /**
         * Unit test for Student constructor.
         */
        @Test
        void testStudentConstructor() {
            Student s1 = new Student("Doe");
            assertEquals("Doe", s1.getName());
            assertEquals(0, s1.getStudentCourseList().size());

            Student s2 = new Student("Doe");
            assertEquals("Doe", s2.getName());
            assertEquals(0, s2.getStudentCourseList().size());
        }

        /**
         * Unit test for Student setName().
         */
        @Test
        void testStudentSetName() {
            Student s = new Student("Doe");
            assertEquals("Doe", s.getName());
            assertEquals(0, s.getStudentCourseList().size());

            s.setName("");
            assertEquals("Nothing", s.getName());
            assertEquals(0, s.getStudentCourseList().size());

            s.setName(null);
            assertEquals("Nothing", s.getName());
            assertEquals(0, s.getStudentCourseList().size());
        }

        /**
         * Unit test for Student addCourse().
         */
        @Test
        void testStudentAddCourse() {
            Course c1 = new Course("Java");

            Student s = new Student("Doe");
            assertEquals("Doe", s.getName());
            assertEquals(0, s.getStudentCourseList().size());

            assertEquals("Non existing course!", s.add(null));

            assertEquals("Student enrolled!", s.add(c1));
            assertEquals(1, s.getStudentCourseList().size());
            assertEquals("Doe courses: [Java]", s.getStudentCourseListString());

            assertEquals("Duplicate course!", s.add(c1));
            assertEquals(1, s.getStudentCourseList().size());
            assertEquals("Doe courses: [Java]", s.getStudentCourseListString());

            c1.addStudent(new Student("Tim"));
            c1.addStudent(new Student("Tom"));
            assertEquals("Duplicate course!", s.add(c1));
            assertEquals(1, s.getStudentCourseList().size());
            assertEquals("Doe courses: [Java]", s.getStudentCourseListString());

            Course c2 = new Course("Python");
            c2.addStudent(new Student("One"));
            c2.addStudent(new Student("Two"));
            c2.addStudent(new Student("Three"));
            assertEquals("Full course!", s.add(c2));
            assertEquals(1, s.getStudentCourseList().size());
            assertEquals("Doe courses: [Java]", s.getStudentCourseListString());

            Course c3 = new Course("C++");
            assertEquals("Student enrolled!", s.add(c3));
            assertEquals(2, s.getStudentCourseList().size());
            assertEquals("Doe courses: [Java, C++]", s.getStudentCourseListString());
        }

        /**
         * Unit test for Student dropCourse().
         */
        @Test
        void testStudentDropCourse() {
            Course c1 = new Course("Java");
            Course c2 = new Course("Python");

            Student s = new Student("Doe");
            assertEquals("Doe", s.getName());
            assertEquals(1, s.getId());
            assertEquals(0, s.getStudentCourseList().size());

            assertEquals("Error withdrawing!", s.drop(null));

            assertEquals("Empty course!", s.drop(c1));
            assertEquals(0, s.getStudentCourseList().size());
            assertEquals("Doe courses: []", s.getStudentCourseListString());

            assertEquals("Student enrolled!", s.add(c1));
            assertEquals(1, s.getStudentCourseList().size());
            assertEquals("Doe courses: [Java]", s.getStudentCourseListString());
            assertEquals("Student dropped!", s.drop(c1));
            assertEquals(0, s.getStudentCourseList().size());
            assertEquals("Doe courses: []", s.getStudentCourseListString());
            assertEquals("Empty course!", s.drop(c1));
            assertEquals(0, s.getStudentCourseList().size());
            assertEquals("Doe courses: []", s.getStudentCourseListString());

            assertEquals("Student enrolled!", s.add(c1));
            assertEquals(1, s.getStudentCourseList().size());
            assertEquals("Doe courses: [Java]", s.getStudentCourseListString());
            assertEquals("Student enrolled!", s.add(c2));
            assertEquals(2, s.getStudentCourseList().size());
            assertEquals("Doe courses: [Java, Python]", s.getStudentCourseListString());
            assertEquals("Student dropped!", s.drop(c1));
            assertEquals(1, s.getStudentCourseList().size());
            assertEquals("Doe courses: [Python]", s.getStudentCourseListString());
        }
    }
