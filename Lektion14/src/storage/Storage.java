package storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import application.model.Student;

public class Storage implements Serializable {
	private static Storage storage;
	private List<Student> students;

	public static Storage getStorage() {
		if (storage == null) {
			storage = new Storage();
		}
		return storage;
	}

	private Storage() {
		students = new ArrayList<Student>();
	}

	public ArrayList<Student> getStudents() {
		return new ArrayList<Student>(students);
	}

	public void addStudent(Student student) {
		students.add(student);
	}

	public void removeStudent(Student student) {
		students.remove(student);
	}

	// -------------------------------------------------------------------------

}
