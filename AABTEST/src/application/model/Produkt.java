package application.model;

/**
 * Modellerer et nyt produkt
 *
 */

public class Produkt {

	private String navn;
	private int antalPåLager;
	private Produktgruppe produktgruppe;

	/**
	 * Initialiserer et produkt med navn og antallet af dette produkt på lager
	 * 
	 * @param navn
	 * @param antalPåLager
	 */
	Produkt(String navn, int antalPåLager) {
		this.navn = navn;
		this.antalPåLager = antalPåLager;
	}

	@Override
	public String toString() {
		return "Produkt [navn=" + getNavn() + ", antalPåLager=" + getAntalPåLager() + "]";
	}

	/**
	 * Returnerer produktets navn
	 * 
	 * @return navn
	 */
	public String getNavn() {
		return navn;
	}

	/**
	 * Sætter produktets navn
	 * 
	 * @param navn
	 */
	public void setNavn(String navn) {
		this.navn = navn;
	}

	/**
	 * Returnerer antallet af dette produkt som er på lager
	 * 
	 * @return antalPåLager
	 */
	public int getAntalPåLager() {
		return antalPåLager;
	}

	/**
	 * Returnerer produktgruppen som produktet hører under
	 * 
	 * @return produktgruppe
	 */
	public Produktgruppe getProduktgruppe() {
		return produktgruppe;
	}

	/**
	 * Sætter produktgruppen
	 * 
	 * @param produktgruppe
	 */
	public void setProduktgruppe(Produktgruppe produktgruppe) {
		this.produktgruppe = produktgruppe;
	}

	public void setAntalPåLager(int antalPåLager) {
		this.antalPåLager = antalPåLager;
	}
	
	

}
