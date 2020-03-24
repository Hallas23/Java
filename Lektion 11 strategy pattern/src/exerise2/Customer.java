package exerise2;

import java.util.Comparator;

public class Customer implements Comparable<Customer> {
	private int number;
	private String navn;
	private static Comparator<Customer> comperator;

	
	public Customer(int number, String navn) {
		this.number = number;
		this.navn = navn;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}
	
	public static Comparator<Customer> getComperator() {
		return comperator;
	}

	public static void setComperator(Comparator<Customer> comperator) {
		Customer.comperator = comperator;
	}

	@Override
	public String toString() {
		return "Customer [number=" + number + ", navn=" + navn + "]";
	}

	@Override
	public int compareTo(Customer o) {
		return comperator.compare(this, o);

	}
	
	
}
