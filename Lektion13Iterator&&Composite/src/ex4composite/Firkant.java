package ex4composite;

public  class Firkant extends Figure {
	private double længde;
	private double bredde;
	
	public Firkant(String name, String type, double længde, double bredde) {
		super(name, type);
		this.bredde = bredde;
		this.længde = længde;
	}

	@Override
	public double calcArea() {
		return længde * bredde;
	}

	public double getLængde() {
		return længde;
	}

	public double getHøjde() {
		return bredde;
	}	
}
