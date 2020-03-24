package opgave1;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class Opgave1Test {

	@Test
	public void testCalculateFine() {
		
		int actualResult = Opgave1.calculateFine(LocalDate.of(2019, 10, 1), LocalDate.of(2020, 10, 5), true);
		assertEquals(90, actualResult, 0.001);
	}

}
