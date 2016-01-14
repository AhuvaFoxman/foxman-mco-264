package stringBag;

public class TestStringBag {

	public static void main(String[] args) throws InvalidDataException {

		// instantiates a new ArrayStringBag with a max size
		ArrayStringBag bags = new ArrayStringBag("Words", 8);

		// insert elements into the array
		bags.insert("Hello!");
		bags.insert("How");
		bags.insert("are");
		bags.insert("you?");

		// show that the toString method works
		System.out.println("Show that the toString() method works");
		System.out.println(bags.toString());

		// show that the clear method works
		System.out.println("\nShow that the clear() method works");
		System.out.println("Clearing the bag...");
		bags.clear();

		System.out.println(bags.toString());

		// test if the isEmpty method works. The answer should be true
		System.out
				.println("Testing the isEmpty() method. The answer should be true.");
		System.out.println("Is the bag empty? " + bags.isEmpty());

		System.out
				.println("\nTesting the isFull() method. The answer should be false.");
		System.out.println("Is the bag full? " + bags.isFull());

		System.out.println("\nInserting 7 more values... ");
		// insert elements again to do more testing
		bags.insert("This");
		bags.insert("is");
		bags.insert("was");
		bags.insert("fun");
		bags.insert("program");
		bags.insert("to");
		bags.insert("make!");

		// test if the isEmpty method works. The answer should be false.
		System.out
				.println("\nTesting the isEmpty() method. The answer should be false.");
		System.out.println("Is the bag empty? " + bags.isEmpty());

		// test if the size method works. The size should be 4
		System.out.println("\nSize of the bag: " + bags.size());

		// test if the remove method works
		// If the number that is generated has a string in it return the string
		// Otherwise the NotFoundException should be thrown

		try {
			System.out.println("\nThe String that was removed was: "
					+ bags.remove());
		} catch (NotFoundException e) {
			System.out.println("Not found.");
		}

		// if the word was removed the array should not print it out the removed
		// word
		System.out.println(bags.toString());

		// try entering something after it was removed
		System.out.println("\nTry entering more data after was removed");
		bags.insert("Ahuva");
		bags.insert("Foxman");

		System.out.println(bags.toString());

		// test of the getName() method works
		System.out.println("\nName of the bag is: " + bags.getName());

	}

}
