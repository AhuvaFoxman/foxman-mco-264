package billOrganizerApp;

import java.util.Comparator;

public class BillDateComparator implements Comparator<Bill>{

	@Override
	public int compare(Bill one, Bill two) {
		return one.getDueDate().compareTo(two.getDueDate());
	}

}
