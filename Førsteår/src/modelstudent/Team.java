package modelstudent;

import java.util.Arrays;

public class Team {
	private String name;
	private String room;

	private Student[] students;
	private int numberOfStudents;

	public Team(String name, String room) {
		this.name = name;
		this.room = room;
		students = new Student[30];

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoom() {
		return room;

	}

	public void setRoom(String room) {
		this.room = room;
	}

	public void addStudent(Student s) {
		students[numberOfStudents] = s;
		numberOfStudents++;
	}

	public Student[] getStudents() {
		return Arrays.copyOf(students, students.length);
	}

	public int getNumberOfStudents() {
		return numberOfStudents;
	}
}
