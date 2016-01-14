package bag;

import generics.Book;

import java.util.LinkedList;



import java.util.List;

import stringBag.InvalidDataException;

public class Bag<T extends Comparable<T>> implements BagInterface<T> {

	private LinkedList<T> bag;
	private int maxSize;
	

	// constructor has a maxSize
	public Bag(int maxSize) throws InvalidDataException {
		if (maxSize < 0) {
			throw new InvalidDataException();
		}

		this.bag = new LinkedList<T>();
		this.maxSize = maxSize;
		
	}

	@Override
	public void dropInto(T item) throws BagFullException {
		if(!isFull()){
		bag.add(item);
		}
		else{
			throw new BagFullException();
		}

	}

	@Override
	public T takeOut() throws BagEmptyException {
		if(!isEmpty()){
		return bag.remove();
		}
		else{
			throw new BagEmptyException();
		}
	}

	@Override
	public T takeOut(T item) throws BagEmptyException {
	if(!isEmpty()){	
		if(bag.contains(item)){
			bag.remove();
			return item;
		}
		else{
			return null;
		}
	}
	else{
		throw new BagEmptyException();
	}


	}

	@Override
	public boolean isEmpty() {
		return bag.isEmpty();
	}

	@Override
	public boolean isFull() {
		if(bag.size() == this.maxSize){
			return true;
		}
		else{
			return false;
		}
	}



public static void main (String[] args) throws InvalidDataException, BagFullException, BagEmptyException{
	Bag myBag = new Bag(2);
	
	Book book = new Book("Curios George", 15.99);
	Book book2 = new Book("Hello", 56.00);
	
	 myBag.dropInto(book);
	 myBag.dropInto(book2);
	 
	 
	 System.out.println(myBag.takeOut(book2.getTitle()));
	 System.out.println(myBag.takeOut());
	
}
}
