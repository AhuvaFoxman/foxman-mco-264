package randomAccessStudentDataLLIndex;

import java.io.Serializable;
import java.util.Iterator;

public class LLExtIterator<T extends Comparable<T>> implements Iterator<T>{
	
	private Node<T> currentNode;
	private Node<T> head;
	
	public LLExtIterator(Node<T> head){
		this.head = head;
		this.currentNode = head;
		
	}
	
	public boolean hasNext() {
		if(currentNode != null){
			return true;
		}
		else{
			return false;
		}
	}

	
	public T next() {
		T data = currentNode.getData();
		this.currentNode = currentNode.getNext();
		return data;
	
	}
	
	public void reset(){
		this.currentNode = head;
	}
	

}
