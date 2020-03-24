package application.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Modellerer en ny produktgruppe
 *
 * @author Sille, Simon og Michelle
 */

public class Produktgruppe implements Serializable {
	private String navn;
	private boolean udlejningsprodukt;
	private final List<Produkt> produkter = new ArrayList<>();

	/**
	 * Initialiserer en produktgruppe med navn og om produktet er et
	 * udlejningsprodukt
	 * 
	 * @param navn
	 * @param udlejningsprodukt
	 */
	public Produktgruppe(String navn, boolean udlejningsprodukt) {
		this.navn = navn;
		this.udlejningsprodukt = udlejningsprodukt;
	}

	/**
	 * Opretter et nyt produkt med navn og antal på lager
	 * 
	 * @param navn
	 * @param antalPåLager
	 * @return
	 */
	public Produkt CreateProdukt(String navn, int antalPåLager) {
		Produkt produkt = new Produkt(navn, antalPåLager);
		produkter.add(produkt);
		produkt.setProduktgruppe(this);
		return produkt;
	}

	/**
	 * Tilføjer et produkt til produktgruppen
	 * 
	 * @param produkt
	 */
	public void addProdukt(Produkt produkt) {
		if (!produkter.contains(produkt))
			produkter.add(produkt);
	}

	/**
	 * Fjerner et produkt fra produktgruppen
	 * 
	 * @param produkt
	 */
	public void removeProdukt(Produkt produkt) {
		if (produkter.contains(produkt))
			produkter.remove(produkt);
	}

	/**
	 * Opretter en gaveæske
	 * 
	 * @param navn
	 * @param antalPåLager
	 * @param glasAntal
	 * @param flaskeAntal
	 * @return
	 */
	public Gaveæske CreateGaveæske(String navn, int antalPåLager, int glasAntal, int flaskeAntal) {
		Gaveæske gaveæske = new Gaveæske(navn, antalPåLager, glasAntal, flaskeAntal);
		produkter.add(gaveæske);
		gaveæske.setProduktgruppe(this);
		return gaveæske;
	}

	/**
	 * Opretter et pantprodukt
	 * 
	 * @param navn
	 * @param antalPåLager
	 * @param pantpris
	 * @return
	 */
	public PantProdukt CreatePantProdukt(String navn, int antalPåLager, double pantpris) {
		PantProdukt pantprodukt = new PantProdukt(navn, antalPåLager, pantpris);
		produkter.add(pantprodukt);
		pantprodukt.setProduktgruppe(this);
		return pantprodukt;
	}

	/**
	 * Returnerer en liste over denne produktgruppes produkter
	 * 
	 * @return
	 */
	public List<Produkt> getProdukter() {
		return new ArrayList<>(produkter);
	}

	/**
	 * Returnerer produktgruppens navn
	 * 
	 * @return navn
	 */
	public String getNavn() {
		return navn;
	}

	/**
	 * Returnerer om produkter i denne produktgruppe kan udlejes
	 * 
	 * @return
	 */
	public boolean isUdlejningsprodukt() {
		return udlejningsprodukt;
	}

	/**
	 * Sætter produktgruppens navn
	 *
	 */

	public void setNavn(String navn) {
		this.navn = navn;
	}

	/**
	 * Sætter om produktgruppen er en udlejingsgruppe
	 *
	 */

	public void setUdlejningsprodukt(boolean udlejningsprodukt) {
		this.udlejningsprodukt = udlejningsprodukt;
	}

	@Override
	public String toString() {
		String result;
		if (this.isUdlejningsprodukt()) {
			result = navn + " (udlejningsprodukt)";
		} else {
			result = navn;
		}
		return result;
	}

}
