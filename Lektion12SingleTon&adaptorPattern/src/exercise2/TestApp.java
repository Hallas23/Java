package exercise2;

public class TestApp {

	public static void main(String[] args) {
		Counter c1 = Counter.getInstance();
		Counter c2 = Counter.getInstance();
		
		System.out.println(c1.getValue());
		System.out.println(c2.getValue());
		c1.count();
		System.out.println(c1.getValue());
		System.out.println(c2.getValue());
		c1.times();
		System.out.println(c1.getValue());
		System.out.println(c2.getValue());
		c1.times();
		System.out.println(c1.getValue());
		System.out.println(c2.getValue());
		c1.zero();
		System.out.println(c1.getValue());
		System.out.println(c2.getValue());



	}

}
