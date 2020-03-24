package application.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import application.controller.Controller;

public class TestApp {

	public static void main(String[] args) {

		Controller c = Controller.getController();

		Produktgruppe øl = c.createProduktgruppe("Standard øl", false);

		PantProdukt pp1 = c.createPantProdukt(øl, "Klosterbryg", 100, 3);

		Produkt p1 = c.createProdukt(øl, "Tuborg", 50);

		PantProdukt pp2 = c.createPantProdukt(øl, "Tuborg med pant", 10, 3);

		Gaveæske g1 = c.createGaveæske("Gaveæske 1", 8, 3, 3, øl);

		Salg s1 = c.createSalg();

		Salg s2 = c.createSalg();

		Prisliste pl1 = c.createPrisliste("Bar");

		Pris pris1 = c.createPris(pl1, 50, p1);
		Pris pris2 = c.createPris(pl1, 100, pp1);

		Pris pris3 = c.createPris(pl1, 30, pp2);
		Pris pris4 = c.createPris(pl1, 500, g1);

		IngenRabat r1 = new IngenRabat();
		ProcentRabat r2 = new ProcentRabat(99);
		PrisRabat r3 = new PrisRabat(50);

		c.createSalgslinje(s1, 5, r1, pris1);
		c.createSalgslinje(s1, 1, r1, pris2);

		c.createSalgslinje(s2, 5, r1, pris1);
		c.createSalgslinje(s2, 1, r1, pris2);

		c.createRundvisning("EAA", LocalDateTime.of(LocalDate.of(2019, 03, 28), LocalTime.of(12, 30)),
				LocalDateTime.of(LocalDate.of(2019, 03, 28), LocalTime.of(14, 30)), 50, 24, null, 50, false);
		
		c.createRundvisning("OLE", LocalDateTime.of(LocalDate.of(2029, 03, 25), LocalTime.of(12, 30)),
				LocalDateTime.of(LocalDate.of(2029, 03, 25), LocalTime.of(14, 30)), 50, 24, null, 50, false);
		
		System.out.println(c.fremtidigeRundvisninger());

		s1.setDato(LocalDate.of(2019, 03, 26));
		s2.setDato(LocalDate.of(2019, 03, 27));

		System.out
				.println(c.antalSolgteAfProduktOverPeriode(p1, LocalDate.of(2019, 03, 24), LocalDate.of(2019, 03, 27)));

	}
}
