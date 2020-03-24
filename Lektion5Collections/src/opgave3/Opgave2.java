package opgave3;

public class Opgave2 {

	public static void main(String[] args) {
		Student s1 = new Student(25, "aans");
		Student s2 = new Student(26, "egoen");

		College c1 = new College("Harvard");
		c1.addStudent(s1);
		c1.addStudent(s2);
		s1.addGrade(20);
		s1.addGrade(10);
		s2.addGrade(20);	
		s2.addGrade(0);
		
		ComperatorClass comp = new ComperatorClass();
		
		System.out.println(comp.compare(s1, s2));
		System.out.println();
		
		
		System.out.println();
//		System.out.println(s1.getGrades());
//		System.out.println(s1.studentAverage());
//		System.out.println(s2.studentAverage());
//
//		System.out.println(c1.getStudents());
//		System.out.println(c1.findStudent(26));
//		System.out.println(c1.calcAverage());

	}

}
