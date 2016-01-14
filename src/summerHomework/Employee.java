package summerHomework;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Employee extends Person {

	private GregorianCalendar hireDate;
	private GregorianCalendar birthday;
	private EmployeeType employeeTypeID;

	public Employee(Integer ID, String first, String last, Address address,
			Character gender, String dateHired, String birthday,
			String empTypeId) {
		this(ID, first, last, null, address, null, gender, dateHired, birthday,
				empTypeId);
	}

	public Employee(Integer ID, String first, String last, Character initial,
			Address address, String phone, Character gender, String dateHired,
			String birthday, String empTypeId) {
		super(ID, first, last, initial, address, phone, gender);

		if (dateHired == null || birthday == null || empTypeId == null) {
			throw new InvalidDataException();
		}

		String[] tokens = birthday.split("/");
		int month = Integer.parseInt(tokens[0]);
		int day = Integer.parseInt(tokens[1]);
		int year = Integer.parseInt(tokens[2]);

		this.birthday = new GregorianCalendar(year, month - 1, day);

		String[] date = dateHired.split("/");
		int m = Integer.parseInt(date[0]);
		int d = Integer.parseInt(date[1]);
		int y = Integer.parseInt(date[2]);

		this.hireDate = new GregorianCalendar(y, m - 1, d);
		GregorianCalendar eighteenYears = new GregorianCalendar(
				this.birthday.get(Calendar.YEAR),
				this.birthday.get(Calendar.MONTH),
				this.birthday.get(Calendar.DAY_OF_MONTH));
		// add 18 years so can compare to the hireDate
		eighteenYears.add(Calendar.YEAR, 18);
		if (eighteenYears.getTime().compareTo(this.hireDate.getTime()) > 0) {
			throw new InvalidDataException();
		}

		EmployeeType type = getEmployeeType(empTypeId);
		if (type == null) {
			throw new InvalidDataException();
		}
		this.employeeTypeID = type;

	}

	public String getHireDate() {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		return (format.format(this.hireDate.getTime()));

	}

	public String getBirthday() {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		return (format.format(this.birthday.getTime()));
	}

	public EmployeeType getEmployeeTypeID() {
		return employeeTypeID;
	}

	public void setEmployeeTypeID(String employeeTypeID) {
		if (employeeTypeID == null) {
			throw new NullPointerException();
		}
		EmployeeType type = getEmployeeType(employeeTypeID);
		if (type == null) {
			throw new InvalidDataException();
		}
		this.employeeTypeID = type;

	}

	public EmployeeType getEmployeeType(String type) {
		if (type == null) {
			throw new NullPointerException();
		}
		for (EmployeeType e : EmployeeType.values()) {
			if (e.name().equalsIgnoreCase((type))) {
				return e;
			}
		}

		return null;
	}

	public String toString() {
		StringBuffer info = new StringBuffer();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		info.append(super.toString());
		info.append("\nHire Date: " + format.format(hireDate.getTime()));
		info.append("\nBirthday: " + format.format(birthday.getTime()));
		info.append("\nEmployee Type: " + employeeTypeID);

		return info.toString();
	}

}
