package application.model;

/**
 * Modellerer en rabat, når den er sat som et fast beløb
 *
 * @author Sille, Simon og Michelle
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

	/**
	 * Beregner en pris for en salgslinje der indeholder en PrisRabat
	 * 
	 */

	@Override
	public double beregnPris(double pris) {
		return pris - prisrabat;
	}

	/**
	 * Retunere PrisRabat
	 * 
	 */

	@Override
	public double getRabat() {
		return prisrabat;
	}

}
