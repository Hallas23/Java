package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import controller.Controller;
import ordination.DagligFast;
import ordination.DagligSkaev;
import ordination.Laegemiddel;
import ordination.PN;
import ordination.Patient;

public class ControllerTest {

	LocalDate startDen;
	private Patient p = Controller.getTestController().opretPatient("121256-0512", "Jane Jensen", 63.4);
	private Laegemiddel l = Controller.getTestController().opretLaegemiddel("Paracetamol", 1, 1.5, 2, "Ml");

	@Before
	public void setUp() throws Exception {
		startDen = LocalDate.of(2019, 1, 1);
	}

	@Test
	public void OpretPnOrdinationTest() {
		// Testcase 1

		PN tc1 = Controller.getTestController().opretPNOrdination(startDen, LocalDate.of(2019, 1, 12), p, l, 1);
		assertTrue(p.getOrdinationer().contains(tc1));

		// Testcase 2

		PN tc2 = Controller.getTestController().opretPNOrdination(startDen, LocalDate.of(2019, 1, 12), p, l, 2);
		assertTrue(p.getOrdinationer().contains(tc2));

		// Testcase 3

		PN tc3 = Controller.getTestController().opretPNOrdination(startDen, LocalDate.of(2019, 1, 12), p, l, 8);
		assertTrue(p.getOrdinationer().contains(tc3));

		// Testcase 4

		PN tc4 = Controller.getTestController().opretPNOrdination(startDen, LocalDate.of(2019, 2, 12), p, l, 1);
		assertTrue(p.getOrdinationer().contains(tc4));

		// Testcase 5

		PN tc5 = Controller.getTestController().opretPNOrdination(startDen, LocalDate.of(2019, 6, 12), p, l, 1);
		assertTrue(p.getOrdinationer().contains(tc5));

		// Testcase 6
		try {
			PN tc6 = Controller.getTestController().opretPNOrdination(startDen, LocalDate.of(2018, 12, 30), p, l, 1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Din slut dato skal være efter din start dato");
		}

		// Testcase 7
		PN tc7 = Controller.getTestController().opretPNOrdination(startDen, LocalDate.of(2019, 6, 12), p, l, -1);
		assertTrue(p.getOrdinationer().contains(tc7));

	}

	@Test
	public void OpretDagligFastOrdinationTest() {
		// Testcase 1
		DagligFast tc1 = Controller.getTestController().opretDagligFastOrdination(startDen, LocalDate.of(2019, 01, 30),
				p, l, 3, 4, 5, 6);
		assertTrue(p.getOrdinationer().contains(tc1));

		// Testcase 2
		DagligFast tc2 = Controller.getTestController().opretDagligFastOrdination(startDen, LocalDate.of(2019, 01, 30),
				p, l, 1, 4, 5, 0);
		assertTrue(p.getOrdinationer().contains(tc2));

		// Testcase 3
		DagligFast tc3 = Controller.getTestController().opretDagligFastOrdination(startDen, LocalDate.of(2019, 01, 30),
				p, l, 0.1, 0.1, 4, 4);
		assertTrue(p.getOrdinationer().contains(tc3));

		// Testcase 4
		DagligFast tc4 = Controller.getTestController().opretDagligFastOrdination(startDen, LocalDate.of(2019, 01, 30),
				p, l, 9999, 3, 4, 0);
		assertTrue(p.getOrdinationer().contains(tc4));

		try {
			DagligFast tc5 = Controller.getTestController().opretDagligFastOrdination(startDen,
					LocalDate.of(2018, 01, 01), p, l, 1, 3, 4, 0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Din slut dato skal være efter din start dato");
		}
	}

	@Test
	public void OpretDagligSkaevOrdinationTest() {
		// Testcase 1
		LocalTime[] klokkeslet1 = new LocalTime[4];
		klokkeslet1[0] = LocalTime.of(10, 30);
		klokkeslet1[1] = LocalTime.of(12, 30);
		klokkeslet1[2] = LocalTime.of(16, 30);
		klokkeslet1[3] = LocalTime.of(00, 00);

		double[] antalenheder1 = new double[4];
		antalenheder1[0] = 2;
		antalenheder1[1] = 2;
		antalenheder1[2] = 5;
		antalenheder1[3] = 2;

		DagligSkaev tc1 = Controller.getTestController().opretDagligSkaevOrdination(startDen,
				LocalDate.of(2019, 01, 30), p, l, klokkeslet1, antalenheder1);
		assertTrue(p.getOrdinationer().contains(tc1));

		// Testcase 2
		LocalTime[] klokkeslet2 = new LocalTime[4];
		klokkeslet1[0] = LocalTime.of(00, 00);
		klokkeslet1[1] = LocalTime.of(12, 30);
		klokkeslet1[2] = LocalTime.of(16, 30);
		klokkeslet1[3] = LocalTime.of(00, 00);

		DagligSkaev tc2 = Controller.getTestController().opretDagligSkaevOrdination(startDen,
				LocalDate.of(2019, 01, 30), p, l, klokkeslet2, antalenheder1);
		assertTrue(p.getOrdinationer().contains(tc2));

		// Testcase 3
		double[] antalenheder2 = new double[4];
		antalenheder2[0] = -3;
		antalenheder2[1] = -2;
		antalenheder2[2] = 5;
		antalenheder2[3] = 2;

		try {
			DagligSkaev tc3 = Controller.getTestController().opretDagligSkaevOrdination(startDen,
					LocalDate.of(2019, 01, 30), p, l, klokkeslet1, antalenheder2);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Antal skal være positivt");
		}

		try {
			DagligSkaev tc4 = Controller.getTestController().opretDagligSkaevOrdination(startDen,
					LocalDate.of(2018, 01, 01), p, l, klokkeslet1, antalenheder1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Din slut dato skal være efter din start dato");
		}

		double[] antalenheder3 = new double[5];
		antalenheder3[0] = 1;
		antalenheder3[1] = 1;
		antalenheder3[2] = 5;
		antalenheder3[3] = 2;
		antalenheder3[4] = 5;

		try {
			DagligSkaev tc5 = Controller.getTestController().opretDagligSkaevOrdination(startDen,
					LocalDate.of(2019, 01, 30), p, l, klokkeslet1, antalenheder3);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Dine to lister skal være lige lange");
		}
	}

	@Test
	public void ordinationPNAnvendtTest() {
		PN tc1 = Controller.getTestController().opretPNOrdination(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 12),
				p, l, 123);

		// Testcase 1
		LocalDate date = LocalDate.of(2019, 01, 01);

		Controller.getTestController().ordinationPNAnvendt(tc1, date);

		assertTrue(tc1.getHuskedeDatoer().contains(date));

		// Testcase 2
		date = LocalDate.of(2019, 01, 02);

		Controller.getTestController().ordinationPNAnvendt(tc1, date);

		assertTrue(tc1.getHuskedeDatoer().contains(date));

		// Testcase 3
		date = LocalDate.of(2019, 01, 06);

		Controller.getTestController().ordinationPNAnvendt(tc1, date);

		assertTrue(tc1.getHuskedeDatoer().contains(date));

		// Testcase 4
		date = LocalDate.of(2019, 01, 11);

		Controller.getTestController().ordinationPNAnvendt(tc1, date);

		assertTrue(tc1.getHuskedeDatoer().contains(date));

		// Testcase 5
		date = LocalDate.of(2019, 01, 12);

		Controller.getTestController().ordinationPNAnvendt(tc1, date);

		assertTrue(tc1.getHuskedeDatoer().contains(date));

		// Testcase 6
		date = LocalDate.of(2018, 01, 30);

		try {
			Controller.getTestController().ordinationPNAnvendt(tc1, date);

			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Datoen er ude fra gyldighedsperioden");
		}

		// Testcase 7
		date = LocalDate.of(2019, 01, 13);

		try {
			Controller.getTestController().ordinationPNAnvendt(tc1, date);

			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Datoen er ude fra gyldighedsperioden");
		}
	}

	@Test
	public void anbefaletDosisPrDoegnTest() {
		// Testcase 1
		Patient p2 = Controller.getTestController().opretPatient("121256-0512", "Jane Jensen", 24.4);

		double tc = Controller.getTestController().anbefaletDosisPrDoegn(p2, l);

		assertEquals(24.4, tc, 0.0001);

		// Testcase 2
		p2 = Controller.getTestController().opretPatient("121256-0512", "Jane Jensen", 25.0);

		tc = Controller.getTestController().anbefaletDosisPrDoegn(p2, l);

		assertEquals(37.5, tc, 0.0001);

		// Testcase 3
		p2 = Controller.getTestController().opretPatient("121256-0512", "Jane Jensen", 50.0);

		tc = Controller.getTestController().anbefaletDosisPrDoegn(p2, l);

		assertEquals(75.00, tc, 0.0001);

		// Testcase 4
		p2 = Controller.getTestController().opretPatient("121256-0512", "Jane Jensen", 120.0);

		tc = Controller.getTestController().anbefaletDosisPrDoegn(p2, l);

		assertEquals(180.00, tc, 0.0001);

		// Testcase 5
		p2 = Controller.getTestController().opretPatient("121256-0512", "Jane Jensen", 121.0);

		tc = Controller.getTestController().anbefaletDosisPrDoegn(p2, l);

		assertEquals(242.00, tc, 0.0001);

		// Testcase 6
		p2 = Controller.getTestController().opretPatient("121256-0512", "Jane Jensen", 150.0);

		tc = Controller.getTestController().anbefaletDosisPrDoegn(p2, l);

		assertEquals(300.00, tc, 0.0001);

		// Testcase 7
		p2 = Controller.getTestController().opretPatient("121256-0512", "Jane Jensen", -1.0);

		tc = Controller.getTestController().anbefaletDosisPrDoegn(p2, l);

		assertEquals(-1.00, tc, 0.0001);
	}
}
