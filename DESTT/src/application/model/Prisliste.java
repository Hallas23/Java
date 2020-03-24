package application.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Modellerer en prisliste som evt bruges til et arrangement, fredagsbar eller
 * butik indeholder priser som er unikke for salgssituationen
 *
 * @author Sille, Simon og Michelle
 */

public class Prisliste implements Serializable {

	private String navn;
	final private List<Pris> priser = new ArrayList<>();

	/**
	 * Initialiserer en ny prisliste med navn som beskriver salgssituationen
	 * 
	 * @param navn
	 */
	public Prisliste(String navn) {
		this.navn = navn;
	}

	/**
	 * Returnerer prislistens navn
	 * 
	 * @return navn
	 */
	public String getNavn() {
		return navn;
	}

	/**
	 * Sætter prislistens navn
	 * 
	 * @param navn
	 */
	public void setNavn(String navn) {
		this.navn = navn;
	}

	/**
	 * Opretter en ny pris til prislisten
	 * 
	 * @param pris
	 * @param produkt
	 * @return pris
	 */
	public Pris CreatePris(double pris, Produkt produkt) {
		Pris prisprodukt = new Pris(pris, produkt);
		priser.add(prisprodukt);
		prisprodukt.setPrisliste(this);
		return prisprodukt;
	}

	/**
	 * Tilføjer en pris til prislisten
	 * 
	 * @param pris
	 */
	public void addPris(Pris pris) {
		if (!priser.contains(pris))
			priser.add(pris);
	}

	/**
	 * Fjerner en pris fra prislisten
	 * 
	 * @param pris
	 */
	public void removePris(Pris pris) {
		if (priser.contains(pris))
			priser.remove(pris);
	}

	/**
	 * Retunere alle priser for prislisten
	 * 
	 * @return
	 */

	public List<Pris> getPriser() {
		return new ArrayList<>(priser);
	}

	@Override
	public String toString() {
		return this.getNavn();
	}

}
