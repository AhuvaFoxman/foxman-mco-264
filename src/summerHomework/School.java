package summerHomework;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class School {

	private String schoolName;
	private Address address;
	private String phoneNumber;
	private ArrayList<Person> people; // will store references of teachers and
										// students
	private ArrayList<Course> courses;
	private ArrayList<Department> departments;

	public School(String name, Address address, String phonenumber)
			throws FileNotFoundException, NotFoundException {
		this(name, address, phonenumber, null, null, null, null);
	}

	public School(String name, Address address, String phonenumber,
			String teachFileName, String studentFileName, String deptFileName,
			String courseFileName) throws FileNotFoundException,
			NotFoundException {
		if (name == null || address == null || teachFileName == null
				|| studentFileName == null || deptFileName == null
				|| courseFileName == null) {
			throw new InvalidDataException();
		}
		this.schoolName = name;

		this.address = address;

		if (phonenumber == null || phonenumber == "N/A") {
			this.phoneNumber = null;
		} else if (phonenumber.length() == 10)
			this.phoneNumber = phonenumber;
		else {
			throw new InvalidDataException();
		}

		this.people = new ArrayList<Person>();
		this.courses = new ArrayList<Course>();
		this.departments = new ArrayList<Department>();

		Scanner input = new Scanner(new File(teachFileName));

		while (input.hasNext()) {
			Integer teachId = input.nextInt();
			String first = input.next();
			String last = input.nextLine().trim();
			String street = input.nextLine();
			String[] cityState = input.nextLine().split(",");
			String city = cityState[0];
			String state = cityState[1];
			String zip = input.next();
			String phone = input.next();
			Character gender = input.next().charAt(0);
			String hireDate = input.next();
			String birthday = input.next();
			String employeeType = input.next();
			String deptCode = input.next();
			String social = input.next();
			String degree = input.next();
			String major = input.next();
			Double salary = input.nextDouble();

			Address teacherAddress = new Address(street, city, state, zip);

			people.add(new Teacher(teachId, first, last, null, teacherAddress,
					phone, gender, hireDate, birthday, employeeType, deptCode,
					social, degree, major, salary));

		}

		input.close();

		Scanner inputTwo = new Scanner(new File(studentFileName));
		while (inputTwo.hasNext()) {
			Integer studentID = inputTwo.nextInt();

			String last = inputTwo.next();
			String first = inputTwo.next();
			Character initial = inputTwo.nextLine().charAt(0);
			String street = inputTwo.nextLine();
			String[] cityState = inputTwo.nextLine().split(",");
			String city = cityState[0];
			String state = cityState[1];
			String zip = inputTwo.nextLine();
			String phone = inputTwo.next();
			Character gender = inputTwo.next().charAt(0);
			String major = inputTwo.nextLine().trim();
			String birthday = inputTwo.next();
			String enrolledDate = inputTwo.next();
			String social = inputTwo.next();

			Address studentAddress = new Address(street, city, state, zip);
			people.add(new Student(studentID, first, last, initial,
					studentAddress, phone, gender, major, enrolledDate,
					birthday, social));

		}
		inputTwo.close();

		Scanner inputThree = new Scanner(new File(courseFileName));

		while (inputThree.hasNext()) {
			String[] tokens = inputThree.nextLine().split(";");
			String courseID = tokens[0];
			String courseName = tokens[1];
			String deptID = tokens[2];
			Integer credits = Integer.parseInt(tokens[3]);

			courses.add(new Course(courseID, courseName, deptID, credits));
		}
		inputThree.close();

		Scanner inputFour = new Scanner(new File(deptFileName));

		while (inputFour.hasNext()) {
			String[] string = inputFour.nextLine().split(";");
			String deptID = string[0];
			String deptName = string[1];
			String location = string[2];
			String phoneNumber = string[3];
			String fax = string[4];
			Integer teachID = Integer.parseInt(string[5]);

			departments.add(new Department(deptID, deptName, location,
					phoneNumber, fax, teachID));

		}
		inputFour.close();
	}

	public String getSchoolName() {
		return schoolName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		if (phoneNumber == null) {
			throw new NullPointerException();
		}
		if (phoneNumber.length() != 10) {
			throw new InvalidDataException();
		}
		this.phoneNumber = phoneNumber;
	}

	public String getPeople() {
		return this.people.toString();
	}

	public String getCourses() {
		return this.courses.toString();
	}

	public String getDepartments() {
		return this.departments.toString();
	}

	public String toString() {
		StringBuffer info = new StringBuffer();
		info.append("School: ");
		info.append("Name: " + this.schoolName);
		info.append(" Address: " + this.address);
		info.append(" Phonenumber: " + this.phoneNumber);
		info.append(" People: " + this.people.toString());
		info.append(" Courses: " + this.courses.toString());
		info.append(" Departments: " + this.departments.toString());

		return info.toString();

	}

	public void addTeacher(Integer ID, String first, String last,
			Character initial, Address address, String phone, Character gender,
			String dateHired, String birthday, String empTypeId,
			String departmentID, String socialSecurity, String degree,
			String major, Double salary) throws NotFoundException,
			DuplicateDataException {

		// check if the teacher already exists
		for (Person p : people) {
			if (p.getID().equals(ID)) {
				throw new DuplicateDataException();
			}
		}

		people.add(new Teacher(ID, first, last, initial, address, phone,
				gender, dateHired, birthday, empTypeId, departmentID,
				socialSecurity, degree, major, salary));
	}

	public void addTeacher(Integer ID, String first, String last,
			Address address, Character gender, String dateHired,
			String birthday, String empTypeId, String departmentID,
			String socialSecurity, String degree, String major, Double salary)
			throws NotFoundException, DuplicateDataException {

		for (Person p : people) {
			if (p.getID().equals(ID)) {
				throw new DuplicateDataException();
			}
		}
		people.add(new Teacher(ID, first, last, address, gender, dateHired,
				birthday, empTypeId, departmentID, socialSecurity, degree,
				major, salary));

	}

	public void addStudent(Integer ID, String first, String last,
			Character initial, Address address, String phoneNumber,
			Character gender, String major, String enrolledDate,
			String birthday, String social) throws DuplicateDataException {

		for (Person p : people) {
			if (p.getID().equals(ID)) {
				throw new DuplicateDataException();
			}
		}
		people.add(new Student(ID, first, last, initial, address, phoneNumber,
				gender, major, enrolledDate, birthday, social));
	}

	public void addCourse(String courseID, String description, String dept,
			Integer numCredits) throws DuplicateDataException {

		// check to see that this course does not exist yet

		Course course = new Course(courseID, description, dept, numCredits);
		if (this.courses.contains(course)) {
			throw new DuplicateDataException();
		}
		courses.add(course);
	}

	public void addDepartment(String departmentID, String dpmtName,
			String location, String phone, String fax, Integer chair)
			throws DuplicateDataException {
		Department dpmt = new Department(departmentID, dpmtName, location,
				phone, fax, chair);
		if (departments.contains(dpmt)) {
			throw new DuplicateDataException();
		}
		departments.add(dpmt);

	}

	public void removeTeacher(int teacherID) throws NotFoundException {
		boolean found = false;
		for (Person p : people) {
			if (p instanceof Teacher) {
				if (teacherID == p.getID()) {
					people.remove(p);
					found = true;
					break;
				}
			}

		}
		if (found == false) {
			throw new NotFoundException();
		}

	}

	public void removeStudent(int studentID) throws NotFoundException {
		boolean found = false;
		for (Person p : people) {
			if (p instanceof Student) {
				if (studentID == p.getID()) {
					people.remove(p);
					found = true;
					break;
				}
			}
		}

		if (found == false) {
			throw new NotFoundException();
		}
	}

	public void removeCourse(String courseID) throws NotFoundException {
		boolean found = false;
		for (Course c : courses) {
			if (courseID.equalsIgnoreCase(c.getCourseID())) {
				courses.remove(c);
				found = true;
				break;
			}
		}
		if (found == false) {
			throw new NotFoundException();
		}
	}

	public void modifyTeacherLastName(int teacherID, String newLast)
			throws NotFoundException {
		boolean found = false;
		for (Person p : people) {
			if (p instanceof Teacher) {
				if (teacherID == p.getID()) {
					((Teacher) p).setLastName(newLast);
					found = true;
					break;
				}
			}
		}

		if (found == false) {
			throw new NotFoundException();
		}
	}

	public void modifyTeacherAddress(int teacherID, Address address)
			throws NotFoundException {
		boolean found = false;
		for (Person p : people) {
			if (p instanceof Teacher) {
				if (teacherID == p.getID()) {
					((Teacher) p).setAddress(address);
					found = true;
					break;
				}
			}
		}

		if (found == false) {
			throw new NotFoundException();
		}
	}

	public void modifyTeacherDegree(int teacherID, String degree, String major)
			throws NotFoundException {
		boolean found = false;
		for (Person p : people) {
			if (p instanceof Teacher) {
				if (teacherID == p.getID()) {
					((Teacher) p).setDegree(degree);
					((Teacher) p).setMajorID(major);
					found = true;
					break;
				}
			}

		}
		if (found == false) {
			throw new NotFoundException();
		}
	}

	public void giveTeacherRaise(int teacherID, Double percent)
			throws NotFoundException {
		boolean found = false;
		for (Person p : people) {
			if (p instanceof Teacher) {
				if (teacherID == p.getID()) {
					((Teacher) p).applyRaise(percent);
					found = true;
					break;
				}
			}

		}
		if (found == false) {
			throw new NotFoundException();
		}
	}

	public void modifyStudentLastName(int studentID, String newLast)
			throws NotFoundException {
		boolean found = false;
		for (Person p : people) {
			if (p instanceof Student) {
				if (studentID == p.getID()) {
					((Student) p).setLastName(newLast);
					found = true;
					break;
				}
			}
		}
		if (found == false) {
			throw new NotFoundException();
		}
	}

	public void modifyStudentPhoneNumber(int studentID, String newPhone)
			throws NotFoundException {
		boolean found = false;
		for (Person p : people) {
			if (p instanceof Student) {
				if (studentID == p.getID()) {
					((Student) p).setPhoneNumber(newPhone);
					found = true;
					break;
				}
			}
		}
		if (found == false) {
			throw new NotFoundException();
		}
	}

	public void addCompletedCourse(Integer studentID, String courseID,
			Grade grade) throws NotFoundException, DuplicateDataException {

		boolean found = false;
		boolean courseFound = false;
		for (Course c : courses) {
			if (courseID.equalsIgnoreCase(c.getCourseID())) {
				CompletedCourse comp = new CompletedCourse(c.getCourseID(),
						c.getDescription(), c.getDepartmentID(),
						c.getNumCredits(), studentID, grade);
				courseFound = true;
				for (Person p : people) {
					if (p instanceof Student) {
						if (studentID.equals(p.getID())) {

							((Student) p).addCompletedCourse(comp, grade);
							found = true;
							break;
						}
					}
				}
				if (found == false) {
					throw new NotFoundException();
				}
			}
		}
		if (courseFound == false) {
			throw new NotFoundException();
		}
	}

	public double getStudentGPA(int studentID) throws NotFoundException {

		for (Person p : people) {
			if (p instanceof Student) {
				if (studentID == p.getID()) {
					return ((Student) p).getGpa();

				}
			}

		}

		throw new NotFoundException();

	}

	public Grade getGradeOfCourse(int studentID, String courseID)
			throws NotFoundException {
		for (Person p : people) {
			if (p instanceof Student) {
				if (studentID == p.getID()) {
					return ((Student) p).getGradeOfCourse(courseID);

				}
			}
		}
		throw new NotFoundException();
	}

	public ArrayList<CompletedCourse> getCourseByDepartment(int studentID,
			String deptID) throws NotFoundException {

		for (Person p : people) {
			if (p instanceof Student) {
				if (studentID == p.getID()) {
					return ((Student) p).getCourseByDepartment(deptID);
				}
			}
		}
		throw new NotFoundException();
	}

	public ArrayList<CompletedCourse> getCoursesByGrade(int studentID, Grade g)
			throws NotFoundException {
		for (Person p : people) {
			if (p instanceof Student) {
				if (studentID == p.getID()) {
					return ((Student) p).getCourseByGrade(g);

				}
			}
		}
		throw new NotFoundException();

	}

	// sort the teachers name by last and first name
	public String getTeachersSortedByName() {
		ArrayList<Teacher> teacher = new ArrayList<Teacher>();

		for (Person p : people) {
			if (p instanceof Teacher) {
				teacher.add((Teacher) p);
			}
		}

		Collections.sort(teacher, new ComparatorPersonName());
		return teacher.toString();
	}

	// sort the teachers array by teacherID
	public String getTeachers() {
		ArrayList<Teacher> teacher = new ArrayList<Teacher>();
		for (Person p : people) {
			if (p instanceof Teacher) {
				teacher.add((Teacher) p);
			}
		}
		Collections.sort(teacher, new ComparatorPersonID());
		return teacher.toString();
	}

	public String getStudents() {
		ArrayList<Student> student = new ArrayList<Student>();
		for (Person p : people) {
			if (p instanceof Student) {
				student.add((Student) p);
			}
		}
		Collections.sort(student, new ComparatorPersonID());
		return student.toString();
	}

	public String getStudentsByName() {
		ArrayList<Student> student = new ArrayList<Student>();
		for (Person p : people) {
			if (p instanceof Student) {
				student.add((Student) p);
			}
		}

		Collections.sort(student, new ComparatorPersonName());
		return student.toString();
	}

	public void addTaughtCourse(int teacherID, String courseID, Integer year,
			Semester semester, Section section) throws NotFoundException,
			DuplicateDataException {
		boolean found = false;
		boolean foundCourse = false;
		for (Person p : people) {
			if (p instanceof Teacher) {
				if (p.getID() == teacherID) {
					found = true;
					for (Course c : courses) {
						if (c.getCourseID().equalsIgnoreCase(courseID)) {
							((Teacher) p).taughtCourse(c, year, semester,
									section);
							foundCourse = true;
							break;
						}
					}
					if (foundCourse == false) {
						throw new NotFoundException();
					}
				}

			}
		}
		if (found == false) {
			throw new NotFoundException();
		}

	}

	public Integer howManyCoursesPerSemester(int teacherID, Integer year,
			Semester semester) throws NotFoundException {

		for (Person p : people) {
			if (p instanceof Teacher) {
				if (p.getID().equals(teacherID)) {
					return ((Teacher) p).howManyCoursesPerSemster(year,
							semester);
				}
			}
		}
		throw new NotFoundException();
	}

	public boolean isPhoneValid(String phone) {
		return (phone.length() == 10);
	}

	public boolean isZipValid(String zip) {
		return ((zip.length() == 5 || zip.length() == 9));
	}
}
