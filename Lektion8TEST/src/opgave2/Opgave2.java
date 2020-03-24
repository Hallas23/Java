package opgave2;

public class Opgave2 {

	public static void main(String[] args) {
		System.out.println(totalPayment(0, 3));
	}

	/*
	 * Returns the total payment for a family minus discount Pre: small >= 0, big >=
	 * 0 (small is the number of pre-school children and big is the number of
	 * children attending school)
	 */
	public static int totalPayment(int small, int big) {
		int result = 0;
		int smallpris = 1000 * small;
		int bigpris = 2000 * big;

		if (small + big == 1) {
			result += smallpris;
			result += bigpris;
		}

		if (small + big == 2 || small + big == 3) {
			if (big >= 1) {
				result += 2000;
				result += smallpris * 0.75;
				result += (bigpris - 2000) * 0.75;
			}

			if (big == 0) {
				result += 1000;
				result += (smallpris - 1000) * 0.75;
			}
		}

		if (small + big > 3) {
			if (big >= 1)
				result += 2000;
			if (big >= 2)
				result += 1500;
			if (big >= 3) {
				result += 1500;
				for (int j = 0; j < small; j++) {
					result += 500;
				}
			}
			if (big > 3) {
				for (int i = 3; i < big; i++) {
					result += 1000;
				}
			}
			if (big == 0) {
				result += 1000;
			}

			if (small >= 2 && big < 2)
				result += 750;
			
			if (small >= 2 && big == 2) {
				result += 750;
				for (int j = 1; j < small; j++) {
					result += 500;
				}
			}

			if (small >= 3 && big < 1) {
				result += 750;
				for (int j = 3; j < small; j++) {
					result += 500;
				}
			}

			if (small >= 3 && big == 1) {
				result += 750;
				for (int j = 2; j < small; j++) {
					result += 500;
				}
			}

		}
		return result;
	}
}
