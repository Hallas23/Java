package exercise3;

public class Adapter extends Item {

	private AlcoBev alcoBev;

	public Adapter(AlcoBev alcobev) {
		 this.alcoBev = alcobev;
	}

	@Override
	public double calcVAT() {
		return alcoBev.getVat();
	}
	
	public void setAdaptor(AlcoBev alcobev) {
		this.alcoBev = alcobev;
	}
	
	@Override
	public double getNetprice() {
		return alcoBev.getprice();
	}
	
	@Override
	public void setNetprice(double theNetprice) {
		alcoBev.setTheNetprice(theNetprice);
	}
	
	@Override
	public String getName() {
		return alcoBev.getName();
	}
	
	@Override
	public void setName(String theDescription) {
		alcoBev.setTheDescription(theDescription);
	}

}
