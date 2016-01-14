package billOrganizerApp;

import java.io.Serializable;
import java.util.Comparator;

import doubleLinkedList.ListEmptyException;
import randomAccessExceptions.NotFoundException;
import randomAccessStudentDataLLIndex.LLExtIterator;

public class PriorityQueue<T extends Serializable & Comparable<T>> {

	private SortedLinkedList<T> sortedLL;
	private Comparator<T> comparator;

	public PriorityQueue(Comparator<T> comparator) {
		this.sortedLL = new SortedLinkedList<T>();
		this.comparator = comparator;
	}

	public void enqueue(T data) {
		sortedLL.insert(data, comparator);
	}

	public T dequeue() throws NotFoundException, ListEmptyException {
		if (!sortedLL.isEmpty()) {
			T data = sortedLL.getHead();
			sortedLL.remove(data);
			return data;
		}

		throw new ListEmptyException();

	}

	public T peek() throws ListEmptyException {
		if (sortedLL.isEmpty()) {
			throw new ListEmptyException();
		}
		return sortedLL.getHead(); // returns but does not remove the first
									// element
		// in a linked list
	}

	public void remove(T data) throws NotFoundException {

		sortedLL.remove(data);
	}

	public LLExtIterator<T> iterator() {
		return sortedLL.iterator();
	}

}
