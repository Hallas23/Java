package urpush;

public class AnalogObserver implements Observer {

	@Override
	public void update(int tid) {
		System.out.println("Analog tid: " + tid);
	}
}
