package opgave3;

import javax.management.RuntimeErrorException;

public class Premium {
	
		double basisPremium;
		
		public Premium (double premium) {
			basisPremium = premium;
		}
		
	
	
	
		public double CalculatePremium(int age, boolean isWoman, int yearsWithoutDamage) {
			double premium = basisPremium;
			if (age < 18 || yearsWithoutDamage < 0 || yearsWithoutDamage > age-18) {
				throw new RuntimeErrorException(null, "Indtast ordenligt");
			}
			if (age < 25) {
				premium = premium * 1.25;
			}
			
			if (isWoman) {
				premium = premium * 0.95;
			}
				
			if (yearsWithoutDamage <= 5 && yearsWithoutDamage >= 3 ) {
				premium = premium * 0.85;
			}
			
			if (yearsWithoutDamage <= 8 && yearsWithoutDamage >= 6 ) {
				premium = premium * 0.75;
			}
			
			if (yearsWithoutDamage > 8) {
				premium = premium * 0.65;
			}
			
			
			return premium;
		}
}
