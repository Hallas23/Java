package urpull;

public class DigitalObserver implements Observer {

	@Override
	public void update(Subject o) {
		int tid = ((UrSubject) o).getTid();
		System.out.println("Digital tid: " + tid);
	}
}
