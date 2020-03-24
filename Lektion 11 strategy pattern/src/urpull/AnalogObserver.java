package urpull;

public class AnalogObserver implements Observer {

	@Override
	public void update(Subject o) {
		int tid = ((UrSubject) o).getTid();
		System.out.println("Analog tid: " + tid);
	}
}
