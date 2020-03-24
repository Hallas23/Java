package exercise3;

import java.util.ArrayList;
import java.util.List;

public class TestApp {

	public static void main(String[] args) {
		Item i1 = new ElApp(100, "øl");
		
		Item e1 = new ElApp(12, "Pære");
		
		Item f1 = new Food(100, "øl");
		
		AlcoBev a1 = new AlcoBev(100, "Tuborg");
		Item adaptor = new Adapter(a1);
		
		List<Item> items = new ArrayList<>();
		
		items.addAll(List.of(i1, e1, f1, adaptor));

		for (Item i: items) {
			System.out.print(i.calcVAT() + " ");
			System.out.println(i.getName());
			System.out.println();

		}
	}

}
