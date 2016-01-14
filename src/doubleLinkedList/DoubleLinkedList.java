package doubleLinkedList;

import java.io.Serializable;
import java.util.Iterator;

import randomAccessExceptions.NotFoundException;
import randomAccessStudentDataLLIndex.Node;

public class DoubleLinkedList<T extends Comparable<T>> implements Serializable {

	private DoubleLinkNode<T> head;
	private DoubleLinkNode<T> tail;

	public DoubleLinkedList() {
		this.head = null;
		this.tail = head;
	}

	public void add(T data) {
		DoubleLinkNode<T> currentNode;
		DoubleLinkNode<T> prevNode;
		if (head == null) {
			head = new DoubleLinkNode<T>(data);
			tail = new DoubleLinkNode<T>(data);

		} else {
			DoubleLinkNode<T> newNode = new DoubleLinkNode<T>(data); // allocate
			// a new
			// Node
			// find the right spot for the Node inside the list
			currentNode = prevNode = head;

			// while there is still more data and use the compareTo method
			while (currentNode != null
					&& data.compareTo(currentNode.getData()) > 0) {
				// make the previous code the current node
				prevNode = currentNode;
				// the currentNode moves along
				currentNode = currentNode.getNext(); // move along to next Node

			}
			// found the right place , attach the links
			if (currentNode == head) {
				newNode.setNext(head);
				head.setPrev(newNode);
				head = newNode;

				while (currentNode.getNext() != null) {

					currentNode = currentNode.getNext();
					tail = currentNode;
				}

			} else { // goes in between other node or at the end of the list
				prevNode.setNext(newNode);
				newNode.setNext(currentNode);
				newNode.setPrev(prevNode);

				if (currentNode != null) {
					currentNode.setPrev(newNode);
					while (currentNode.getNext() != null) {
						currentNode = currentNode.getNext();
						tail = currentNode;

					}

				} else {
					tail = newNode;
				}

			}

		}

	}

	public void remove(T data) throws NotFoundException, ListEmptyException {

		if (head == null) {
			throw new ListEmptyException();
		}

		if (tail.getData().compareTo(data) == 0
				&& head.getData().compareTo(data) == 0) {
			tail = head = null;
			return;
		}

		if (tail.getData().compareTo(data) == 0) {
			tail = tail.getPrev();
			tail.setNext(null);
			return;
		}

		if (head.getData().compareTo(data) == 0) {
			head = head.getNext();
			head.setPrev(null);
			return;
		}

		DoubleLinkNode<T> currentNode = head;
		DoubleLinkNode<T> prevNode = head;

		while (currentNode != null) {

			if (currentNode.getData().equals(data)) {
				// this is the Node that must be removed

				prevNode.setNext(currentNode.getNext());
				currentNode.getNext().setPrev(prevNode);
				return;

			} else {
				// move further along in the list
				prevNode = currentNode;
				currentNode = currentNode.getNext();
			}
		}
		throw new NotFoundException();
	}

	public T find(T data) throws NotFoundException {
		DoubleLinkNode<T> currentNode = head; // start to iterate through the
												// list
		while (currentNode != null) {
			if (currentNode.getData().equals(data)) {
				return currentNode.getData();
			}
			currentNode = currentNode.getNext(); // move further down the list
		}
		throw new NotFoundException();
	}

	public boolean isEmpty() {
		return (head == null);
	}

	public void removeAll() {
		this.head = null;
	}

	public Iterator<T> iterator() {
		ReverseIterator<T> revIter = new ReverseIterator<T>(tail);
		return revIter;
	}

	public static void main(String[] args) throws NotFoundException,
			ListEmptyException {

		DoubleLinkedList<Integer> linkedList = new DoubleLinkedList<Integer>();

		linkedList.add(10);
		linkedList.add(5);
		linkedList.add(20);
		linkedList.add(25);
		linkedList.add(15);
		linkedList.add(30);
		linkedList.add(2);
		linkedList.add(17);
		linkedList.add(4);

		System.out.println("Print out all added data in reverse sorted order:");
		Iterator<Integer> theIter = linkedList.iterator();
		while (theIter.hasNext()) {
			System.out.println(theIter.next());
		}

		try {
			linkedList.remove(4);
			linkedList.remove(17);
			linkedList.remove(2);
			linkedList.remove(15);
			linkedList.remove(25);
			linkedList.remove(20);
			linkedList.remove(10);
			linkedList.remove(30);
			linkedList.remove(5);

		} catch (NotFoundException e1) {
			System.out.println("Not Found.");
		} catch (ListEmptyException e2) {
			System.out.println("List is Empty.");
		}

		System.out.println("\nPrint out the list again to show everything was removed");
		theIter = linkedList.iterator();
		while (theIter.hasNext()) {
			System.out.println(theIter.next());
		}

		linkedList.add(2);
		linkedList.add(30);
		linkedList.add(15);
		linkedList.add(1);
		
		System.out.println("\nShowing that the find method works:");
		System.out.println("Find number 2: " + linkedList.find(2));
		
		System.out.println("\nShow that the find method throws an excpetion if it doesnt find it: ");
		try{
		System.out.println("Find number 5 that is not in the list: " + linkedList.find(5));
		}catch(NotFoundException e1){
			System.out.println(e1.getMessage());
		}
		
		
		System.out.println("\nAdding more data for testing purposes....");
		theIter = linkedList.iterator();
		while (theIter.hasNext()) {
			System.out.println(theIter.next());
		}
		
		System.out.println("\nShow that the remove method throws an exception if try to remove a number that is not there:");
		try {
			linkedList.remove(100);
		}catch(NotFoundException e){
			System.out.println(e.getMessage());
		}

	}

}
