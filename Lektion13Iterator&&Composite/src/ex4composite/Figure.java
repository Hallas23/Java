package ex4composite;

public abstract class Figure {
		String name;
		String type;
		
		public Figure(String name, String type) {
			this.name = name;
			this.type = type;
		}
		
		public String getName() {
			return name;
		}
		
		public String getType() {
			return type;
		}
		
		public abstract double calcArea();
		
		public String draw() {
			return name + " " + type;
		}
		
		
		
		
}
