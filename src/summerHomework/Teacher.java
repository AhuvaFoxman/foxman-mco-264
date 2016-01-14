package summerHomework;

import java.util.ArrayList;

public class Teacher extends Employee {

	private String departmentID;
	private String socialSecurity;
	private Degree degree;
	private Major majorID;
	private Double salary;
	private ArrayList<TaughtCourse> taught;

	// neither initial or phone
	public Teacher(Integer ID, String first, String last, Address address,
			Character gender, String dateHired, String birthday,
			String empTypeId, String departmentID, String socialSecurity,
			String degree, String major, Double salary)
			throws NotFoundException {

		this(ID, first, last, null, address, null, gender, dateHired, birthday,
				empTypeId, departmentID, socialSecurity, degree, major, salary);
	}

	// initial and phone
	public Teacher(Integer ID, String first, String last, Character initial,
			Address address, String phone, Character gender, String dateHired,
			String birthday, String empTypeId, String departmentID,
			String socialSecurity, String degree, String major, Double salary)
			throws NotFoundException {

		super(ID, first, last, initial, address, phone, gender, dateHired,
				birthday, empTypeId);

		if (departmentID == null || socialSecurity == null || degree == null
				|| major == null || salary == null) {
			throw new InvalidDataException();
		}
		this.departmentID = departmentID;

		this.socialSecurity = socialSecurity;

		Degree d = getDegreeName(degree);
		if (d == null) {
			throw new NotFoundException();
		}
		this.degree = d;

		Major m = getMajorName(major);
		if (m == null) {
			throw new NotFoundException();
		}
		this.majorID = m;

		// validate the salary
		if ((salary >= 20000 && salary <= 200000)) {
			this.salary = salary;
		} else {
			throw new NotFoundException();
		}

		this.taught = new ArrayList<TaughtCourse>();

	}

	public String getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}

	public String getSocialSecurity() {
		return socialSecurity;
	}

	public void setSocialSecurity(String socialSecurity) {
		this.socialSecurity = socialSecurity;
	}

	public Degree getDegree() {
		return degree;
	}

	public Degree getDegreeName(String degree) {
		if (degree == null) {
			throw new NullPointerException();
		}
		for (Degree d : Degree.values()) {
			if (d.name().equals(degree)) {
				return d;
			}
		}
		return null;
	}

	public void setDegree(String degree) throws NotFoundException {
		if (degree == null) {
			throw new NullPointerException();
		}
		Degree d = getDegreeName(degree);
		if (d == null) {
			throw new NotFoundException();
		}
		this.degree = d;
	}

	public Major getMajorID() {
		return majorID;
	}

	public Major getMajorName(String major) {
		for (Major m : Major.values()) {
			if (m.toString().equalsIgnoreCase(major)) {
				return m;
			}
		}
		return null;
	}

	public void setMajorID(String majorID) throws NotFoundException {
		if (majorID == null) {
			throw new NullPointerException();
		}
		Major m = getMajorName(majorID);
		if (m == null) {
			throw new NotFoundException();
		}
		this.majorID = m;

	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		// can only increase the salary
		if (salary > this.salary)
			this.salary = salary;

	}

	public String getTaught() {
		return this.taught.toString();
	}

	public void setTaught(ArrayList<TaughtCourse> taught) {
		this.taught = taught;
	}

	// add the percentage to the salary
	public void applyRaise(Double percent) {
		this.salary += salary * percent;
	}

	public void taughtCourse(Course c, Integer year, Semester semester,
			Section sectionID) throws DuplicateDataException {

		TaughtCourse taughtCourse = new TaughtCourse(c.getCourseID(),
				c.getDescription(), c.getDepartmentID(), c.getNumCredits(),
				super.getID(), year, sectionID, semester);
		if (this.taught.contains(taughtCourse)) {
			throw new DuplicateDataException();
		}

		for (TaughtCourse t : taught) {
			if (t.getYear().equals(year) && t.getSection().equals(sectionID)
					&& t.getSemester().equals(semester)) {
				throw new InvalidDataException();
			}
		}

		this.taught.add(taughtCourse);

	}

	public int howManyCoursesPerSemster(Integer year, Semester semesterID)
			throws NotFoundException {
		int count = 0;
		for (TaughtCourse t : this.taught) {
			if (semesterID.equals(t.getSemester()) && year.equals(t.getYear())) {
				count++;
			}

		}
		return count;
	}

	public int howManyDifferentCourses() {

		return this.taught.size();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;

		return true;
	}

	public int compareTo(Teacher teacher) {
		if (teacher.getLastName().equalsIgnoreCase(this.getLastName())) {
			return teacher.getFirstName().compareTo(this.getFirstName());
		} else
			return this.getLastName().compareTo(teacher.getLastName());
	}

	public String toString() {
		StringBuffer info = new StringBuffer();
		info.append("Teacher: ");
		info.append(super.toString());
		info.append("\ndepartment ID: " + departmentID);
		info.append("\nSocial Security Number: " + socialSecurity);
		info.append("\nDegree: " + degree);
		info.append("\nMajor " + majorID);
		info.append("\nSalary: " + salary);
		info.append("\nTaught Courses: " + taught.toString() + "\n");

		return info.toString();
	}

}
