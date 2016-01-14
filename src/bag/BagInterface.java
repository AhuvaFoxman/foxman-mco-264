package bag;

public interface BagInterface<T extends Comparable<T>> {

	public void dropInto(T item) throws BagFullException;
	
	public T takeOut() throws BagEmptyException;
	
	public T takeOut(T item) throws BagEmptyException;
	
	public boolean isEmpty();
	
	public boolean isFull();
}
