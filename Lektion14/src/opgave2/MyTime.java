package opgave2;

import java.util.ArrayList;
import java.util.List;

public class MyTime implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Integer> times = new ArrayList<Integer>();
	
	private int time = 0;
	
	public MyTime() {
		
	}

	public int getTime() {
		return time;
	}

	public void resetTime() {
		time = 0;
	}
	
	public void increaseTime() {
		time++;
	}
	
	public void saveTime() {
		times.add(this.getTime());
	}
	
	
	
}