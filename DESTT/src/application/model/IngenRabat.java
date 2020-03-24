package application.model;

/**
 * Modellerer en tom rabat
 *
 * @author Sille, Simon og Michelle
 *
 */

public class IngenRabat implements Rabat {

	/**
	 * Initialiserer ingenRabat
	 */
	public IngenRabat() {
	}

	/**
	 * Beregner prisen(som altid er 0)
	 */
	@Override
	public double beregnPris(double pris) {
		return pris;
	}

	/**
	 * Returnerer rabatten
	 */
	@Override
	public double getRabat() {
		return 0;
	}

}
