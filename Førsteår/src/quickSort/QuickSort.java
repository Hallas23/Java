
package quickSort;

import java.util.Arrays;


//The Quick Sort is normally the fastest sorting algorithm

public class QuickSort {

	public static void main(String[] args) {

		Comparable[] arr = { 12, 11, 13, 5, 6, 7 };

		System.out.println("Given Array");
		printArray(arr);

		QuickSort ob = new QuickSort();
		ob.quickSort(arr, 0, arr.length - 1);

		System.out.println("\nSorted array");
		printArray(arr);

	}

	public void quickSort(Comparable[] data, int min, int max) {
		int pivot;

		if (min < max) {
			pivot = partition(data, min, max); // make partitions
			quickSort(data, min, pivot - 1);// sort left partition
			quickSort(data, pivot + 1, max);// sort right partition
		}

	}

	// -----------------------------------------------------------------
//  Creates the partitions needed for quick sort.
//-----------------------------------------------------------------
	private static int partition(Comparable[] data, int min, int max) {

		// Use first element as the partition value

		Comparable partitionValue = data[min];

		int left = min;
		int right = max;

		while (left < right) {
			// Search for an element that is > the partition element
			while (data[left].compareTo(partitionValue) <= 0 && left < right)
				left++;
			// Search for an element that is < the partitionelement
			while (data[right].compareTo(partitionValue) > 0)
				right--;
			if (left < right)
				swap(data, left, right);
		}
		// Move the partition element to its final position
		swap(data, min, right);

		return right;
	}

	private static void swap(Comparable[] data, int index1, int index2) {
		Comparable temp = data[index1];
		data[index1] = data[index2];
		data[index2] = temp;
	}

	/* A utility function to print array of size n */
	static void printArray(Comparable[] arr) {
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
}
