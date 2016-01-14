package bag;

public class BagEmptyException extends Exception {
	
	public BagEmptyException(){
		super("Bag is empty.");
	}

}
