package summerHomework;

public class Department {

	private String departmentID;
	private String departmentName;
	private String departmentLocation;
	private String dpmtPhonenumber;
	private String faxNumber;
	private Integer dpmtChair;

	public Department(String departmentID, String dpmtName) {
		this(departmentID, dpmtName, null, null, null, null);
	}

	public Department(String departmentID, String dpmtName, String location,
			String phone, String fax, Integer chair) {
		if (departmentID == null || dpmtName == null || location == null
				|| phone == null || fax == null || chair == null) {
			throw new InvalidDataException();
		}
		this.departmentID = departmentID;

		this.departmentName = dpmtName;

		this.departmentLocation = location;

		if (isPhoneValid(phone)) {
			this.dpmtPhonenumber = phone;
		} else {
			throw new InvalidDataException();
		}
		if (isPhoneValid(fax)) {
			this.faxNumber = fax;
		} else {
			throw new InvalidDataException();
		}

		this.dpmtChair = chair;

	}

	// getters

	public String getDepartmentID() {
		return departmentID;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public String getDepartmentLocation() {
		return departmentLocation;
	}

	public String getDpmtPhonenumber() {
		return dpmtPhonenumber;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public Integer getDpmtChair() {
		return dpmtChair;
	}

	// setters
	public void setDepartmentLocation(String departmentLocation) {
		this.departmentLocation = departmentLocation;
	}

	public void setDpmtPhonenumber(String dpmtPhonenumber) {
		if (dpmtPhonenumber == null) {
			throw new NullPointerException();
		}
		if (isPhoneValid(dpmtPhonenumber)) {
			this.dpmtPhonenumber = dpmtPhonenumber;
		} else {
			throw new InvalidDataException();
		}

	}

	public void setFaxNumber(String faxNumber) {
		if (faxNumber == null) {
			throw new NullPointerException();
		}
		if (isPhoneValid(faxNumber)) {
			this.faxNumber = faxNumber;
		} else {
			throw new InvalidDataException();
		}
	}

	public void setDpmtChair(Integer dpmtChair) {
		this.dpmtChair = dpmtChair;
	}

	public boolean isPhoneValid(String phone) {
		return (phone.length() == 10);
	}

	public int compareTo(Department dept) {

		return (this.departmentID.compareTo(dept.departmentID));

	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		if (departmentID == null) {
			if (other.departmentID != null)
				return false;
		} else if (!departmentID.equals(other.departmentID))
			return false;
		return true;
	}

	public String toString() {
		StringBuffer b = new StringBuffer();
		b.append("Department ID: " + this.departmentID);
		b.append(" Department Name: " + this.departmentName);
		if (departmentLocation != null) {
			b.append("\nLocation: " + this.departmentLocation);
		}
		if (dpmtPhonenumber != null) {
			b.append("\nPhonenumber: " + this.dpmtPhonenumber);
		}
		if (faxNumber != null) {
			b.append("\nFaxnumber: " + this.faxNumber);
		}
		if (dpmtChair != null) {
			b.append("\nChair Person: " + this.dpmtChair + "\n");
		}

		return b.toString();
	}

}
