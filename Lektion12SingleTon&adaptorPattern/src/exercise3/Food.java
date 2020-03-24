package exercise3;

public class Food extends Item {
	
	

	public Food(double netprice, String name) {
		this.setNetprice(netprice);
		this.setName(name);
	}
	
	public double calcVAT() {
		return (getNetprice() * 1.05) - getNetprice();
	}


}
