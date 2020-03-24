package ex4composite;

public class Triangle extends Figure {
	private double højde;
	private double grundlinje;

	public Triangle(String name, String type, double højde, double grundlinje) {
		super(name, type);
		this.højde = højde;
		this.grundlinje = grundlinje;
	}

	@Override
	public double calcArea() {
		return 0.50 * højde * grundlinje;
	}

	public double getHøjde() {
		return højde;
	}

	public double getGrundlinje() {
		return grundlinje;
	}
	
	

}
