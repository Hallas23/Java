package application.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Modellerer et nyt salg
 * 
 * @author Sille, Simon og Michelle
 */

public class Salg implements Serializable, Comparable<Salg> {

	private LocalDate dato;
	private LocalTime tid;
	private String ID;
	private final ArrayList<Salgslinje> salgslinjer;
	private Betalingsform betalingsform;

	/**
	 * Initialiserer et salg og tildeler et unikt ID
	 */
	public Salg() {
		betalingsform = null;
		salgslinjer = new ArrayList<>();
		this.setID();
		this.setDato(LocalDate.now());
		this.setTid(LocalTime.now());
	}

	/**
	 * Returnerer salgets salgslinjer
	 * 
	 * @return salgslinjer
	 */
	public ArrayList<Salgslinje> getSalgslinjer() {
		return new ArrayList<>(salgslinjer);
	}

	/**
	 * Tilføjer en salgslinje til salget
	 * 
	 * @param salgslinje
	 */
	public void addSalgslinje(Salgslinje s) {
		salgslinjer.add(s);
	}

	/**
	 * Fjerner en salgslinje fra salget
	 * 
	 * @param salgslinje
	 */
	public void removeSalgslinje(Salgslinje s) {
		salgslinjer.remove(s);
	}

	/**
	 * Returnerer datoen for salget
	 * 
	 * @return dato
	 */
	public LocalDate getDato() {
		return dato;
	}

	/**
	 * Sætter datoen for salget
	 * 
	 * @param dato
	 */
	public void setDato(LocalDate dato) {
		this.dato = dato;
	}

	/**
	 * Returnerer tidspunktet for salget
	 * 
	 * @return tid
	 */
	public LocalTime getTid() {
		return tid;
	}

	/**
	 * Sætter tidspunktet for salget
	 * 
	 * @param tid
	 */
	public void setTid(LocalTime tid) {
		this.tid = tid;
	}

	/**
	 * Returnerer betalingsformen for salget
	 * 
	 * @return betalingsform
	 */
	public Betalingsform getBetalingsform() {
		return betalingsform;
	}

	/**
	 * Sætter betalingsformen for salget
	 * 
	 * @param betalingsform
	 */
	public void setBetalingsform(Betalingsform b) {
		this.betalingsform = b;
		for (Salgslinje s : getSalgslinjer()) {
			s.getProdukt().setAntalPåLager(s.getProdukt().getAntalPåLager() - s.getAntal());
		}
	}

	/**
	 * Returnerer salgets ID
	 * 
	 * @return ID
	 */
	public String getID() {
		return ID;
	}

	/**
	 * Tildeler et ID til salget
	 */
	public void setID() {
		this.ID = "";
		double d;
		for (int i = 1; i <= 16; i++) {
			d = Math.random() * 10;
			this.ID = this.ID + ((int) d);
			if (i % 4 == 0 && i != 16) {
				this.ID = this.ID + "-";
			}
		}
	}

	/**
	 * Udregner den samlede pris for salget
	 * 
	 * @return samlet pris
	 */
	public double udregnSamletPris() {
		double result = 0;
		for (Salgslinje s : salgslinjer) {
			result += s.getPris();
		}
		return result;
	}

	/**
	 * Udregner den samlede pantpris
	 * 
	 * @return pantpris
	 */
	public double udregnSamletPant() {
		double result = 0;
		for (Salgslinje s : salgslinjer) {
			if (s.getProdukt() instanceof PantProdukt) {
				result += ((PantProdukt) s.getProdukt()).getPantPris() * s.getAntal();
			}
		}
		return result;
	}

	/**
	 * Opretter en ny salgslinje med pris, antal og rabat
	 * 
	 * @param pris
	 * @param antal
	 * @param rabat
	 * @return salgslinje
	 */
	public Salgslinje createSalgslinje(Pris pris, int antal, Rabat rabat) {
		Salgslinje s = new Salgslinje(antal, rabat, pris);
		double prisCheck = s.getPris();
		if (prisCheck < 0) {
			throw new RuntimeException("Ugyldig rabat");
		}
		this.addSalgslinje(s);
		return s;
	}

	@Override
	public String toString() {
		return this.dato + " " + this.tid.getHour() + ":" + this.tid.getMinute() + " - (" + udregnSamletPris() + " kr)"
				+ " " + getID();
	}

	@Override
	public int compareTo(Salg other) {
		return this.getDato().compareTo(other.getDato());
	}

}
