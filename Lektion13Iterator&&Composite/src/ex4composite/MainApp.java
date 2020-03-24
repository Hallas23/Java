package ex4composite;

import java.util.ArrayList;
import java.util.List;

public class MainApp {

	public static void main(String[] args) {
		Triangle t1 = new Triangle("Triangle 1", "Triangle", 10, 5);
		Firkant f1 = new Firkant("Firkant 1", "Firkant", 10, 20);
		Elipse e1 = new Elipse("Elipse 1", "Elipse", 5, 9);
		
		Triangle t2 = new Triangle("Triangle 2", "Triangle", 14, 10);
		Firkant f2 = new Firkant("Firkant 2", "Firkant", 5, 13);
		Elipse e2 = new Elipse("Elipse 2", "Elipse", 3, 2);
		
		ArrayList<Figure> figures = new ArrayList<>();
		
		ArrayList<Figure> figures2 = new ArrayList<>();
		
		figures.addAll(List.of(t1, e1, f1));
		
		figures2.addAll(List.of(t2, e2, f2));

		CompositeFigure cf1 = new CompositeFigure("cf1", "Composite", figures);
		
		CompositeFigure cf2 = new CompositeFigure("cf2", "Composite", figures2);

		cf1.add(cf2);
		
		
		System.out.println(cf1.calcArea());
	}
}
