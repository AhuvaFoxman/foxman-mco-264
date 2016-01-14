package doubleLinkedList;

import java.util.Iterator;

public class ReverseIterator<T extends Comparable<T>> implements Iterator<T> {

	private DoubleLinkNode<T> currentNode;
	private DoubleLinkNode<T> tail;
	public ReverseIterator(DoubleLinkNode<T> tail){
		this.tail = tail;
		this.currentNode = tail;
	}
	
	public boolean hasNext() {
		
		if(this.currentNode != null){
			return true;
		}
		else{
			return false;
		}
	}

	
	public T next() {
		T data = currentNode.getData();
		this.currentNode = currentNode.getPrev();
		return data;

	}
	
	public void reset(){
		this.currentNode = tail;
	}

}
