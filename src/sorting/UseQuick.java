package sorting;

public class UseQuick {


	public static void main(String[] args) {
		QuickSort<Integer> s = new QuickSort<Integer>();
		Integer[] numbers = {4,7,6,2,5,3,1};

		s.sort(numbers, 0, 6);
		
		for(int i = 0; i < numbers.length; i++){
		System.out.println(numbers[i]);
		}
}
}
