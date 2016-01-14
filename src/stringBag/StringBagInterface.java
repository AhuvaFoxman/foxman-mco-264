package stringBag;

public interface StringBagInterface {

	void insert(String element);
	//Precondition: The StringBag is not full
	//
	//Places the element into the StringBag
	
	boolean isFull();
	//Returns true if the StringBag is full. Otherwise it returns false
	
	int size();
	//returns the number of strings in this StringBag
	
	String remove();
	//Precondition: The StringBag is not empty
	//removes a random element from the bag, deletes it from the StringBag
	//returns the element that is removed
	
	boolean isEmpty();
	//returns true if the bag is empty. Otherwise returns false.
	
	void clear();
	//makes the StringBag empty
	
	String getName();
	//returns the name of the StringBag
	
	String toString();
	//Returns a nicely formatting String representing this StringBag

 }
