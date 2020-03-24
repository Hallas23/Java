package application.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Modellerer en prisliste som evt bruges til et arrangement, fredagsbar eller
 * butik indeholder priser som er unikke for salgssituationen
 *
 */

public class Prisliste {

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
	public Pris CreatePris(int pris, Produkt produkt) {
		Pris prisprodukt = new Pris(pris, produkt);
		priser.add(prisprodukt);
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
	 * Viser om prisen er indeholdt i prislisten
	 * 
	 * @param pris
	 * @return contains
	 */
	public boolean contains(Pris pris) {
		if (priser.contains(pris))
			return true;
		return false;
	}
	
	public List<Pris> getPriser() {
		return new ArrayList<>(priser);
	}

}
