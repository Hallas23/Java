package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PN extends Ordination {

	private double antalEnheder;
	private List<LocalDate> huskedeDatoer = new ArrayList<>();

	public PN(LocalDate startDen, LocalDate slutDen, double antalEnheder) {
		super(startDen, slutDen);
		this.antalEnheder = antalEnheder;
	}

	/**
	 * Registrerer at der er givet en dosis paa dagen givesDen Returnerer true hvis
	 * givesDen er inden for ordinationens gyldighedsperiode og datoen huskes
	 * Retrurner false ellers og datoen givesDen ignoreres
	 * 
	 * @param givesDen
	 * @return
	 */
	public boolean givDosis(LocalDate givesDen) {
		if (givesDen.isAfter(getStartDen()) && givesDen.isBefore(getSlutDen()) || givesDen.equals(getStartDen()) || givesDen.equals(getSlutDen())) {
			huskedeDatoer.add(givesDen);
			return true;
		}else 
			return false;
	}

	public double doegnDosis() {
		if (huskedeDatoer.size() == 0 || antalEnheder <= 0)
			return 0;
		Collections.sort(huskedeDatoer);
		double antaldage = ChronoUnit.DAYS.between(huskedeDatoer.get(0), huskedeDatoer.get(huskedeDatoer.size() - 1))
				+ 1;
		return (getAntalGangeGivet() * antalEnheder) / antaldage;
	}

	public double samletDosis() {
		if (huskedeDatoer.size() == 0 || antalEnheder <= 0)
			return 0;
		double antaldage = ChronoUnit.DAYS.between(huskedeDatoer.get(0), huskedeDatoer.get(huskedeDatoer.size() - 1))
				+ 1;
		return doegnDosis() * antaldage;
	}

	/**
	 * Returnerer antal gange ordinationen er anvendt
	 * 
	 * @return
	 */
	public int getAntalGangeGivet() {
		return huskedeDatoer.size();
	}

	public double getAntalEnheder() {
		return antalEnheder;
	}

	@Override
	public String getType() {
		return "PN";
	}

	public List<LocalDate> getHuskedeDatoer() {
		return new ArrayList<>(huskedeDatoer);
	}

	public void removeDato(LocalDate dato) {
		if (huskedeDatoer.contains(dato)) {
			huskedeDatoer.remove(dato);
		}
	}

}
