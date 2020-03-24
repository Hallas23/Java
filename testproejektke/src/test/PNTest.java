package test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import controller.Controller;
import ordination.Laegemiddel;
import ordination.PN;
import ordination.Patient;

public class PNTest {
	private PN pn;
	private Patient p = new Patient("121256-0512", "Jane Jensen", 63.4);
	private Laegemiddel lm = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");

	@Before
	public void setUp() throws Exception {
		pn = Controller.getTestController().opretPNOrdination(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 12), p,
				lm, 123);
	}

	@Test
	public void givDosisTest() {
		// TC1
		LocalDate d = LocalDate.of(2019, 1, 1);

		boolean actual = pn.givDosis(d);
		assertEquals(true, actual);

		// TC2

		d = LocalDate.of(2019, 1, 6);
		actual = pn.givDosis(d);

		assertEquals(true, actual);

		// TC3

		d = LocalDate.of(2019, 1, 11);
		actual = pn.givDosis(d);

		assertEquals(true, actual);

		// TC4

		d = LocalDate.of(2019, 1, 12);
		actual = pn.givDosis(d);

		assertEquals(true, actual);

		// TC5

		d = LocalDate.of(2018, 1, 11);
		actual = pn.givDosis(d);

		assertEquals(false, actual);

		// TC6

		d = LocalDate.of(2019, 1, 13);
		actual = pn.givDosis(d);

		assertEquals(false, actual);

		// TC7

		d = LocalDate.of(2019, 1, 28);
		actual = pn.givDosis(d);

		assertEquals(false, actual);
	}

	@Test
	public void doegnDosisTest() {
		// TC1

		pn = Controller.getTestController().opretPNOrdination(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 12), p,
				lm, 1);

		pn.givDosis(LocalDate.of(2019, 1, 1));
		pn.givDosis(LocalDate.of(2019, 1, 6));
		pn.givDosis(LocalDate.of(2019, 1, 8));
		pn.givDosis(LocalDate.of(2019, 1, 10));
		pn.givDosis(LocalDate.of(2019, 1, 11));

		double actual = pn.doegnDosis();

		double expected = 0.45;
		assertEquals(expected, actual, 0.01);

		// TC2

		pn = Controller.getTestController().opretPNOrdination(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 12), p,
				lm, 2);

		pn.givDosis(LocalDate.of(2019, 1, 1));
		pn.givDosis(LocalDate.of(2019, 1, 6));
		pn.givDosis(LocalDate.of(2019, 1, 8));
		pn.givDosis(LocalDate.of(2019, 1, 10));
		pn.givDosis(LocalDate.of(2019, 1, 11));

		actual = pn.doegnDosis();

		expected = 0.90;
		assertEquals(expected, actual, 0.01);

		// TC3

		pn = Controller.getTestController().opretPNOrdination(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 12), p,
				lm, 8);

		pn.givDosis(LocalDate.of(2019, 1, 1));
		pn.givDosis(LocalDate.of(2019, 1, 6));
		pn.givDosis(LocalDate.of(2019, 1, 8));
		pn.givDosis(LocalDate.of(2019, 1, 10));
		pn.givDosis(LocalDate.of(2019, 1, 11));

		actual = pn.doegnDosis();

		expected = 3.63;
		assertEquals(expected, actual, 0.01);

		// TC4

		pn = Controller.getTestController().opretPNOrdination(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 12), p,
				lm, 1);

		pn.givDosis(LocalDate.of(2019, 1, 1));

		actual = pn.doegnDosis();

		expected = 1;
		assertEquals(expected, actual, 0.01);

		// TC5

		pn = Controller.getTestController().opretPNOrdination(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 12), p,
				lm, 1);

		actual = pn.doegnDosis();

		expected = 0;
		assertEquals(expected, actual, 0.01);

		// TC6

		pn = Controller.getTestController().opretPNOrdination(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 12), p,
				lm, 8);

		actual = pn.doegnDosis();

		expected = 0;
		assertEquals(expected, actual, 0.01);

		// TC7

		pn = Controller.getTestController().opretPNOrdination(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 12), p,
				lm, 0);

		pn.givDosis(LocalDate.of(2019, 1, 1));
		pn.givDosis(LocalDate.of(2019, 1, 6));
		pn.givDosis(LocalDate.of(2019, 1, 8));
		pn.givDosis(LocalDate.of(2019, 1, 10));
		pn.givDosis(LocalDate.of(2019, 1, 11));

		actual = pn.doegnDosis();

		expected = 0;
		assertEquals(expected, actual, 0.01);

		// TC8

		pn = Controller.getTestController().opretPNOrdination(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 12), p,
				lm, -1);

		pn.givDosis(LocalDate.of(2019, 1, 1));
		pn.givDosis(LocalDate.of(2019, 1, 6));
		pn.givDosis(LocalDate.of(2019, 1, 8));
		pn.givDosis(LocalDate.of(2019, 1, 10));
		pn.givDosis(LocalDate.of(2019, 1, 11));

		actual = pn.doegnDosis();

		expected = 0;
		assertEquals(expected, actual, 0.01);
	}

	@Test
	public void samletDosisTest() {
		// TC1

		pn = Controller.getTestController().opretPNOrdination(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 12), p,
				lm, 2.2);

		pn.givDosis(LocalDate.of(2019, 1, 1));
		pn.givDosis(LocalDate.of(2019, 1, 6));
		pn.givDosis(LocalDate.of(2019, 1, 8));
		pn.givDosis(LocalDate.of(2019, 1, 10));
		pn.givDosis(LocalDate.of(2019, 1, 11));

		double actual = pn.samletDosis();

		double expected = 11;
		assertEquals(expected, actual, 0.01);

		// TC2

		pn = Controller.getTestController().opretPNOrdination(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 12), p,
				lm, 4.4);

		pn.givDosis(LocalDate.of(2019, 1, 1));
		pn.givDosis(LocalDate.of(2019, 1, 6));
		pn.givDosis(LocalDate.of(2019, 1, 8));
		pn.givDosis(LocalDate.of(2019, 1, 10));
		pn.givDosis(LocalDate.of(2019, 1, 11));

		actual = pn.samletDosis();

		expected = 22;
		assertEquals(expected, actual, 0.01);

		// TC3

		pn = Controller.getTestController().opretPNOrdination(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 12), p,
				lm, 17.6);

		pn.givDosis(LocalDate.of(2019, 1, 1));
		pn.givDosis(LocalDate.of(2019, 1, 6));
		pn.givDosis(LocalDate.of(2019, 1, 8));
		pn.givDosis(LocalDate.of(2019, 1, 10));
		pn.givDosis(LocalDate.of(2019, 1, 11));

		actual = pn.samletDosis();

		expected = 88;
		assertEquals(expected, actual, 0.01);

		// TC4

		pn = Controller.getTestController().opretPNOrdination(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 12), p,
				lm, 0);

		pn.givDosis(LocalDate.of(2019, 1, 1));
		pn.givDosis(LocalDate.of(2019, 1, 6));
		pn.givDosis(LocalDate.of(2019, 1, 8));
		pn.givDosis(LocalDate.of(2019, 1, 10));
		pn.givDosis(LocalDate.of(2019, 1, 11));

		actual = pn.samletDosis();

		expected = 0;
		assertEquals(expected, actual, 0.01);

	}

}
