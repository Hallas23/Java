package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import application.model.Student;
import application.service.Service;

public class ServiceTest {
	private Service service = Service.getService();

	@Before
	public void setUp() throws Exception {
		service = Service.getService();
		service.createStudent("Margrethe", 51, true);
		System.out.println("setup kaldt");
	}

	@Test
	public void testCreateStudent() {
		Student s1 = service.createStudent("Hanne", 51, true);
		assertNotNull(s1);
		assertEquals(s1.getName(), "Hanne");

		assertTrue(service.getStudents().contains(s1));

	}

	@Test
	public void testDeleteStudent() {
		Student s2 = service.createStudent("Hanne", 51, true);
		assertTrue(service.getStudents().contains(s2));
		service.deleteStudent(s2);
		assertFalse(service.getStudents().contains(s2));

	}

	@Test
	public void testGetAge() {
		Student s1 = service.createStudent("Margrethe", 25, true);
		assertEquals(s1.getAge(), 25);

	}

}
