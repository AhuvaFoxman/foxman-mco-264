package summerHomework;

import java.util.Comparator;

public class ComparatorPersonName implements Comparator<Person> {

	@Override
	public int compare(Person one, Person two) {
		// compare based on last name
		// if they are equal then compare based on first name
		if (one.getLastName().equalsIgnoreCase(two.getLastName())) {
			return one.getFirstName().compareTo(two.getFirstName());
		}
		return (one.getLastName().compareTo(two.getLastName()));
	}

}
