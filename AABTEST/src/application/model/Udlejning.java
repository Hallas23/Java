package application.model;

/**
 * Modellerer en udlejning af et produkt
 */

import java.time.LocalDate;

public class Udlejning extends Salg {

	private LocalDate udlejningsDato, afleveringsDato;
	private String tlf;
	private String navn;
	private boolean afleveret;
	private Betalingsform betalingsform;

	/**
	 * Initialiserer en udlejning
	 * 
	 * @param navn
	 * @param tlf
	 * @param udlejningsDato
	 * @param afleveringsdato
	 */
	public Udlejning(String navn, String tlf, LocalDate udlejningsDato, LocalDate afleveringsdato) {
		super();
		this.navn = navn;
		this.setTlf(tlf);
		this.setUdlejningsDato(udlejningsDato);
		this.setAfleveringsDato(afleveringsdato);
		this.setAfleveret(false);
	}

	/**
	 * Bestemmer om produktet er afleveret
	 * 
	 * @return afleveret
	 */
	public boolean isAfleveret() {
		return afleveret;
	}

	/**
	 * Sætter produktet som afleveret
	 * 
	 * @param afleveret
	 */
	public void setAfleveret(boolean afleveret) {
		this.afleveret = afleveret;
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
	 * Returnerer udlejerens telefonnummer
	 * 
	 * @return
	 */
	public String getTlf() {
		return tlf;
	}

	/**
	 * Sætter udlejerens telefonnummer
	 * 
	 * @param tlf
	 */
	public void setTlf(String tlf) {
		this.tlf = tlf;
	}

	/**
	 * Returnerer udlejningsdatoen for produktet
	 * 
	 * @return
	 */
	public LocalDate getUdlejningsDato() {
		return udlejningsDato;
	}

	/**
	 * Sætter udlejningsdatoen for produktet
	 * 
	 * @param udlejningsDato
	 */
	public void setUdlejningsDato(LocalDate udlejningsDato) {
		this.udlejningsDato = udlejningsDato;
	}

	/**
	 * Returnerer afleveringsdatoen for produktet
	 * 
	 * @return
	 */
	public LocalDate getAfleveringsDato() {
		return afleveringsDato;
	}

	/**
	 * Sætter afleveringsdatoen for produktet
	 * 
	 * @param afleveringsDato
	 */
	public void setAfleveringsDato(LocalDate afleveringsDato) {
		this.afleveringsDato = afleveringsDato;
	}

	/**
	 * Returnerer betalingsformen for udlejningen
	 * 
	 * @return
	 */
	public Betalingsform betalingsform() {
		return betalingsform;
	}

	/**
	 * Sætter hvilken betalingsform der blev brugt til at afregne
	 * 
	 * @param betalingsform
	 */
	public void setBetalingsform(Betalingsform betalingsform) {
		this.betalingsform = betalingsform;
	}

	@Override
	public String toString() {
		return navn + " " + udlejningsDato + " til " + afleveringsDato;
	}

}
