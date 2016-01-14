package sorting;

public class Student implements Comparable<Student> {

	private String studentID;
	private String firstName;
	private String lastName;
	private Double balance;

	public Student(String studentID, String firstName, String lastName,
			Double balance) {
		this.studentID = studentID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.balance = balance;
	}

	public String getStudentID() {
		return studentID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Double getBalance() {
		return balance;
	}

	@Override
	public String toString() {
		return "Student [studentID=" + studentID + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", balance=" + balance + "]";
	}

	public int compareTo(Student other) {
		return lastName.compareTo(other.lastName);
	}

}
