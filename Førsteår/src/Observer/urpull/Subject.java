package Observer.urpull;

public interface Subject {

	public void registerObserver(Observer o);

	public void removeObserver(Observer o);
}
