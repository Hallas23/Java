package test;

import java.util.HashSet;
import java.util.Set;

import modelstudent.Student;
import modelstudent.Team;
import storage.Storage;

public class StorageTest {
    Storage storage;
    Team teama, teamb;
    Student s1, s2;

    @Before
    public void setUp() throws Exception {
        //arrange
        storage = new Storage();
        teama = new Team("A", "1");
        teamb = new Team("B", "2");

        storage.addTeam(teama);
        storage.addTeam(teamb);
        s1 = new Student("Hans", 22);
        s2 = new Student("Ib", 20);
        storage.addStudent(s1);
        storage.addStudent(s2);

    }
    
    @Test
    public void testStorage() {
        //act
        Storage s = new Storage();
        //assert
        assertNotNull(s);
        assertEquals(0, s.getAllStudents().size());
        assertEquals(0, s.getAllTeams().size());
    }
    
    @Test
    public void testAddTeam() {
        //arrange
        Team teamc = new Team("C", "3");
        assertEquals(2, storage.getAllTeams().size());
        assertFalse(storage.getAllTeams().contains(teamc));
        //act
        storage.addTeam(teamc);
        //assert
        assertEquals(3, storage.getAllTeams().size());
        assertTrue(storage.getAllTeams().contains(teamc));
    }
    
    @Test
    public void testGetAllTeams() {
        // act && assert
        assertEquals(2, storage.getAllTeams().size());
        assertTrue(storage.getAllTeams().contains(teama));
        assertTrue(storage.getAllTeams().contains(teamb));

    }
    
    @Test
    public void testAddStudent() {
        //arrange
        Student s3 = new Student("Jane", 24);
        assertEquals(2, storage.getAllStudents().size());
        assertFalse(storage.getAllStudents().contains(s3));
        //act
        storage.addStudent(s3);
        //assert
        assertEquals(3, storage.getAllStudents().size());
        assertTrue(storage.getAllStudents().contains(s3));
    }
    
    @Test
    public void testGetAllStudents() {
        //act & assert
        assertEquals(2, storage.getAllStudents().size());
        assertTrue(storage.getAllStudents().contains(s1));
        assertTrue(storage.getAllStudents().contains(s2));
    }
    
}
