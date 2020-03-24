package application.model;

/**
 * Modellerer en ny pris indeholdende et produkt
 *
 */

public class Pris {

	private double pris;
	private Produkt produkt;

	/**
	 * Initialiserer en ny pris med pris og det tilknyttede produkt
	 * 
	 * @param pris
	 * @param produkt
	 */
	Pris(double pris, Produkt produkt) {
		this.pris = pris;
		this.produkt = produkt;
	}

	/**
	 * Returnerer en pris
	 * 
	 * @return pris
	 */
	public double getPris() {
		return pris;
	}

	/**
	 * Sætter et produkt til prisen
	 * 
	 * @param produkt
	 */
	public void setProdukt(Produkt p) {
		this.produkt = p;
	}

	/**
	 * Returnerer prisens produkt
	 * 
	 * @return produkt
	 */
	public Produkt getProdukt() {
		return produkt;
	}

	public void setPris(double pris) {
		this.pris = pris;
	}
	
	

}
