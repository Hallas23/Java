package application.model;

import java.io.Serializable;

/**
 * 
 * @author Sille, Simon og Michelle
 *
 */

public interface Rabat extends Serializable {
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
