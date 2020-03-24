package ex4composite;

import java.util.ArrayList;

public class CompositeFigure extends Figure {
	ArrayList<Figure> figureComponents = new ArrayList<>();

	public CompositeFigure(String name, String type, ArrayList<Figure> figureComponents) {
		super(name, type);
		this.figureComponents = figureComponents;
	}
	
	@Override
	public String draw() {
		String s = "";
		for (Figure f: figureComponents) {
			s = s + f.draw() + "\n";
		}
		return s;
	}
	
	public void add(CompositeFigure figureComponent) {
		figureComponents.add(figureComponent);
	}
	
	public void remove(CompositeFigure figureComponent) {
		figureComponents.remove(figureComponent);
	}
	
	
	@Override
	public double calcArea() {
		double sum = 0;
		for (Figure f: figureComponents) {
			System.out.println();
			sum += f.calcArea();
		}
		System.out.println();
		return sum;
	}

}
