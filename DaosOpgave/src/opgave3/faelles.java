package opgave3;


import java.util.Random;

public class faelles {
	
	private int global = 0;

	
	public synchronized void tagerRandomTid(int max) {
		int temp = 2;
		Random r = new Random();
		int nymax = Math.abs(r.nextInt())% (max +1);
		for(int i = 1; i < nymax; i++) {
			temp += nymax;
			temp *= i;
			for(int j = 1; j < nymax; j++) {
				temp += temp * 2;
				int nyt = temp * j + 4;
				temp = nyt * 2;
			}
		}
	}
	
	public synchronized void kritisksection() {
		int temp;
		temp = global;
		tagerRandomTid(temp);
		global = temp +1;
		
	}
	
	public int getGlobal() {
		return global;
	}
}