package exercise3;

public class AlcoBev  {
	
	private double theNetprice;
	private String theDescription;
	
	public AlcoBev(double theNetprice, String theDescription) {
		this.theNetprice = theNetprice;
		this.theDescription = theDescription;
	}

	public double getprice() {
		return theNetprice;
	}

	public void setTheNetprice(double theNetprice) {
		this.theNetprice = theNetprice;
	}
	
	public String getName() {
		return theDescription;
	}

	public void setTheDescription(String theDescription) {
		this.theDescription = theDescription;
	}
	
	public double getVat() {
		if (theNetprice > 150) {
			return (theNetprice * 2.2) - theNetprice;

		}
		return (theNetprice * 1.80) - theNetprice;

	}
	
	

}
