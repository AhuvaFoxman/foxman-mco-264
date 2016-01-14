package summerHomework;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CompletedCourse extends Course {
	private Integer studentID;
	private Grade grade;
	private GregorianCalendar completedDate;

	public CompletedCourse(String courseID, String desc, String dept,
			Integer credits, Integer studentID, Grade grade) {
		super(courseID, desc, dept, credits);

		if (studentID == null || grade == null) {
			throw new InvalidDataException();
		}

		this.grade = grade;
		this.studentID = studentID;
		this.completedDate = new GregorianCalendar(); // today
	}

	public Integer getStudentID() {
		return this.studentID;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	// need to return a copy of the date
	public GregorianCalendar getCompletedDate() {
		GregorianCalendar copyDate = new GregorianCalendar(
				completedDate.get(Calendar.YEAR),
				completedDate.get(Calendar.MONTH),
				completedDate.get(Calendar.DAY_OF_MONTH));
		return copyDate;
	}

	public String toString() {
		StringBuffer b = new StringBuffer();
		SimpleDateFormat format = new SimpleDateFormat("MM/DD/yyyy");
		b.append(super.toString());
		b.append("\nStudent ID: " + this.studentID);
		b.append("\nGrade: " + this.grade);
		b.append("\nCompleted Date: "
				+ format.format(this.completedDate.getTime()) + "\n");

		return b.toString();
	}

}
