package model;

public class Customer implements Comparable<Customer> {
	private String lastName;
	private String firstName;
	private int age;
	
	public Customer(String lastName, String firstName, int age) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.age = age;
	}
	
	@Override
    public int compareTo(Customer customer) {
		if (lastName.equals(customer.getLastName()))
			return firstName.compareTo(customer.getFirstName());
		else 
			return lastName.compareTo(customer.getLastName());
	}

	public int getAge() {
		return age;
	}

	public String getLastName() {
		return lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	@Override
    public String toString(){
		return lastName + " " + firstName + " " + age;
	}
}
