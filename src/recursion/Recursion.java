package recursion;

public class Recursion {

	
	public static void message(int num){
		for (int i = 0; i < num; i++){
			System.out.println("Recursion is fun!");
		}
	}
	
	public static void recurMessage(int num){
		//anchor case
		if(num == 0) return;
		
		System.out.println("Recursion is fun!"); //do it once
		recurMessage(num-1);
	}
	
	
	public static void main(String[] args){
		recurMessage(10);
	}
}
