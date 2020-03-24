package hygge;

import java.util.ArrayList;

public class Hygge {

	public static void main(String[] args) {
		int[] a = { 5, 3, 6, 7, 9 };
		System.out.println(avoidObstacles(a));
	}

	public static int avoidObstacles(int[] inputArray) {
		int n = 1;
		boolean found = true;
		for (int i = 0; i < inputArray.length; i++) {
			found = true;	
			if (inputArray[i] % n == 0 ) {
				i = -1;
				n++;
				continue;
			}
		
			if (found && inputArray.length+1 == i) {
				return n;
			}
		}
		return n;
	}
}
