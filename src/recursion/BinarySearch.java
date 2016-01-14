package recursion;

import java.util.Arrays;
import java.util.Random;

public class BinarySearch {
	
	private int[] numbers;
	
	public BinarySearch(int size){
		numbers = new int[size];
		Random gen = new Random();
		
		for(int i = 0; i < size; i++){
			numbers[i] = gen.nextInt(10);
		}
		
		Arrays.sort(numbers);
		
	}
	
	public String toString(){
		StringBuilder build = new StringBuilder();
		
		for(int num : numbers){
			build.append(num);
			build.append(" ");
			
		}
		
		return build.toString();
	}
	
	public int search(int value){
		return BinSearch(value, 0, numbers.length-1); //do - 1 because its the position
		
	}
	
	//doing it recursively
	private int BinSearch(int value, int start, int end){
	if(end < start){ //no list. This is an anchor. We know for sure we can return it
		return -1;
	}
	
	int mid = (start + end) / 2; ///this is the mid of the list
	if(value == numbers[mid]){
		return mid;
	}
	else if(numbers[mid] > value){
		return BinSearch(value,start, mid -1); //value might be in first half of the list
	}
	else{
		return BinSearch(value, mid+1, end);
	}
	
	}
	
	public static void main(String[] args){
		BinarySearch search = new BinarySearch(4);
		int index = search.search(5);
		System.out.println(index>0?index:"Not found");
	}

}
