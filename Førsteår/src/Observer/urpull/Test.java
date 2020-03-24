package Observer.urpull;

public class Test {

	public static void main(String[] args) {
		UrSubject ur = new UrSubject();

		Observer analog = new AnalogObserver();
		ur.registerObserver(analog);

		Observer digital = new DigitalObserver();
		ur.registerObserver(digital);

		for (int i = 0; i < 4; i++) {
			ur.tiktak();
			System.out.println();
			if (ur.getTid() == 2)
				ur.removeObserver(digital);
		}
	}
}
