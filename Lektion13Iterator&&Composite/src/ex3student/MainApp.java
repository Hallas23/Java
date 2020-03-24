package ex3student;

import java.util.Iterator;

public class MainApp {

	public static void main(String[] args) {
		
		ArrayedListStudent<Integer> als1 = new ArrayedListStudent<Integer>();
		als1.add(1);
		als1.add(2);
		als1.add(3);
		als1.add(1);
		als1.add(2);
		als1.add(3);
		
		Iterator<Integer> it = als1.iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next().intValue());
		}
	}

}
