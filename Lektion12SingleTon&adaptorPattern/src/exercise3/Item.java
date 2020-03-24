package exercise3;

public abstract class Item {

	private double netprice;
	private String name;

	
	public double getNetprice() {
		return netprice;
	}

	public void setNetprice(double netprice) {
		this.netprice = netprice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public abstract double calcVAT();

}
