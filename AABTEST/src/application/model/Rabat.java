package application.model;

public interface Rabat {
	/**
	 * Returnerer prisen efter rabatten er trukket fra
	 * 
	 * @param pris
	 * @return
	 */
	double beregnPris(double pris);

	/**
	 * Returnerer størrelsen af rabatten
	 * 
	 * @return
	 */
	double getRabat();

}
