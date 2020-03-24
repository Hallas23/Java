package opgave2_3;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class College {

	private String name;
	private final Map<Integer,Student> students = new LinkedHashMap<>();

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

	public Map<Integer, Student> getStudents() {
		return new LinkedHashMap<>(students);
	}
	
	public void addStudent(Student student) {
		if (!students.containsKey(student.getStudentNo())) {
			students.put(student.getStudentNo(), student);
		}
	}
	public void removeStudent(Student student) {
		if (students.containsKey(student.getStudentNo())) {
			students.remove(student.getStudentNo());
		}
	}
	
	public double calcAverage() {
		double sum = 0;
		for (Student s: students.values()) {
			sum = sum + s.studentAverage();
		}
		if (sum > 0) {
		return sum / students.size();
		}
		else {
			return 0;
		}
	}
	
	public Student findStudent(int studentNo) {
		if (students.containsKey(studentNo)) {
			
				return students.get(studentNo);
			}
		
		return null;
	}

}
