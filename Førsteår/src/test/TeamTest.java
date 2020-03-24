package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import modelstudent.Student;
import modelstudent.Team;

public class TeamTest {

	@Test
	public void testAddStudent() {
		// arrange
		Team team = new Team("The winner team", "Room A");
		Student s1 = new Student("Jane Jensen", 21);
		Student s2 = new Student("Lene Hansen", 22);
		Student[] expected = new Student[30];
		expected[0] = s1;
		expected[1] = s2;
		// act
		team.addStudent(s1);
		team.addStudent(s2);
		// assert
		assertArrayEquals(expected, team.getStudents());

	}

	@Test
	public void testGetNumberOfStudents() {
		// arrange
		Team team = new Team("The winner team", "Room A");
		Student s1 = new Student("Jane Jensen", 21);
		Student s2 = new Student("Lene Hansen", 22);
		Student[] expected = new Student[30];
		expected[0] = s1;
		expected[1] = s2;
		// act
		team.addStudent(s1);
		team.addStudent(s2);
		// assert
		assertEquals(2, team.getNumberOfStudents());
	}

}
