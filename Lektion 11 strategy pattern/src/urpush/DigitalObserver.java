package urpush;

public class DigitalObserver implements Observer {

	@Override
	public void update(int tid) {
		System.out.println("Digital tid: " + tid);
	}
}
