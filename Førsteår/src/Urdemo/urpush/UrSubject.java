package Urdemo.urpush;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class UrSubject implements Subject {
	private int tid;

	// link to --> 0..* Observer
	private Set<Observer> observers = new HashSet<>();

	public UrSubject() {
		tid = 0;
	}

	public int getTid() {
		return tid;
	}

	public void tiktak() {
		tid++;
		this.notifyObservers();
	}

	//-------------------------------------------------------------------------

	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	private void notifyObservers() {
		Iterator<Observer> itr = observers.iterator();
		while (itr.hasNext()) {
			Observer o = itr.next();
			o.update(tid);
		}
	}
}
