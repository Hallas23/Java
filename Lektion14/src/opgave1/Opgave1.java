package opgave1;

import java.util.ArrayList;
import java.util.List;

public class Opgave1 implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> times = new ArrayList<String>();
	
	private int time = 0;
	
	public Opgave1() {
		
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
		times.add("" + this.getTime());
	}
	
	
	
}
