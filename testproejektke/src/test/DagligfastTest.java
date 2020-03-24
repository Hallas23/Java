package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import controller.Controller;
import ordination.DagligFast;
import ordination.Dosis;
import ordination.Laegemiddel;
import ordination.Patient;

public class DagligfastTest {

	private Patient p = Controller.getTestController().opretPatient("121256-0512", "Jane Jensen", 63.4);
	private Laegemiddel l = Controller.getTestController().opretLaegemiddel("Paracetamol", 1, 1.5, 2, "Ml");

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void doegDosisTest() throws Exception {

//		Testcase 1
		double tc1 = Controller.getTestController()
				.opretDagligFastOrdination(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 30), p, l, 1, 0, 0, 0)
				.doegnDosis();

		assertEquals(1, tc1, 0.001);

//		Testcase 2
		double tc2 = Controller.getTestController()
				.opretDagligFastOrdination(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 30), p, l, 0, 1, 0, 0)
				.doegnDosis();

		assertEquals(1, tc2, 0.001);

//		Testcase 3
		double tc3 = Controller.getTestController()
				.opretDagligFastOrdination(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 30), p, l, 0, 0, 1, 0)
				.doegnDosis();

		assertEquals(1, tc3, 0.001);

//		Testcase 4
		double tc4 = Controller.getTestController()
				.opretDagligFastOrdination(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 30), p, l, 0, 0, 0, 1)
				.doegnDosis();

		assertEquals(1, tc4, 0.001);

//		Testcase 5
		double tc5 = Controller.getTestController()
				.opretDagligFastOrdination(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 30), p, l, 1, 1, 1, 1)
				.doegnDosis();

		assertEquals(4, tc5, 0.001);

//		Testcase 6
		double tc6 = Controller.getTestController().opretDagligFastOrdination(LocalDate.of(2019, 1, 1),
				LocalDate.of(2019, 1, 30), p, l, 0.1, 0.1, 0.1, 0.1).doegnDosis();

		assertEquals(0, tc6, 0.001);

//		Testcase 7
		double tc7 = Controller.getTestController().opretDagligFastOrdination(LocalDate.of(2019, 1, 1),
				LocalDate.of(2019, 1, 30), p, l, 1111, 1111, 1111, 1111).doegnDosis();

		assertEquals(4444, tc7, 0.001);

	}

	@Test
	public void samletDosis() throws Exception {
//		Testcase 1
		double tc1Samlet = Controller.getTestController()
				.opretDagligFastOrdination(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 10), p, l, 2, 2, 2, 0)
				.samletDosis();

		assertEquals(60, tc1Samlet, 0.001);
	}

	@Test

	public void opretDosis() throws Exception {
//		Testcase 1
		DagligFast tc1Opret = new DagligFast(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 10));

		tc1Opret.opretDosis(LocalTime.of(8, 00), 2.0);

		Dosis d1 = tc1Opret.opretDosis(LocalTime.of(8, 00), 2.0);

		assertTrue(tc1Opret.getDoser()[0].equals(d1));

//			Testcase 2

		tc1Opret.opretDosis(LocalTime.of(12, 00), 2.0);

		Dosis d2 = tc1Opret.opretDosis(LocalTime.of(12, 00), 2.0);

		assertTrue(tc1Opret.getDoser()[1].equals(d2));

//			Testcase 3

		tc1Opret.opretDosis(LocalTime.of(18, 00), 2.0);

		Dosis d3 = tc1Opret.opretDosis(LocalTime.of(18, 00), 2.0);

		assertTrue(tc1Opret.getDoser()[2].equals(d3));

//				Testcase 4

		tc1Opret.opretDosis(LocalTime.of(00, 00), 2.0);

		Dosis d4 = tc1Opret.opretDosis(LocalTime.of(00, 00), 2.0);

		assertTrue(tc1Opret.getDoser()[3].equals(d4));

//				Testcase 5
		try {
			tc1Opret.opretDosis(LocalTime.of(8, 00), -2.0);
			fail();
		} catch (RuntimeException e) {
			assertEquals(e.getMessage(), "Antal skal være positivt");
		}

//			Testcase 6
		try {
			tc1Opret.opretDosis(LocalTime.of(24, 00), 2.0);
			fail();
		} catch (RuntimeException e) {
			assertEquals(e.getMessage(), "Invalid value for HourOfDay (valid values 0 - 23): 24");
		}

	}

}
