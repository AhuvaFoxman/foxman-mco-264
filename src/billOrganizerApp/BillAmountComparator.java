package billOrganizerApp;

import java.util.Comparator;

public class BillAmountComparator implements Comparator<Bill>{

	@Override
	public int compare(Bill one, Bill two) {
		return one.getAmount().compareTo(two.getAmount());
	}

}
