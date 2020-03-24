package application.model;

public class Employee implements java.io.Serializable{
	private String name;
	private int wage; // hourly wage

	// link to company class (--> 0..1)
	private Company company;

	public Employee(String name, int wage) {
		this.name = name;
		this.wage = wage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWage() {
		return wage;
	}

	public void setWage(int wage) {
		this.wage = wage;
	}

	@Override
	public String toString() {
		return name + " (kr " + wage + ")";
	}

	// -----------------------------------------------------------------------------

	public Company getCompany() {
		return company;
	}

	/**
	 * Sets the company as this employees company, if they aren't connected 
	 * Pre: The employee isn't connected to another company
	 * @param company
	 */
	public void setCompany(Company company) {
		if (this.company != company) {
			Pre.require(this.company == null);
			this.company = company;
			company.addEmployee(this);
		}
	}

	/**
	 * Clears the employee's company
	 */
	public void setCompanyNull() {
		if (this.company != null) {
			Company oldCompany = this.company;
			this.company = null;
			oldCompany.removeEmployee(this);
		}

	}

	// -----------------------------------------------------------------------------

	/**
	 * Returns the weekly salary of this employee.
	 */
	public int weeklySalary() {
		int salary = wage * company.getHours();
		return salary;
	}

}
