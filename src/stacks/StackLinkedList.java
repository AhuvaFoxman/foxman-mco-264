package stacks;

import java.util.LinkedList;

public class StackLinkedList<T> {
	
	private LinkedList<T> values;
	
	public StackLinkedList(){
		values = new LinkedList<T>();
	}
	
	public void push(T value){
		values.addFirst(value);
	}
	
	public void pop(){
		values.removeFirst();
	}
	
	public T peek(){
		T value = values.getFirst();
		return value;
	}
	
	public boolean isEmpty(){
		return values.isEmpty();
			
		}
	}


