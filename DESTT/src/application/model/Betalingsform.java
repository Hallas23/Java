package application.model;

import java.io.Serializable;

/**
 * Modellerer en betalingsform som bruges ved salg af produkter, rundvisninger
 * og udlejninger
 * 
 * @author Sille, Simon og Michelle
 *
 */

public class Betalingsform implements Serializable {

	/**
	 * 
	 */
	private String navn;

	/**
	 * Initialiserer en ny betalingsform med et navn
	 * 
	 * @param navn
	 */
	public Betalingsform(String navn) {
		this.setNavn(navn);
	}

	/**
	 * Returnerer betalingsformens navn
	 * 
	 * @return navn
	 */
	public String getNavn() {
		return navn;
	}

	/**
	 * Ændrer betalingsformens navn Pre: navn.length > 0
	 * 
	 * @param navn
	 */
	public void setNavn(String navn) {
		this.navn = navn;
	}

	@Override
	public String toString() {
		return navn;
	}

}
