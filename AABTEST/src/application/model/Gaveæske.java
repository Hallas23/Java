package application.model;

/**
 * Modellerer en gaveæske med andre produkter
 */

import java.util.ArrayList;
import java.util.List;

public class Gaveæske extends Produkt {

	private int glasAntal;
	private int flaskeAntal;
	final private List<Produkt> produkter;

	/**
	 * Initialiserer en ny gaveæske Pre: navn.length >0 Pre: flaskAntal + glasAntal
	 * >0
	 * 
	 * @param navn
	 * @param antalPåLager
	 * @param glasAntal
	 * @param flaskeAntal
	 */
	public Gaveæske(String navn, int antalPåLager, int glasAntal, int flaskeAntal) {
		super(navn, antalPåLager);
		this.glasAntal = glasAntal;
		this.flaskeAntal = flaskeAntal;
		produkter = new ArrayList<>();
	}

	/**
	 * Returnerer antallet af glas der skal i denne gaveæske
	 * 
	 * @return glasAntal
	 */
	public int getGlasAntal() {
		return glasAntal;
	}

	/**
	 * Returnerer antallet af flasker der skal i denne gaveæske
	 * 
	 * @return flaskeAntal
	 */
	public int getFlaskeAntal() {
		return flaskeAntal;
	}

	/**
	 * Tilføjer en liste af produkter til denne gaveæske
	 * 
	 * @param produkter
	 */
	public void addProdukt(Produkt produkt) {
		produkter.add(produkt);
	}

	/**
	 * Fjerner en liste af produkter fra denne gaveæske
	 * 
	 * @param produkter
	 */
	public void removeProdukt(Produkt produkt) {
		produkter.remove(produkt);
	}

	/**
	 * Returnerer produkterne fra gaveæsken
	 * 
	 * @return
	 */
	public List<Produkt> getProdukter() {
		return new ArrayList<>(produkter);
	}

	/**
	 * Fjerner alle produkter fra gaveæsken
	 */
	public void reset() {
		produkter.clear();
	}

	@Override
	public String toString() {
		return "Gaveæske [navn= " + getNavn() + ", antal på lager= " + getAntalPåLager() + " , glasAntal= " + glasAntal
				+ ", flaskeAntal= " + flaskeAntal + "]";
	}

	public void setGlasAntal(int glasAntal) {
		this.glasAntal = glasAntal;
	}

	public void setFlaskeAntal(int flaskeAntal) {
		this.flaskeAntal = flaskeAntal;
	}
	
	

}
