package summerHomework;

import java.util.Comparator;

public class ComparatorPersonID implements Comparator<Person> {

	@Override
	public int compare(Person one, Person two) {
		return (one.getID().compareTo(two.getID()));
	}

}
