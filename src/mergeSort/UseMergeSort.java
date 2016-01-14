package mergeSort;

public class UseMergeSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MergeSort sortArray = new MergeSort(15);
		System.out.println("Unsorted" + sortArray + "\n");

		sortArray.sort();
		System.out.println("Number of comparisons " + sortArray.getCompare());

		System.out.println("Sorted:" + sortArray);

	}

}
