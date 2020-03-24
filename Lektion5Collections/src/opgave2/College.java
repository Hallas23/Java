package opgave2;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class College {

	private String name;
	private final Set<Student> students = new LinkedHashSet<>();

	public College(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Student> getStudents() {
		return new ArrayList<>(students);
	}
	
	public void addStudent(Student student) {
		if (!students.contains(student)) {
			students.add(student);
		}
	}
	public void removeStudent(Student student) {
		if (students.contains(student)) {
			students.remove(student);
		}
	}
	
	public double calcAverage() {
		double sum = 0;
		
		for (Student s: students) {
			sum = sum + s.studentAverage();
		}
		return sum / students.size();
	}
	
	public Student findStudent(int studentNo) {
		for (Student s: students) {
			
			if (studentNo == s.getStudentNo()) {
				return s;
			}
		}
		return null;
		
	}

}
