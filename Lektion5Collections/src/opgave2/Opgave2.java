package opgave2;

public class Opgave2 {

	public static void main(String[] args) {
		Student s1 = new Student(25, "Hans");
		College c1 = new College("Harvard");
		c1.addStudent(s1);
		s1.addGrade(3);
		s1.addGrade(20);
		s1.addGrade(10);
		
		
		System.out.println(s1.getGrades());
		System.out.println(s1.studentAverage());
		System.out.println(c1.findStudent(25));

	}

}
