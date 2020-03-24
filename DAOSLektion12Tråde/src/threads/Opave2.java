package threads;

public class Opave2 extends Thread {
	int tal;

	/**
	 *
	 * TaellerThread() constructor
	 */
	public Opave2(int n) {
		tal = n;
	}

	/**
	 *
	 * run() er trådensprimæremetode.
	 */
	public void run() {
		System.out.println("factorial ----------------" );
		factorial(tal);
		System.out.println("sum ------------------");
		System.out.println(sum(tal));
	
		System.out.println("sumrec ------------------");
		sumrec(tal);
	}

	private int factorial(int tal) {
		int result = 0;
		int n = tal;

		if (n == 0 || n == 1)
			return 1;

		System.out.println(result = factorial(n - 1) * n);

		return result;
	}

	private int sum(int tal) {
		
		return ((tal * (tal + 1)) / 2);
	}
	
	private int sumrec(int tal) {
	int result = 0;
	
	if (tal == 1) 
		return 1;
	
		System.out.println(result = sumrec(tal - 1) + tal);
		
		return result;
	}

}