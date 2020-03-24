package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import modelstudent.Student;
import modelstudent.TooManyGradesException;

public class StudentTest {

    @Test
    public void testStudent() {
        Student student = new Student("Jane Jensen", 22);
        assertNotNull(student);
        assertEquals("Jane Jensen", student.getName());
        assertEquals(22, student.getAge());
    }

    @Test
    public void testSetName() {
        Student student = new Student("Jane Jensen", 22);
        student.setName("Hugo Mortensen");
        assertEquals("Hugo Mortensen", student.getName());
    }
    
    @Test
    public void testSetAge() {
        Student student = new Student("Jane Jensen", 22);
        student.setAge(33);
        assertEquals(33, student.getAge());
    }

    @Test
    public void testAverageGrade() {
        // Arrange
        Student student = new Student("Jane Jensen", 22);
        student.addGrade(7);
        student.addGrade(12);
        student.addGrade(2);
        // Act
        double actualResult = student.averageGrade();
        // Assert
        assertEquals(7, actualResult, 0.0001);
    }

    // @Test(expected = TooManyGradesException.class)
    // public void testAverageGradeException() {
    // // Arrange
    // Student student = new Student("Jane Jensen", 22);
    // student.addGrade(7);
    // student.addGrade(12);
    // student.addGrade(2);
    // student.addGrade(7);
    // student.addGrade(12);
    // student.addGrade(2);
    // student.addGrade(7);
    // student.addGrade(12);
    // student.addGrade(2);
    // student.addGrade(7);
    // // Act
    // student.addGrade(12);
    // // Assert
    //
    // }

    public void testAverageGradeException() {
        // Arrange
        Student student = new Student("Jane Jensen", 22);
        student.addGrade(7);
        student.addGrade(12);
        student.addGrade(2);
        student.addGrade(7);
        student.addGrade(12);
        student.addGrade(2);
        student.addGrade(7);
        student.addGrade(12);
        student.addGrade(2);
        student.addGrade(7);
        // Act
        try {
            student.addGrade(12);
            fail();
        }
        catch (TooManyGradesException e) {
            assertEquals(e.getMessage(), "For mange karakterer givet");
        }
    }
}
