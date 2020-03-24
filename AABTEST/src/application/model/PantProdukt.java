package application.model;

/**
 * Modellerer et nyt produkt af typen pantProdukt
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
	public PantProdukt(String navn, int antalPåLager, double pantPris) {
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

	public void setPantPris(double pantPris) {
		this.pantPris = pantPris;
	}

	@Override
	public String toString() {
		return "PantProdukt [navn=" + getNavn() + ", antalPåLager=" + getAntalPåLager() + " pantprodukt=" + pantPris
				+ "]";

	}
}
