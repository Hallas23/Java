package application.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import application.model.Student;
import storage.Storage;

public class Service {

	private static Service service;
	private Storage storage;

	public static Service getService() {
		if (service == null) {
			service = new Service();
		}
		return service;
	}

	private Service() {
		storage = Storage.getStorage();

	}

	// Denne metode kræver at Storage constructoren ikke er privat den er kun
	// til JUnit test
	// public Service getTestService() {
	// storage = new Storage();
	// return this;
	// }

	/**
	 * Creates a new Student.<br />
	 * Requires: age >= 0.
	 */
	public Student createStudent(String name, int age, boolean active) {
		Student student = new Student(name, age, active);
		storage.addStudent(student);
		return student;
	}

	/**
	 * Deletes the student.<br />
	 */
	public void deleteStudent(Student student) {
		storage.removeStudent(student);
	}

	/**
	 * Updates the student.<br />
	 * Requires: age >= 0.
	 */
	public void updateStudent(Student student, String name, int age, boolean active) {
		student.setName(name);
		student.setAge(age);
		student.setActive(active);

	}

	/**
	 * Get all the students
	 */
	public ArrayList<Student> getStudents() {
		return storage.getStudents();
	}

	// -------------------------------------------------------------------------

	/**
	 * Loads the storage (including all objects in storage).
	 */

	public void loadStorage() {
		try (FileInputStream fileIn = new FileInputStream("storage.ser2")) {
			try (ObjectInputStream in = new ObjectInputStream(fileIn);) {
				storage = (Storage) in.readObject();

				System.out.println("Storage loaded from file storage.ser.");
			} catch (ClassNotFoundException ex) {
				System.out.println("Error loading storage object.");
				throw new RuntimeException(ex);
			}
		} catch (IOException ex) {
			System.out.println("Error loading storage object.");
			throw new RuntimeException(ex);
		}

	}

	public void saveStorage() {
		try (FileOutputStream fileOut = new FileOutputStream("storage.ser2")) {
			try (ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
				out.writeObject(storage);
				System.out.println("Storage saved in file storage.ser.");
			}
		} catch (IOException ex) {
			System.out.println("Error saving storage object.");
			throw new RuntimeException(ex);
		}
	}

}
