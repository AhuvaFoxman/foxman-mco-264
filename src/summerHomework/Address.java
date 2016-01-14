package summerHomework;

public class Address {

	private String street;
	private String city;
	private States state;
	private String zipCode;

	public Address(String street, String city, String state, String zip) {
		if (street == null || city == null || state == null || zip == null) {
			throw new InvalidDataException();
		}
		this.street = street;
		this.city = city;

		States st = getStateCode(state);
		if (st == null) {
			throw new NullPointerException();
		}
		this.state = st;

		// dont need to validate because only a valid zip will be entered into
		// the system
		this.zipCode = zip;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		if (street == null) {
			throw new NullPointerException();
		}
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		if (city == null) {
			throw new NullPointerException();
		}
		this.city = city;
	}

	public States getState() {
		return state;
	}

	public void setState(String state) {
		if (state == null) {
			throw new NullPointerException();
		}
		States st = getStateCode(state);
		if (st == null) {
			throw new NullPointerException();
		}
		this.state = st;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public States getStateCode(String state) {
		if (state.length() > 2) {
			for (States s : States.values()) {
				if (s.getStateName().equalsIgnoreCase(state.trim())) {
					return s;
				}
			}
		} else {
			for (States s : States.values()) {
				if (s.name().equalsIgnoreCase(state)) {
					return s;
				}
			}
		}
		return null;
	}

	public String toString() {
		StringBuffer b = new StringBuffer();
		b.append("Address: ");
		b.append(" Street: " + this.street);
		b.append(" City: " + this.city);
		b.append(" State: " + this.state);
		b.append(" Zipcode: " + this.zipCode);

		return b.toString();
	}

}
