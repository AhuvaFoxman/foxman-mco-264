package shellSort;


public class UseShellSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i;
		int qty = 16;
		ShellSort<Integer> myShellSort = new ShellSort<Integer>();
		Integer[] numbers = {4,7,3,9,8,16,1,14,2,11,15,10,6,13,12,5};

			for (i=0;i< 16;i++)
			 System.out.println( numbers[i] +  "   ");
			myShellSort.sort(numbers,qty);	
			 System.out.println();
			 for (i=0;i< 16;i++)
			System.out.println(  numbers[i] +  "   ");

			}

}

