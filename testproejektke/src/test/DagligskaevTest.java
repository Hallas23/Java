package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import controller.Controller;
import ordination.DagligSkaev;
import ordination.Laegemiddel;
import ordination.Patient;

public class DagligskaevTest {
	private LocalDate startDen;
	private LocalDate slutDen;
	private DagligSkaev ds = new DagligSkaev(startDen, slutDen);
	private Patient p1 = Controller.getTestController().opretPatient("121256-0512", "Jane Jensen", 63.4);
	private Laegemiddel l1 = Controller.getTestController().opretLaegemiddel("Paracetamol", 1, 1.5, 2, "Ml");

	@Before
	public void setUp() throws Exception {
		startDen = LocalDate.of(2019, 01, 1);
		slutDen = LocalDate.of(2019, 01, 30);
	}

	@Test
	public void testOpretDosis() {
		// Testcase 1
		ds.opretDosis(LocalTime.of(12, 30), 3.0);
		assertEquals(1, ds.getDoser().size());

		// Testcase 2
		try {
			ds.opretDosis(LocalTime.of(24, 00), 2.0);
			fail();
		} catch (RuntimeException e) {
			assertEquals(e.getMessage(), "Invalid value for HourOfDay (valid values 0 - 23): 24");
		}

		// Testcase 3
		try {
			ds.opretDosis(LocalTime.of(13, 30), -1.0);
			fail();
		} catch (IllegalArgumentException i) {
			assertEquals(i.getMessage(), "Antal skal være positivt");
		}
	}

	@Test
	public void testDoegnDosis() {
		// Testcase 1
		DagligSkaev tc1 = new DagligSkaev(startDen, slutDen);
		tc1.opretDosis(LocalTime.of(8, 30), 2.0);
		tc1.opretDosis(LocalTime.of(10, 30), 2.0);
		tc1.opretDosis(LocalTime.of(16, 00), 3.0);

		double result = tc1.doegnDosis();
		assertEquals(7, result, 0.0001);

		// Testcase 2
		try {
			tc1.opretDosis(LocalTime.of(24, 00), 2.0);
			tc1.opretDosis(LocalTime.of(10, 30), 2.0);
			tc1.opretDosis(LocalTime.of(16, 00), 3.0);
			fail();
		} catch (RuntimeException e) {
			assertEquals(e.getMessage(), "Invalid value for HourOfDay (valid values 0 - 23): 24");
		}

		// Testcase 3
		try {
			tc1.opretDosis(LocalTime.of(13, 30), -3.0);
			tc1.opretDosis(LocalTime.of(10, 30), 2.0);
			tc1.opretDosis(LocalTime.of(16, 00), 3.0);
			fail();
		} catch (IllegalArgumentException i) {
			assertEquals(i.getMessage(), "Antal skal være positivt");
		}
	}

	@Test
	public void testSamletDosis() {
		// Testcase 1
		DagligSkaev tsd = new DagligSkaev(startDen, slutDen);
		tsd.opretDosis(LocalTime.of(16, 00), 4.0);
		double result = tsd.samletDosis();
		assertEquals(120, result, 0.0001);

		// Testcase 2
		DagligSkaev tsd1 = new DagligSkaev(startDen, slutDen);
		tsd1.opretDosis(LocalTime.of(12, 00), 2.0);
		result = tsd1.samletDosis();
		assertEquals(60, result, 0.0001);

		DagligSkaev tsd2 = new DagligSkaev(startDen, slutDen);
		tsd2.opretDosis(LocalTime.of(8, 00), 7.0);
		result = tsd2.samletDosis();
		assertEquals(210, result, 0.0001);
	}

}