package application.model;

/**
 * Modellerer en rabat, når den er sat som et fast beløb
 *
 */

public class PrisRabat implements Rabat {

	private double prisrabat;

	/**
	 * Opretter en ny PrisRabat Pre: pris >= 0
	 * 
	 * @param prisrabat
	 */
	public PrisRabat(double prisrabat) {
		this.prisrabat = prisrabat;
	}

	@Override
	public double beregnPris(double pris) {
		return pris - prisrabat;
	}

	@Override
	public double getRabat() {
		return prisrabat;
	}

}
