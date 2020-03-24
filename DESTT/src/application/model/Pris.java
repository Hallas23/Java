package application.model;

import java.io.Serializable;

/**
 * Modellerer en ny pris indeholdende et produkt
 *
 * @author Sille, Simon og Michelle
 */

public class Pris implements Serializable {

	private double pris;
	private Produkt produkt;
	private Prisliste prisliste;

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

	/**
	 * Returnerer prisens pris
	 * 
	 * @return produkt
	 */

	public void setPris(double pris) {
		this.pris = pris;
	}

	/**
	 * retunerer prisens prisliste
	 * 
	 * @return
	 */

	public Prisliste getPrisliste() {
		return prisliste;
	}

	/**
	 * Sætter prisens prisliste
	 * 
	 * @return
	 */

	public void setPrisliste(Prisliste prisliste) {
		this.prisliste = prisliste;
		prisliste.addPris(this);
	}

	@Override
	public String toString() {
		return produkt.getNavn() + ", " + this.getPris() + " kr.";
	}

}
