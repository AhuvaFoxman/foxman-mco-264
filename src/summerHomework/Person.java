package summerHomework;

public class Person {

	private Integer ID;
	private String firstName;
	private String lastName;
	private Character midInitial;
	private Address address;
	private String phoneNumber;
	private Character gender;

	public Person(Integer ID, String first, String last, Address address,
			Character gender) {

		this(ID, first, last, address, null, gender);

	}

	// a constructor for a person who does not have an initial but has a phone
	public Person(Integer ID, String first, String last, Address address,
			String phone, Character gender) {
		this(ID, first, last, null, address, phone, gender);
	}

	// a constructor that accepts an initial and phone
	public Person(Integer ID, String first, String last, Character initial,
			Address address, String phone, Character gender) {

		if (ID == null || first == null || last == null || address == null
				|| gender == null) {
			throw new InvalidDataException();
		}
		this.ID = ID;

		this.firstName = first;

		this.lastName = last;

		this.midInitial = initial;

		this.address = address;

		// dont need to check because only allows valid number into the system
		this.phoneNumber = phone;

		if (Character.toUpperCase(gender) == 'F'
				|| Character.toUpperCase(gender) == 'M') {
			this.gender = gender;
		} else {
			throw new InvalidDataException();
		}
	}

	public Integer getID() {
		return ID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Character getMidInitial() {
		return midInitial;
	}

	public Address getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Character getGender() {
		return gender;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setPhoneNumber(String phone) {
		this.phoneNumber = phone;
	}

	public int compareTo(Person person) {
		return (this.ID.compareTo(person.ID));
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}

	public String toString() {
		StringBuffer b = new StringBuffer();
		b.append(ID + " " + firstName + " ");
		if (midInitial != null) {
			b.append(midInitial + " ");
		}
		b.append(lastName + "\n");
		b.append(address + "\n");
		if (phoneNumber != null) {
			b.append("Phone number: " + phoneNumber + "\n");
		}
		b.append("Gender: " + gender);

		return b.toString();
	}
}
