package opgave3;

import static org.junit.Assert.*;
import org.junit.Test;

public class PremiumTest {

	@Test
	public void testCalculatePremium() {
		Premium premium1 = new Premium(1000);
		Premium premium2 = new Premium(2000);
		
		double test1 = premium1.CalculatePremium(19, true, 0);
		assertEquals(1187.5, test1, 0.001);
		
		double test2 = premium1.CalculatePremium(25, true, 0);
		assertEquals(950, test2, 0.001);
		
		double test3 = premium2.CalculatePremium(19, true, 0);
		assertEquals(2375, test3, 0.001);
		
		double test4 = premium1.CalculatePremium(24, false, 5);
		assertEquals(1062.5, test4, 0.001);

	}

}
