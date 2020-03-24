package application.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Modellerer en rundvisning
 */

public class Rundvisning {

	private String beskrivelse;
	private LocalDateTime startTid;
	private LocalDateTime slutTid;
	private double prisPrPerson;
	private int kontaktNr;
	private Betalingsform betalingsform;
	private int antalPersoner;
	private boolean studieRabat;

	/**
	 * Opretter en ny rundvisning Pre: startTid er før slutTid Pre: antalPersoner >
	 * 0 Pre: prisPrPerson > 0
	 * 
	 * @param startTid
	 * @param slutTid
	 * @param prisPrPerson
	 * @param kontaktNr
	 * @param betalingsform
	 * @param antalPersoner
	 * @param studieRabat
	 */
	public Rundvisning(String beskrivelse, LocalDateTime startTid, LocalDateTime slutTid, double prisPrPerson,
			int kontaktNr, Betalingsform betalingsform, int antalPersoner, boolean studieRabat) {
		this.beskrivelse = beskrivelse;
		this.startTid = startTid;
		this.slutTid = slutTid;
		this.prisPrPerson = prisPrPerson;
		this.kontaktNr = kontaktNr;
		this.betalingsform = betalingsform;
		this.antalPersoner = antalPersoner;
		this.setStudieRabat(studieRabat);
	}

	/**
	 * Returnerer starttidspunktet for rundvisningen
	 * 
	 * @return startTid
	 */
	public LocalDateTime getStartTid() {
		return startTid;
	}

	/**
	 * Sætter starttidspunktet for rundvisningen
	 * 
	 * @param startTid
	 */
	public void setStartTid(LocalDateTime startTid) {
		this.startTid = startTid;
	}

	/**
	 * Returnerer sluttidspunktet for rundvisningen
	 * 
	 * @return slutTid
	 */
	public LocalDateTime getSlutTid() {
		return slutTid;
	}

	/**
	 * Sætter sluttidspunktet for rundvisningen
	 * 
	 * @param slutTid
	 */
	public void setSlutTid(LocalDateTime slutTid) {
		this.slutTid = slutTid;
	}

	/**
	 * Returnerer prisen pr person
	 * 
	 * @return prisPrPerson
	 */
	public double getPrisPrPerson() {
		return prisPrPerson;
	}

	/**
	 * Sætter prisen pr person
	 * 
	 * @param prisPrPerson
	 */
	public void setPrisPrPerson(double prisPrPerson) {
		this.prisPrPerson = prisPrPerson;
	}

	/**
	 * Returnerer nummeret for kontaktpersonen tilknyttet rundvisningen
	 * 
	 * @return kontaktNr
	 */
	public int getKontaktNr() {
		return kontaktNr;
	}

	/**
	 * Sætter kontaktnummeret
	 * 
	 * @param kontaktNr
	 */
	public void setKontaktNr(int kontaktNr) {
		this.kontaktNr = kontaktNr;
	}

	/**
	 * Returnerer betalingsformen som bruges til at betale rundvisningen
	 * 
	 * @return betalingsform
	 */
	public Betalingsform getBetalingsform() {
		return betalingsform;
	}

	/**
	 * Sætter betalingsformen for rundvisningen
	 * 
	 * @param betalingsform
	 */
	public void setBetalingsform(Betalingsform betalingsform) {
		this.betalingsform = betalingsform;
	}

	/**
	 * Returnerer antallet af personer som er tilknyttet rundvisningen
	 * 
	 * @return
	 */
	public int getAntalPersoner() {
		return antalPersoner;
	}
	
	/**
	 * Returnerer beksrivelse af rundvisningen
	 * 
	 * @return
	 */
	
	public String getBeskrivelse() {
		return beskrivelse;
	}
	
	/**
	 * Sætter en rundvisnings beskrivelse
	 * 
	 * @param beskrivelse
	 */

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

	/**
	 * Sætter antallet af personer som er tilknyttet rundvisningen
	 * 
	 * @param antalPersoner
	 */
	public void setAntalPersoner(int antalPersoner) {
		this.antalPersoner = antalPersoner;
	}

	/**
	 * Returnerer om rundvisningen betales med studierabat
	 * 
	 * @return studieRabat
	 */
	public boolean isStudieRabat() {
		return studieRabat;
	}

	/**
	 * Sætter studierabatten for rundvisningen
	 * 
	 * @param studieRabat
	 */
	public void setStudieRabat(boolean studieRabat) {
		this.studieRabat = studieRabat;
	}

	/**
	 * Returnerer den samlede pris for rundvisningen
	 * 
	 * @return samlet pris
	 */
	public double getSamletPris() {
		double result = 0;
		if (studieRabat == true) {
			result = (prisPrPerson * antalPersoner) * 0.15;
		} else {
			result = prisPrPerson * antalPersoner;
		}
		if (startTid.isAfter(LocalDateTime.of(LocalDate.now(), LocalTime.of(18, 0)))) {
			result = result * 1.15;
		}
		return result;
	}

	@Override
	public String toString() {
		String result = null;
		if (this.startTid.isBefore(LocalDateTime.now()) && this.betalingsform == null) {
			result = "(IKKE BETALT) Tlf: " + kontaktNr + " Personer: " + antalPersoner;
		} else {
			result = startTid.toLocalDate() + " fra " + startTid.toLocalTime() + " til " + slutTid.toLocalTime() + "( "
					+ antalPersoner + " personer)";
		}
		return result;
	}

}
