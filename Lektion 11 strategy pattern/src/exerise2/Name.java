package exerise2;

import java.util.Comparator;

public class Name implements Comparator<Customer> {
	

	@Override
	public int compare(Customer o1, Customer o2) {
		return (o1.getNavn()).compareTo(o2.getNavn());
		
	}
}
