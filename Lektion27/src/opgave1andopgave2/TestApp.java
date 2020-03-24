package opgave1andopgave2;

import java.util.HashMap;

public class TestApp {

	public static void main(String[] args) {
		HashMap<String, Bil> biler = new HashMap<>();
		
		Bil bil1 = new Bil("12345678", "audi", "A8", "rød");
		Bil bil2 = new Bil("87654321", "BMW", "M4", "blå");
		Bil bil3 = new Bil("54637281", "Citroen", "C5", "Sølv");
		Bil bil4 = new Bil("18273645", "Ferrari", "Enzo", "rød");
		Bil bil5= new Bil("18273645", "Ferrari", "Enzo", "rød");

		
		biler.put(bil1.getRegisteringsnummer(),bil1);
		biler.put(bil2.getRegisteringsnummer(),bil2);
		biler.put(bil3.getRegisteringsnummer(),bil3);
		biler.put(bil4.getRegisteringsnummer(),bil4);
		biler.put(bil5.getRegisteringsnummer(),bil5);

		System.out.println(biler);

		
	}

}
