package mergeSort;

import java.util.Random;

public class MergeSort {

	private int[] data; // array of values
	//private static Random generator = new Random();
	private int compare;

	public MergeSort(int size) {
		compare = 0;
		data = new int[] {7,4,6,2,3,1,5};
			
		
		/**
		for (int i = 0; i < size; i++) {
			data[i] = generator.nextInt(100);
		}
		**/
	}

	public void sort() {
		sortArray(0, data.length - 1);
	}

	int getCompare() {
		return compare;
	}

	private void sortArray(int low, int high) {
		if (high - low >= 1) {
			// still data left, not the base case
			int middle1 = (low + high) / 2; // middle of the array
			int middle2 = middle1 + 1; // next element over

			// output split step
			System.out.println("split:      " + subarray(low, high));
			System.out.println("            " + subarray(low, middle1));
			System.out.println("            " + subarray(middle2, high));

			// sort the two halves of the array, recursively
			sortArray(low, middle1);
			sortArray(middle2, high);

			// once both halves are sorted , they must be merged
			merge(low, middle1, middle2, high);

		}

	}

	private void merge(int left, int middle1, int middle2, int right) {

		int leftIndex = left; // index into left subarray
		int rightIndex = middle2; // index into right subarray
		int combinedIndex = left; // index into temporary working array, the
									// merge sort is not an inplace sort
		int[] combined = new int[data.length];

		// output two subarrays before merging
		System.out.println("merge:       " + subarray(left, middle1));
		System.out.println("             " + subarray(middle2, right));

		// merge arrays until reaching end of either
		while (leftIndex <= middle1 && rightIndex <= right) {
			// place smaller of the two current elements into result set of
			// values
			// and move to next space in the arrays
			if (data[leftIndex] <= data[rightIndex]) {
				combined[combinedIndex++] = data[leftIndex++];
				compare++;
			} else {
				combined[combinedIndex++] = data[rightIndex++];
				compare++;
			}

		} // end while
		if (leftIndex == middle2) {
			// exhausted all values in left array
			while (rightIndex <= right) {
				combined[combinedIndex++] = data[rightIndex++];
			}
		} else {
			// exhausted right array, just copy over all values in left array
			while (leftIndex <= middle1) {
				combined[combinedIndex++] = data[leftIndex++];
			}
		}

		// now values are sorted so copy them back into original array
		for (int i = left; i <= right; i++) {
			data[i] = combined[i];
		}

		// output merged array
		System.out.println("             " + subarray(left, right));
		System.out.println();

	}

	// method to output certain values in array
	public String subarray(int low, int high) {
		StringBuilder temporary = new StringBuilder();
		// out spaces for alignment
		for (int i = 0; i < low; i++) {
			temporary.append("   ");
		}

		// output elements left in array
		for (int i = low; i <= high; i++) {
			temporary.append(" " + data[i]);
		}

		return temporary.toString();

	}

	@Override
	public String toString() {
		return subarray(0, data.length - 1);

	}
}