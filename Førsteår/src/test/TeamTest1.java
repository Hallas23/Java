package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import modelstudent.Student;
import modelstudent.Team;

public class TeamTest1 {
    private Team team;
    private Student s1, s2, s3;
    
    @Before
    public void setUp() throws Exception {
        // arrange
        team = new Team("The winner team", "Room A");
        s1 = new Student("Jane Jensen", 21);
        s2 = new Student("Lene Hansen", 22);
        s3 = new Student("Mette Nielsen", 20);
        team.addStudent(s1);
        team.addStudent(s2);
    }
    
    @Test
    public void testTeam() {
        // act
        Team team = new Team("Great Boys", "Room B");
        //assert
        assertNotNull(team);
        assertEquals("Great Boys", team.getName());
        assertEquals("Room B", team.getRoom());
        assertEquals(0, team.getNumberOfStudents());
        Student[] expected = new Student[30];
        assertArrayEquals(team.getStudents(), expected);
        
    }
    
    @Test
    public void testSetName() {
        //act
        team.setName("Great Boys");
        //assert
        assertEquals("Great Boys", team.getName());
    }
    
    @Test
    public void testSetRoom() {
        //act
        team.setRoom("Room B");
        //assert
        assertEquals("Room B", team.getRoom());
        
    }
    
    @Test
    public void testAddStudent() {
        // arrange
        Student[] expected = new Student[30];
        expected[0] = s1;
        expected[1] = s2;
        expected[2] = s3;
        // act
        team.addStudent(s3);
        // assert
        assertArrayEquals(team.getStudents(), expected);
        
    }
    
    @Test
    public void testGetNumberOfStudents() {
        // arrange
        // act
        int actualResult = team.getNumberOfStudents();
        // assert
        assertEquals(actualResult, 2);
    }
}
