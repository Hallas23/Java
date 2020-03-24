package opgave2;


import java.util.Random;

public class faelles {
	
	private int global = 0;
	public static boolean flag[] = new boolean[2];
	public static int turn;

	
	public void tagerRandomTid(int max) {
		int temp = 2;
		Random r = new Random();
		int nymax = Math.abs(r.nextInt())% (max +1);
		for(int i = 0; i < nymax; i++) {
			temp += nymax;
			temp *= i;
			for(int j = 0; j < nymax; j++) {
				temp += temp * 2;
				int nyt = temp * j + 4;
				temp = nyt * 2;
			}
		}
	}
	
	public void kritisksection() {
		int temp;
		temp = global;
		tagerRandomTid(temp);
		global = temp +1;
		
	}
	
	public int getGlobal() {
		return global;
	}
}