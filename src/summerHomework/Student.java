package summerHomework;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Student extends Person {

	private Major major;
	private GregorianCalendar enrolledDate;
	private GregorianCalendar birthDate;
	private Grade gpa = null; // default
	private Integer creditsEarned = 0; // default
	private String socialSecurity;
	private ArrayList<CompletedCourse> courses;

	// constructor without initial or phone
	public Student(Integer ID, String first, String last, Address address,
			Character gender, String birthday, String enrolledDate,
			String social) {

		this(ID, first, last, null, address, null, gender, null, birthday,
				enrolledDate, social);
	}

	// constructor with inital and phone
	public Student(Integer ID, String first, String last, Character initial,
			Address address, String phoneNumber, Character gender,
			String major, String birthday, String enrolledDate, String social) {

		super(ID, first, last, initial, address, phoneNumber, gender);

		if (enrolledDate == null || birthday == null || social == null) {
			throw new InvalidDataException();
		}
		if (major == null) {
			this.major = Major.UDCD;
		} else {
			this.major = setMajor(major);
		}

		String[] date = enrolledDate.split("/");
		int month = Integer.parseInt(date[0]);
		int day = Integer.parseInt(date[1]);
		int year = Integer.parseInt(date[2]);

		this.enrolledDate = new GregorianCalendar(year, month - 1, day);

		String[] birth = birthday.split("/");
		int m = Integer.parseInt(birth[0]);
		int d = Integer.parseInt(birth[1]);
		int y = Integer.parseInt(birth[2]);
		this.birthDate = new GregorianCalendar(y, m - 1, d);

		this.socialSecurity = social;

		this.courses = new ArrayList<CompletedCourse>();
	}

	public Major getMajor() {
		return major;
	}

	public String getEnrolledDate() {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		return (format.format(this.enrolledDate.getTime()));
	}

	public String getBirthDate() {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		return (format.format(this.birthDate.getTime()));
	}

	public double getGpa() {
		return gpa.getGradeVal();
	}

	public int getCreditsEarned() {
		return creditsEarned;
	}

	public String getSocialSecurity() {
		return this.socialSecurity;
	}

	public String getCourses() {
		return this.courses.toString();
	}

	public void setGpa(String gpa) {
		for (Grade g : Grade.values()) {
			if (g.name().equalsIgnoreCase(gpa)) {
				this.gpa = g;
				break;
			}
		}

	}

	public void setCreditsEarned(Integer creditsEarned) {
		if (creditsEarned == null) {
			throw new NullPointerException();
		}
		if (creditsEarned < 0 || creditsEarned > 120) {
			throw new InvalidDataException();
		} else {
			this.creditsEarned = creditsEarned;
		}
	}

	public Major setMajor(String major) {

		for (Major m : Major.values()) {
			if (m.name().equalsIgnoreCase(major)) {
				return m;

			}
		}
		return null;
	}

	public void addCompletedCourse(Course c, Grade g)
			throws DuplicateDataException {

		CompletedCourse course = new CompletedCourse(c.getCourseID(),
				c.getDescription(), c.getDepartmentID(), c.getNumCredits(),
				super.getID(), g);
		if (this.courses.contains(course)) {
			throw new DuplicateDataException();
		}
		this.courses.add(course);
		setCreditsEarned(c.getNumCredits());
		setGpa(g.name());
	}

	// search through the array of completed courses to see if there is a
	// specific course
	// throws exception if the course is not found
	public CompletedCourse findCompletedCourse(String courseID)
			throws NotFoundException {
		for (CompletedCourse c : courses) {
			if (courseID.equalsIgnoreCase(c.getCourseID())) {
				return c;
			}

		}

		throw new NotFoundException();

	}

	public Grade getGradeOfCourse(String courseID) throws NotFoundException {

		if (courseID == null) {
			throw new NullPointerException();
		}
		for (CompletedCourse c : courses) {
			if (courseID.equalsIgnoreCase(c.getCourseID())) {
				return c.getGrade();
			}
		}
		throw new NotFoundException();
	}

	public ArrayList<CompletedCourse> getCourseByDepartment(String departmentID)
			throws NotFoundException {
		if (departmentID == null) {
			throw new NullPointerException();
		}
		ArrayList<CompletedCourse> complete = new ArrayList<CompletedCourse>();
		for (CompletedCourse c : courses) {
			if (departmentID.equalsIgnoreCase(c.getDepartmentID())) {
				complete.add(c);

			}
		}
		if (complete.size() < 1) {
			throw new NotFoundException();
		}
		return complete;
	}

	public ArrayList<CompletedCourse> getCourseByGrade(Grade g)
			throws NotFoundException {
		if (g == null) {
			throw new NullPointerException();
		}
		ArrayList<CompletedCourse> completeGrade = new ArrayList<CompletedCourse>();
		for (CompletedCourse c : courses) {
			if (g.equals(c.getGrade())) {
				completeGrade.add(c);
			}
		}

		if (completeGrade.size() < 1) {
			throw new NotFoundException();
		}

		return completeGrade;
	}

	public String toString() {
		StringBuffer b = new StringBuffer();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		b.append("Student: ");
		b.append(super.toString());
		if (major == Major.UDCD) {
			b.append("\nMajor is undecided");
		} else {
			b.append("\nMajor: " + this.major);
		}
		b.append("\nDate Enrolled: " + format.format(this.birthDate.getTime()));

		b.append("\nBirthday: " + format.format(this.enrolledDate.getTime()));
		b.append("\nGPA: " + this.gpa);
		b.append("\nCredits: " + this.creditsEarned);
		b.append("\nCourses: " + this.courses.toString() + "\n");

		return b.toString();
	}

}
