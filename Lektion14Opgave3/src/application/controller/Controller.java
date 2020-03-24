package application.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import application.model.Company;
import application.model.Employee;
import storage.Storage;

public class Controller {

	private static Controller instance;
	private Storage storage = Storage.getStorage();

	public static Controller getController() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}

	private Controller() {
		storage = Storage.getStorage();
	}

	/**
	 * Creates a new Company.<br />
	 * Requires: hours >= 0.
	 */
	public Company createCompany(String name, int hours) {
		Company company = new Company(name, hours);
		storage.addCompany(company);
		return company;
	}

	/**
	 * Deletes the company.<br />
	 * Requires: The company has no employees.
	 */
	public void deleteCompany(Company company) {
		storage.removeCompany(company);
	}

	/**
	 * Updates the company.<br />
	 * Requires: hours >= 0.
	 */
	public void updateCompany(Company company, String name, int hours) {
		company.setName(name);
		company.setHours(hours);
	}

	/**
	 * Get all the companies
	 */
	public ArrayList<Company> getCompanies() {
		return storage.getCompanies();
	}

	// -------------------------------------------------------------------------

	/**
	 * Creates a new employee.<br />
	 * Requires: wage >= 0.
	 */
	public Employee createEmployee(String name, int wage) {
		Employee employee = new Employee(name, wage);
		storage.addEmployee(employee);
		return employee;
	}

	/**
	 * Creates a new employee.<br />
	 * Requires: wage >= 0, company!=null.
	 */
	public Employee createEmployee(String name, int wage, Company company) {
		Employee employee = createEmployee(name, wage);
		company.addEmployee(employee);
		return employee;
	}

	/**
	 * Deletes the employee.
	 */
	public void deleteEmployee(Employee employee) {
		Company company = employee.getCompany();
		if (company != null) {
			company.removeEmployee(employee);
		}
		storage.removeEmployee(employee);
	}

	/**
	 * Updates the employee.<br />
	 * Requires: wage >= 0.
	 */
	public void updateEmployee(Employee employee, String name, int wage) {
		employee.setName(name);
		employee.setWage(wage);
	}

	/**
	 * Adds the employee to the company. Removes the employee from the old company
	 * if present.
	 */
	public void addEmployeeToCompany(Employee employee, Company company) {
		Company oldCompany = employee.getCompany();
		if (oldCompany != null) {
			oldCompany.removeEmployee(employee);
		}
		company.addEmployee(employee);
	}

	/**
	 * Removes the employee from the old company if not null.
	 * 
	 * @param company  The old company. Can be null.
	 * @param employee The employee to remove.
	 */
	public void removeEmployeeFromCompany(Employee employee, Company company) {
		if (company != null) {
			company.removeEmployee(employee);
		}
	}

	/**
	 * Get all the employees.
	 */
	public ArrayList<Employee> getEmployees() {
		return storage.getEmployees();
	}

	// -------------------------------------------------------------------------

	/**
	 * Initializes the storage with some objects.
	 */
	public void loadStorage() {
		try (FileInputStream fileIn = new FileInputStream("storage.ser")) {
			try (ObjectInputStream in = new ObjectInputStream(fileIn);) {
				storage = (Storage) in.readObject();
				System.out.println("Storage loaded from file storage.ser.");
			} catch (ClassNotFoundException ex) {
				System.out.println("Error loading storage object.");
				throw new RuntimeException(ex);
			}
		} catch (IOException ex) {
			System.out.println("Error loading storage object.");
			throw new RuntimeException(ex);
		}

	}

	public void saveStorage() {
		try (FileOutputStream fileOut = new FileOutputStream("storage.ser")) {
			try (ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
				out.writeObject(storage);
				System.out.println("Storage saved in file storage.ser.");
			}
		} catch (IOException ex) {
			System.out.println("Error saving storage object.");
			throw new RuntimeException(ex);
		}
	}

	public void initStorage() {
		Company c1 = getController().createCompany("IBM", 37);
		Company c2 = getController().createCompany("AMD", 40);

		getController().createCompany("SLED", 45);
		getController().createCompany("Vector", 32);

		getController().createEmployee("Bob Dole", 210, c2);
		getController().createEmployee("Alice Schmidt", 250, c1);
		getController().createEmployee("George Down", 150, c2);

		getController().createEmployee("Rita Uphill", 300);
	}
}
