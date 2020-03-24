package opgave2_3;

public class Opgave2 {

	public static void main(String[] args) {
		Student s1 = new Student(25, "Hans");
		Student s2 = new Student(26, "Egon");

		College c1 = new College("Harvard");
		c1.addStudent(s1);
		c1.addStudent(s2);
		s1.addGrade(20);
		s1.addGrade(10);
		s2.addGrade(20);	
		s2.addGrade(0);
		
	
		
		System.out.println(s1.getGrades());
		System.out.println(s1.studentAverage());
		System.out.println(s2.studentAverage());

		System.out.println(c1.getStudents());
		System.out.println(c1.findStudent(26));
		System.out.println(c1.calcAverage());

	}

}
