package opgave3;

import java.util.Arrays;	


public class Opgave3 {

	public static void main(String[] args) {
		int[] list = {5, 10, 5, 6, 4, 9, 8};
		System.out.println(Arrays.toString(prefixAverage(list)));
	}
	
	public static double[] prefixAverage(int[] list) {
		
		double tal = 0;
		
		double[] output = new double[list.length];
		
		for (int i = 0; i < list.length; i++) {
			
			tal = tal + list[i];
			double average = tal / (i + 1);
			output[i] = average;
			
		}
		return output;
	}
}
