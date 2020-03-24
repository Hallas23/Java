package storage;

import java.util.ArrayList;

import application.model.Company;
import application.model.Employee;

public class Storage implements java.io.Serializable{
	private ArrayList<Company> companies = new ArrayList<>();
	private ArrayList<Employee> employees = new ArrayList<>();

	private static Storage instance;

	public static Storage getStorage() {
		if (instance == null) {
			instance = new Storage();
		}
		return instance;
	}

	private Storage() {
		
	}

	// -------------------------------------------------------------------------

	public ArrayList<Company> getCompanies() {
		return new ArrayList<Company>(companies);
	}

	public void addCompany(Company company) {
		companies.add(company);
	}

	public void removeCompany(Company company) {
		companies.remove(company);
	}

	// -------------------------------------------------------------------------

	public ArrayList<Employee> getEmployees() {
		return new ArrayList<Employee>(employees);
	}

	public void addEmployee(Employee employee) {
		employees.add(employee);
	}

	public void removeEmployee(Employee employee) {
		employees.remove(employee);
	}

	// -------------------------------------------------------------------------

}
