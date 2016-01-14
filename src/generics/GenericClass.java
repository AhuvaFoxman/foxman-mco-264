package generics;

public class GenericClass{

	//a generic method
	public <T extends Comparable <T>> T max (T valueOne, T valueTwo){
		
		//return null if both are the same, otherwise return the larger value
		int result = valueOne.compareTo(valueTwo);
		if(result > 0){
			return valueOne;
		}
		else if(result < 0){
			return valueTwo;
		}
		else{
			return null;
		}
	}
	
	public static void main(String[] args){
		String name1 = "Hello";
		String name2 = "goodbye";
		GenericClass gen = new GenericClass();
		System.out.println(gen.max(56.8,56.9));
		System.out.println(gen.max(name1, name2));
		
		Book firstBook = new Book("CuriosGeorge", 13.99);
		Book secondBook = new Book("Amelia Bedilia", 6.00);
		System.out.println(gen.max(firstBook, secondBook)); //it will complain because book was not implements comparable
		//because java does not know unless you do implements comparable
		
	}
}
