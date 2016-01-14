package summerHomework;

public class Course {

	private String courseID;
	private String description;
	private String departmentID;
	private Integer numCredits;

	public Course(String courseID, String description, String dept,
			Integer numCredits) {
		if (courseID == null || description == null || dept == null
				|| numCredits == null) {
			throw new InvalidDataException();
		}

		this.courseID = courseID;

		this.description = description;

		this.departmentID = dept;

		if (numCredits < 0 || numCredits > 4) {
			throw new InvalidDataException();
		} else {
			this.numCredits = numCredits;
		}
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}

	public Integer getNumCredits() {
		return numCredits;
	}

	public void setNumCredits(Integer numCredits) {
		this.numCredits = numCredits;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (courseID == null) {
			if (other.courseID != null)
				return false;
		} else if (!courseID.equals(other.courseID))
			return false;
		return true;
	}

	public int compareTo(Course course) {
		return this.courseID.compareTo(course.courseID);
	}

	public String toString() {
		StringBuffer b = new StringBuffer();
		b.append("Course: ");
		b.append("\nCourse ID: " + this.courseID);
		b.append("\nCourse Description: " + this.description);
		b.append("\nDepartment ID: " + this.departmentID);
		b.append("\nNumber of Credits: " + this.numCredits + "\n");

		return b.toString();
	}

}
