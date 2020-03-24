package modelstudent;

public class TestNuk {

	public static void main(String[] args) {
		Student student = new Student("Jane Jensen", 22);
		// Act
		double actualResult = student.averageGrade();
		// Assert
		System.out.println(actualResult);

	}

}
