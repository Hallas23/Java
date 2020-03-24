package mergeSort;

class MergeSort {
	// Merges two subarrays of arr[].
	// First subarray is arr[l..m]
	// Second subarray is arr[m+1..r]
	public void merge(Comparable data[], int first, int mid, int last) {
		// Find sizes of two subarrays to be merged
		int first1 = first;
		int first2 = mid + 1; // endpoints of first subarray
		int last1 = mid;
		int last2 = last; // endpoints of second subarray
		int index = first1; // next index open in temp array

		Comparable[] temp = new Comparable[data.length];

		// Copy smaller item from each subarray into temp until one
		// of the subarrays is exhausted

		while (first1 <= last1 && first2 <= last2) {
			if (data[first1].compareTo(data[first2]) < 0) {
				
				temp[index] = data[first1];
				first1++;
			} else {
				temp[index] = data[first2];
				first2++;
			}
			index++;
		}
		// Copy remaining elements from first subarray, if any
		while (first1 <= last1) {
			temp[index] = data[first1];
			first1++;
			index++;
		}
		// Copy remaining elements from second subarray, if any
		while (first2 <= last2) {
			temp[index] = data[first2];
			first2++;
			index++;
		}
		// Copy merged data into original array
		for (index = first; index <= last; index++)
			data[index] = temp[index];
	}

	// Main function that sorts arr[l..r] using
	// merge()
	public void sort(Comparable data[], int min, int max) {
		
		if (min < max) {
			// Find the middle point
			int m = (min + max) / 2;

			// Sort first and second halves
			sort(data, min, m);
			sort(data, m + 1, max);

			// Merge the sorted halves
			merge(data, min, m, max);
		}
	}

	/* A utility function to print array of size n */
	static void printArray(Comparable[] arr) {
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	// Driver method
	public static void main(String args[]) {
		Comparable[] arr = { 12, 11, 13, 5, 6, 7 };

		System.out.println("Given Array");
		printArray(arr);

		MergeSort ob = new MergeSort();
		ob.sort(arr, 0, arr.length - 1);

		System.out.println("\nSorted array");
		printArray(arr);
	}
}