package stringBag;

import java.util.Random;

public class ArrayStringBag {

	private String name;
	private String[] bag;
	private int lastIndex = 0;

	// constructor has a maxSize
	public ArrayStringBag(String name, int maxSize) throws InvalidDataException {
		if (name == null || maxSize < 0) {
			throw new InvalidDataException();
		}

		this.name = name;
		this.bag = new String[maxSize];
	}

	// constructor with no maxSize. Assume that the most elements will be 100
	public ArrayStringBag(String name) throws InvalidDataException {
		if (name == null) {
			throw new InvalidDataException();
		}
		this.bag = new String[100];
	}

	public void insert(String string) {
		if (isFull()) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			bag[lastIndex] = string;
			lastIndex++;
		}
	}

	public boolean isFull() {
		if (lastIndex == (bag.length)) {
			return true;
		} else {
			return false;
		}

	}

	public int size() {
		return (lastIndex);
	}

	public String remove() throws NotFoundException {

		if (isEmpty()) {
			throw new NotFoundException();
		} else {
			Random random = new Random();
			int number = random.nextInt(size());
			String removeString = bag[number];
			int i;
			for (i = number; i < lastIndex; i++) {

				bag[i] = bag[i + 1];

			}
			lastIndex--;
			if (removeString == null) {
				throw new NotFoundException();
			} else {
				return removeString;
			}
		}

	}

	public boolean isEmpty() {
		// if the first element is null than the Array is empty
		if (bag[0] == null) {
			return true;
		} else {
			return false;
		}
	}

	public void clear() {
		// makes the array empty
		for (int i = 0; i < lastIndex; i++) {
			bag[i] = null;
			lastIndex = 0;
		}
	}

	public String getName() {
		return this.name;
	}

	public String toString() {
		StringBuffer bagString = new StringBuffer();
		bagString.append("Bag: " + this.name + "\n");
		for (int i = 0; i < lastIndex; i++) {
			bagString.append(i + 1 + ". " + bag[i] + "\n");

		}

		return bagString.toString();
	}

}
