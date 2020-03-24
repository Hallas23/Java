package opgave3;

public class Opgave3 {


	public static void main(String[] args) {
		System.out.println(CalcNo(100));
	}
	
	public static int CalcNo(int n) {
		if (n == 0) {
			return 2;
		}
		else if (n == 1) {
			return 1;
		}
		else if (n == 2) {
			return 5;
		}
		else if (n > 2 && n % 2 == 0) {
			return 2 * CalcNo(n-3) - CalcNo(n-1);
		}
		else {
			return CalcNo(n-1) + CalcNo(n-2) + 3 * CalcNo(n-3);
		}
	}
}
