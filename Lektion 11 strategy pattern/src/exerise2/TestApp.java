package exerise2;

import java.util.ArrayList;
import java.util.Collections;

public class TestApp {

	public static void main(String[] args) {
		Customer c2 = new Customer(23, "Balladelan");
		Customer c1 = new Customer(22, "Allan");
		
		Customer.setComperator(new Nummer());
		
		
		ArrayList<Customer> l1 = new ArrayList<Customer>();
		
		l1.add(c2);
		l1.add(c1);
		
		System.out.println(l1);
		
		Collections.sort(l1);
		
		System.out.println(l1);
		
	}

}
