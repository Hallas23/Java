package exercise2;

public class Counter {

	private int value;	
	private static Counter instance;
	
	public static Counter getInstance() {
		if (instance == null) {
			instance = new Counter();
		}
		return instance;
	}

	private Counter () {
		value = 0;
	}
	
	public void count () {
		value +=1;
	}
	
	public void times () {
		value *= 2;
	}
	
	public void zero () {
		value = 0;
	}
	
	public int getValue () {
		return value;
	}
}

