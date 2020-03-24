package application.model;

/**
 * Modellerer en procentrabat
 * 
 * @author Sille, Simon og Michelle
 */

public class ProcentRabat implements Rabat {

	private double procent;

	/**
	 * Opretter en ny procent Rabat Pre: procent >= 0
	 * 
	 * @param procent
	 */
	public ProcentRabat(double procent) {
		this.procent = procent;
	}

	/**
	 * Beregner prisen for procentrabatten
	 */
	@Override
	public double beregnPris(double pris) {
		return pris - (procent / 100 * pris);
	}

	/**
	 * Returnerer procentrabatten
	 */
	@Override
	public double getRabat() {
		return procent;
	}

}
