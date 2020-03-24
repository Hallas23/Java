package opgave1;

public class Opgave1 {

	public static void main(String[] args) {
		for (int n = 0; n < 7; n++) {
			System.out.printf("%d|", n);
			for (int m = 0; m <= n; m++) {
				System.out.printf("%4d", binomial(n,m));
			}
			System.out.println();
		}
	}
	
	public static int binomial(int n, int m) {
		if (m == 0 || m == n) {
			return 1;
		}
		
		else {
			return binomial(n-1, m) + binomial(n-1, m - 1);
		}
	}
}
