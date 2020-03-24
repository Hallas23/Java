package opgave4.ex4student;

public class Rainfall {
	
	
	public static void main(String[] args) {
		int[] weekly = {
				20, 10, 12, 12, 13, 14, 15, 10, 8, 7, 13, 15, 10,
				9, 6, 8, 12, 22, 14, 16, 16, 18, 23, 12, 0, 2,
				0, 0, 18, 0, 0, 0, 34, 12, 34, 23, 23, 12, 44,
				23, 12, 34, 22, 22, 22, 22, 18, 19, 21, 32, 24, 13 };
		
		System.out.println(minRainfall3(weekly));
		System.out.println(minRainfallN(2, weekly));
		System.out.println(sameRainfall(weekly));

	}

	/**
	 * Returns the week number of the week that starts 
	 * a period of 3 weeks with the least rainfall.
	 */
	public static int minRainfall3(int[] weekly) {
		int minugenr = 0;
		int min = Integer.MAX_VALUE;
		int temp = 0;
		
		for (int i = 0; i < weekly.length - 2; i++) {
			temp = weekly[i] + weekly[i + 1] + weekly[i + 2];
			
			if (temp < min) {
				min = temp;
				minugenr = i;
			}
			
		}
		return minugenr;
	}

	/**
	 * Returns the week number of the week that starts 
	 * a period of n weeks with the least rainfall.
	 * Requires: 0 < n < 52.
	 */
	public static int minRainfallN(int n, int[] weekly) {
		int index = 0;
		int tal = 0;
		
		
		for (int i = 0; i < n; i++) {
			tal = tal + weekly[i];
		}
		
		int min = tal;
		
		for (int i = 1; i < weekly.length - n; i++) {
			
			tal = tal + weekly[i - 1 + n] - weekly[i - 1];
			
			if (tal < min) {
				min = tal;
				index = i;
			}
			
		}
		return index;
	}

	/**
	 *  Returns the week number of the week that starts 
	 *  the longest period, where the rainfall has been exactly the same.
	 */
	public static int sameRainfall(int[] weekly) {
			int index = 0;
			int uger = 0;
			int max = Integer.MIN_VALUE;
			
			for (int i = 0; i < weekly.length - 1; i++) {
				
				if (weekly[i] == weekly[i+1]) {
					uger++;
				}
				else {
					uger = 0;
				}
				
				if (uger > max) {
					max = uger;
					index = i - max + 1;
				}
				
			}
			return index;
		}
	}

