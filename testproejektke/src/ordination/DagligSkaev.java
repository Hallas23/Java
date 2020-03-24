package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DagligSkaev extends Ordination {
	private final List<Dosis> doser = new ArrayList<>();

	public DagligSkaev(LocalDate startDen, LocalDate slutDen) {
		super(startDen, slutDen);
	}

	public ArrayList<Dosis> getDoser() {
		return new ArrayList<>(doser);
	}

	public Dosis opretDosis(LocalTime tid, double antal) {
		if (antal < 0) {
			throw new IllegalArgumentException("Antal skal være positivt");
		}

		Dosis dosis = new Dosis(tid, antal);
		doser.add(dosis);
		return dosis;
	}

	@Override
	public double samletDosis() {
		return doegnDosis() * antalDage();
	}

	@Override
	public double doegnDosis() {
		double result = 0;
		for (Dosis d : doser) {
			result += d.getAntal();
		}
		return result;
	}

	@Override
	public String getType() {
		return "Dagligskæv";
	}
}
