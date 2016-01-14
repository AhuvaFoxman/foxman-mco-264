package recursion;

public class Sum {
	
	public Sum(){
		
	}
	
	public int sum(int[] numbers){
		//assume the array is fulled of capacity
		int total = 0;
		for(int num : numbers){
			total += num;
		}
	
	return total;
	}
	
	public int recurSum(int[] numbers){
		return recurSum(numbers,0);
		
		
	}
	
	public int recurSum(int[] numbers, int curr){
		int total = 0;
		
		if(numbers.length == 0){
			return 0;
		}
		if(curr == numbers.length-1){
			return numbers[curr];
		}
		else{
		
			return numbers[curr] + recurSum(numbers, curr + 1);
					
		}
		
	}
	public static void main(String[] args){
		int[] num = new int[]{1,2,3};
		Sum sum = new Sum();
		System.out.println(sum.sum(num));
		System.out.println(sum.recurSum(num, 0));
		
	}

}
