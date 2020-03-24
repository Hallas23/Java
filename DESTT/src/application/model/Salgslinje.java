package application.model;

import java.io.Serializable;

/**
 * Modellerer en salgslinje som hovedsageligt holder styr på antallet af et
 * bestemt produkt tilføjet til et salg
 *
 * @author Sille, Simon og Michelle
 */

public class Salgslinje implements Serializable {

	private int antal;
	private Rabat rabat;
	private Pris produktPris;

	/**
	 * Initialiserer en ny salgslinje
	 * 
	 * @param antal
	 * @param rabat
	 * @param pris
	 */
	public Salgslinje(int antal, Rabat rabat, Pris pris) {
		this.antal = antal;
		this.rabat = rabat;
		this.produktPris = pris;
	}

	/**
	 * Returnerer prisen for produktet
	 * 
	 * @return produktPris
	 */
	public Pris getProdukPris() {
		return produktPris;
	}

	/**
	 * Sætter produktets pris
	 * 
	 * @param produkPris
	 */
	public void setProdukPris(Pris produkPris) {
		this.produktPris = produkPris;
	}

	/**
	 * Returnerer antallet af produkter i salgslinjen
	 * 
	 * @return antal
	 */
	public int getAntal() {
		return antal;
	}

	/**
	 * Returnerer rabatten for salgslinjen
	 * 
	 * @return
	 */
	public Rabat getRabat() {
		return rabat;
	}

	/**
	 * Returnerer produktet
	 * 
	 * @return produkt
	 */
	public Produkt getProdukt() {
		return produktPris.getProdukt();
	}

	/**
	 * Returnerer prisen for salgslinjen
	 * 
	 * @return pris
	 */
	public double getPris() {
		return rabat.beregnPris(produktPris.getPris() * antal);
	}

	/**
	 * Sætter antallet af produktet
	 * 
	 * @param antal
	 */

	public void setAntal(int antal) {
		this.antal = antal;
	}

	/**
	 * Sætter rabbaten
	 * 
	 * @param antal
	 */

	public void setRabat(Rabat rabat) {
		this.rabat = rabat;
	}

	@Override
	public String toString() {
		String result = antal + "x " + getProdukt().getNavn() + " (" + this.getPris() + " kr)";

		return result;
	}

}
