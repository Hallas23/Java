package opgave;

public class UdvidelsesPakke extends TimePakke {

	private double plusPris;
	
	public UdvidelsesPakke(double mndPris, double minutPris, int frieTimer, double plusPris) {
		super(mndPris, minutPris, frieTimer);
		this.plusPris = plusPris;
	}
	
	@Override
	public double abonnementsPris() {
			return super.abonnementsPris() + plusPris;
	}
}
