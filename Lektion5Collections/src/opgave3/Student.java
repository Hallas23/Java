package opgave3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Student implements Comparable<Student> {

	private int StudentNo;
	private String name;
	private List<Integer> grades = new ArrayList<>();

	public Student(int studentNo, String name) {
		super();
		StudentNo = studentNo;
		this.name = name;
	}

	public int getStudentNo() {
		return StudentNo;
	}

	public void setStudentNo(int studentNo) {
		StudentNo = studentNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Integer> getGrades() {
		return (ArrayList<Integer>) grades;
	}

	public void addGrade(int grade) {
		grades.add(grade);
	}
	
	public double studentAverage() {
		int sum = 0;
		for (Integer g: grades) {
			sum = sum + g.intValue();
		}
		if (sum > 0) {
			return sum / grades.size();
			}
			else {
				return 0;
			}
	}
	
	public String toString() {
		return "" + StudentNo + " " + name;
	}

	@Override
	public int compareTo(Student s) {
		
		return this.StudentNo - s.StudentNo;
	}
	
	public int hashCode() {
		return StudentNo;
	}
	
	public boolean equals(Student s) {
		if (this.StudentNo == s.StudentNo) {
			return true;
		}
		return false;
	}

}
