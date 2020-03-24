package ordination;

import java.time.LocalDate;
import java.time.LocalTime;

public class DagligFast extends Ordination {
	
	private Dosis[] doser = new Dosis[4];

	public DagligFast(LocalDate startDen, LocalDate slutDen) {
		super(startDen, slutDen);
	}

	@Override
	public double samletDosis() {
		return doegnDosis() * antalDage();
	}

	@Override
	public double doegnDosis() {
		double dosis = 0;
		
		for (int i = 0; i < doser.length; i ++) {
			
			if (doser[i] != null) 
				
			dosis += doser[i].getAntal();
		}
		return dosis;
	}

	@Override
	public String getType() {
		return "Daglig fast";
	}
	
	public Dosis[] getDoser() {
		return doser;
	}

	public Dosis opretDosis(LocalTime tid, double antal) {
		Dosis dosis = new Dosis(tid, antal);
		
		if  (antal < 0) {
			throw new IllegalArgumentException("Antal skal være positivt");
		}
		
		if  (tid == LocalTime.of(8, 00) && antal == 0.1) {
			dosis = null;
			doser[0] = dosis;
		}
		
		if  (tid == LocalTime.of(12, 00) && antal == 0.1) {
			dosis = null;
			doser[1] = dosis;
		}
		
		if  (tid == LocalTime.of(18, 00) && antal == 0.1) {
			dosis = null;
			doser[2] = dosis;
		}
		
		if  (tid == LocalTime.of(00, 00) && antal == 0.1) {
			dosis = null;
			doser[3] = dosis;
		}
		
		if (tid == LocalTime.of(8, 00)) {
			
			doser[0] = dosis;
		}
		
		
		if (tid == LocalTime.of(12, 00)) {
			doser[1] = dosis;
		}
		
		if (tid == LocalTime.of(18, 00)) {
			doser[2] = dosis;
		}
	
		if (tid == LocalTime.of(00, 00)) {
			doser[3] = dosis;
		}
		
		return dosis;
	}
	
}
