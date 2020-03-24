package test;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import application.model.Student;

public class StudentTest {
	private Student student;

	@Before
	public void setUp() throws Exception {
		student = new Student("Jane", 23);
	}

	@Test
	public void testStudent() {
		assertNotNull(student);
	}

}
