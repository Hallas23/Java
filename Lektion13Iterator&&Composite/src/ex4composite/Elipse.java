package ex4composite;

public class Elipse extends Figure {
	private double a;
	private double b;
	

	public Elipse(String name, String type, double a, double b) {
		super(name, type);
		this.a = a;
		this.b = b;
	}

	@Override
	public double calcArea() {
		return Math.PI * a * b;
	}

	public double getA() {
		return a;
	}

	public double getB() {
		return b;
	}
	
	

}
