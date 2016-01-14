package billOrganizerApp;

import java.io.Serializable;
import java.util.Comparator;

import linkedLists.DuplicateDataException;
import randomAccessExceptions.NotFoundException;
import randomAccessStudentDataLLIndex.LinkedList;
import randomAccessStudentDataLLIndex.Node;

public class SortedLinkedList<T extends Serializable & Comparable<T>> extends
		LinkedList<T> {



	public void insert(T data) throws DuplicateDataException{
		Node<T> currentNode = null;
		Node<T> prevNode;
		if (head == null) { // the first Node in the list points to the student
							// index record
			head = new Node<T>(data);
		} else {
			
			if (super.contains(data)) {
				throw new DuplicateDataException();
			}
			
			Node<T> newNode = new Node<T>(data); // allocate a new Node
			// find the right spot for the Node inside the list
			// if the head or tail is equal to the data then throw
			// duplicateDataException

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
				// the new node will become the new head and point to the
				// current head
				newNode.setNext(head);
				head = newNode;
			} else { // goes in between other node or at the end of the list
				prevNode.setNext(newNode);
				newNode.setNext(currentNode);
			}

		}
	}

	public void insert(T data, Comparator<T> comparator) {
		Node<T> currentNode;
		Node<T> prevNode;
		if (head == null) { // the first Node in the list points to the student
							// index record
			head = new Node<T>(data);

		}

		else {
			Node<T> newNode = new Node<T>(data); // allocate a new Node
			// find the right spot for the Node inside the list
			// if the head or tail is equal to the data then throw
			// duplicateDataException

			currentNode = prevNode = head;

			// while there is still more data and use the compareTo method
			while (currentNode != null
					&& comparator.compare(data, currentNode.getData()) > 0) {

				// make the previous code the current node
				prevNode = currentNode;
				// the currentNode moves along
				currentNode = currentNode.getNext(); // move along to next Node
			}
			// found the right place , attach the links
			if (currentNode == head) {
				// the new node will become the new head and point to the
				// current head
				newNode.setNext(head);
				head = newNode;
			} else { // goes in between other node or at the end of the list
				prevNode.setNext(newNode);
				newNode.setNext(currentNode);
			}

		}
	}

	public T find(T data) throws NotFoundException {

		Node<T> currentNode = head; // start to iterate through the list
		while (currentNode != null) {
			if (currentNode.getData().equals(data)) {
				return currentNode.getData();
			}
			currentNode = currentNode.getNext(); // move further down the list
		}
		throw new NotFoundException();
	}

	
	
	
}
