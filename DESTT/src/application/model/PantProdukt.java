package application.model;

/**
 * Modellerer et nyt produkt af typen pantProdukt
 * 
 * @author Sille, Simon og Michelle
 *
 */

public class PantProdukt extends Produkt {

	private double pantPris;

	/**
	 * Initialiserer et nyt pantprodukt
	 * 
	 * @param navn
	 * @param antalPåLager
	 * @param pantPris
	 */
	PantProdukt(String navn, int antalPåLager, double pantPris) {
		super(navn, antalPåLager);
		this.pantPris = pantPris;
	}

	/**
	 * Returnerer pantprisen for produktet
	 * 
	 * @return pantpris
	 */
	public double getPantPris() {
		return pantPris;
	}

	/**
	 * Sætter pantprisen for produktet
	 * 
	 * @return pantpris
	 */

	public void setPantPris(double pantPris) {
		this.pantPris = pantPris;
	}

	@Override
	public String toString() {
		return "PantProdukt [navn=" + getNavn() + ", antalPåLager=" + getAntalPåLager() + " pantprodukt=" + pantPris
				+ "]";

	}
}
