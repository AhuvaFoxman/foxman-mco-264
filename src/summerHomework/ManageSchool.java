package summerHomework;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ManageSchool {

	public static void main(String[] args) throws NotFoundException,
			FileNotFoundException, DuplicateDataException {
		Scanner keyboard = new Scanner(System.in);

		School school = null;

		System.out.println("What is the name of your school? ");
		String name = keyboard.nextLine();
		System.out.println("Enter the street address of the school: ");
		String street = keyboard.nextLine();
		System.out.println("Enter the city of the school: ");
		String city = keyboard.nextLine();
		System.out.println("Enter the state of the school: ");
		String state = keyboard.nextLine();

		System.out.println("Enter the zip code of the school: ");
		String zip = keyboard.next();

		while (!(zip.length() == 5 || zip.length() == 9)) {
			System.out
					.println("You did not enter a valid zip code. Please enter again: ");
			zip = keyboard.next();
		}

		Address address = null;

		try {
			address = new Address(street, city, state, zip);
		} catch (NullPointerException e1) {
			System.out
					.println("Incorrect information was given for the address. Shutting down the program.");
			System.exit(1);
		} catch (InvalidDataException e) {
			System.out
					.println("Invalid address was set up. Shutting the program");
			System.exit(1);
		}
		String phone = null;

		System.out.println("Enter the phone number of the school: ");
		phone = keyboard.next();

		while (!(phone.length() == 10)) {
			System.out
					.println("You did not enter a valid phone number. Please enter a 10 digit phone number: ");
			phone = keyboard.next();
		}

		String teachers = "teachers.txt";
		String students = "students.txt";
		String departments = "departments.txt";
		String courses = "courses.txt";

		try {
			school = new School(name, address, phone, teachers, students,
					departments, courses);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (NotFoundException e) {
			System.out.println(e.getMessage());
		} catch (InvalidDataException e) {
			System.out.println(e.getMessage());
		}

		String firstName;
		String lastName;
		Character initial;
		Character gender;
		Integer ID;
		String hireDate;
		String birthday;
		String empType;
		String dept;
		String social;
		String degree;
		String major;
		double salary;

		int choice;
		do {
			choice = menu();
			switch (choice) {
			case 1: // Add a teacher

				System.out.println("Enter the first name of the teacher: ");
				firstName = keyboard.next();
				System.out.println("Enter the last name of the teacher: ");
				lastName = keyboard.next();
				// assuming that noone's name starts with an X
				System.out
						.print("(optional) If you have a middle initial enter it here: if not, Enter \"X\": ");
				initial = keyboard.next().charAt(0);
				keyboard.nextLine();
				System.out.println("Enter the street address of the teacher: ");
				street = keyboard.nextLine();
				System.out.println("Enter the city of the teacher: ");
				city = keyboard.nextLine();
				System.out.println("Enter the State of the teacher: ");
				state = keyboard.nextLine();
				System.out.println("Enter the zip code of the teacher: ");
				zip = keyboard.next();
				while (!(school.isZipValid(zip))) {
					System.out
							.println("You did not enter a valid zip code. Please enter again: ");
					zip = keyboard.next();
				}

				try {
					address = new Address(street, city, state, zip);
				} catch (NullPointerException e1) {
					System.out
							.println("Not enough info was added for the address. Shutting down the program.");
					System.exit(1);
				} catch (InvalidDataException e) {
					System.out
							.println("Invalid address was set up. Shutting the program");
					System.exit(1);
				}
				System.out
						.println("(optional) Enter the phone number of the teacher: If you do not wish to do so, enter \"NA\": ");
				phone = keyboard.next();

				System.out.println("Enter the Gender of the teacher: F/M ");
				gender = keyboard.next().charAt(0);
				System.out.println("Enter the teacher ID: ");
				ID = keyboard.nextInt();
				keyboard.nextLine(); // swallow the extra line
				System.out
						.println("Enter the date you were hired: (MM/DD/YYYY)");
				hireDate = keyboard.nextLine();
				System.out
						.println("Enter the teacher's birthday: (MM/DD/YYYY)");
				birthday = keyboard.nextLine();
				System.out
						.println("What type of employee are you: PROFESSOR,INSTRUCTOR");
				empType = keyboard.nextLine();
				System.out.println("Enter the department ID: ");
				dept = keyboard.nextLine();
				System.out.println("Enter your social security number: ");
				social = keyboard.nextLine();
				System.out.println("Enter your degree: BA,BS, MA,MS,Phd,CPA");
				degree = keyboard.nextLine();
				System.out.println("Enter your the full name of your major: ");
				major = keyboard.nextLine();

				for (Major m : Major.values()) {
					if (m.getMajor().equalsIgnoreCase(major)) {
						major = m.name();
						break;
					}
				}
				System.out.println("Enter your salary: ");
				salary = keyboard.nextDouble();

				try {
					if (!(phone.equalsIgnoreCase("NA"))
							&& (!(initial.equals('X')))) {

						school.addTeacher(ID, firstName, lastName, initial,
								address, phone, gender, hireDate, birthday,
								empType, dept, social, degree, major, salary);
						System.out.println("Teacher " + firstName + " "
								+ lastName + " was added.");
					} else if ((!(phone.equalsIgnoreCase("NA")))
							&& initial.equals('X')) {
						school.addTeacher(ID, firstName, lastName, null,
								address, phone, gender, hireDate, birthday,
								empType, dept, social, degree, major, salary);
						System.out.println("Teacher " + firstName + " "
								+ lastName + " was added.");

					} else if (phone.equalsIgnoreCase("NA")
							&& (!(initial.equals('X')))) {
						school.addTeacher(ID, firstName, lastName, initial,
								address, null, gender, hireDate, birthday,
								empType, dept, social, degree, major, salary);
						System.out.println("Teacher " + firstName + " "
								+ lastName + " was added.");

					} else {
						school.addTeacher(ID, firstName, lastName, null,
								address, null, gender, hireDate, birthday,
								empType, dept, social, degree, major, salary);
						System.out.println("Teacher " + firstName + " "
								+ lastName + " was added.");
					}
				} catch (DuplicateDataException e) {
					System.out.println("Teacher is already in the system");
					System.exit(1);
				} catch (NotFoundException e) {
					System.out
							.println("Data could not be found. Shutting the program...");
					System.exit(1);

				} catch (InvalidDataException e1) {
					System.out
							.println("Invalid data was entered for the teacher. Exiting...");
					System.exit(1);

				}
				break;

			case 2:// Add a student
				System.out.println("Enter the first name of the student: ");
				firstName = keyboard.next();
				System.out.println("Enter the last name of the student: ");
				lastName = keyboard.next();
				System.out
						.print("(Optional) Enter the middle intial: If you do not have one enter \"X\": ");
				initial = keyboard.next().charAt(0);
				keyboard.nextLine();
				System.out.println("Enter the street address of the student: ");
				street = keyboard.nextLine();
				System.out.println("Enter the city of the student: ");
				city = keyboard.nextLine();
				System.out.println("Enter the State of the student: ");
				state = keyboard.nextLine();
				System.out.println("Enter the zip code of the student: ");
				zip = keyboard.next();
				while ((!school.isZipValid(zip))) {
					System.out
							.println("You did not enter a valid zip code. Please enter again: ");
					zip = keyboard.next();
				}

				try {
					address = new Address(street, city, state, zip);
				} catch (NullPointerException e1) {
					System.out
							.println("Not enough info was added for the address. Shutting down the program.");
					System.exit(1);
				} catch (InvalidDataException e) {
					System.out
							.println("Invalid address was set up. Shutting the program");
					System.exit(1);
				}
				System.out
						.println("(optional) Enter the phone number of the student: If you do not wish to enter a phone, enter \"NA\"");
				phone = keyboard.next();

				keyboard.nextLine();
				System.out.println("Enter the Gender of the student: F/M ");
				gender = keyboard.nextLine().charAt(0);
				System.out.println("Enter the student ID: ");
				ID = keyboard.nextInt();
				keyboard.nextLine(); // swallow the extra line

				System.out
						.println("Enter your the full name of your major: If you are undecided, enter \"NA\"");
				major = keyboard.nextLine();

				for (Major m : Major.values()) {
					if (m.getMajor().equalsIgnoreCase(major)) {
						major = m.name();
						break;
					}
				}
				System.out.println("Enter the date you enrolled: (MM/DD/YYYY)");
				String date = keyboard.nextLine();
				System.out.println("Enter your birthday: (MM/DD/YYYY)");
				birthday = keyboard.nextLine();
				System.out.println("Enter your social security number: ");
				social = keyboard.nextLine();

				try {
					if (major.equalsIgnoreCase("NA")
							&& (!(phone.equalsIgnoreCase("NA")) && (!(initial
									.equals('X'))))) {
						school.addStudent(ID, firstName, lastName, initial,
								address, phone, gender, null, date, birthday,
								social);
						System.out.println("Student " + firstName + " "
								+ lastName + " was added.");
					} else if (major.equalsIgnoreCase("NA")
							&& (!(phone.equalsIgnoreCase("NA")) && (initial
									.equals('X')))) {
						school.addStudent(ID, firstName, lastName, null,
								address, phone, gender, null, date, birthday,
								social);
						System.out.println("Student " + firstName + " "
								+ lastName + " was added.");

					} else if (phone.equalsIgnoreCase("NA")
							&& (!(initial.equals('X')) && (major
									.equalsIgnoreCase("NA")))) {
						school.addStudent(ID, firstName, lastName, initial,
								address, null, gender, null, date, birthday,
								social);
					} else if (phone.equalsIgnoreCase("NA")
							&& initial.equals('X')
							&& (major.equalsIgnoreCase("NA"))) {
						school.addStudent(ID, firstName, lastName, null,
								address, null, gender, null, date, birthday,
								social);
						System.out.println("Student " + firstName + " "
								+ lastName + " was added.");
					} else if ((!(phone.equalsIgnoreCase("NA")) && (!(initial
							.equals('X')))) && !(major.equalsIgnoreCase("NA"))) {
						school.addStudent(ID, firstName, lastName, initial,
								address, phone, gender, major, date, birthday,
								social);
						System.out.println("Student " + firstName + " "
								+ lastName + " was added.");

					} else if ((!(phone.equalsIgnoreCase("NA")) && (initial
							.equals('X'))) && !(major.equalsIgnoreCase("NA"))) {
						school.addStudent(ID, firstName, lastName, null,
								address, phone, gender, major, date, birthday,
								social);
						System.out.println("Student " + firstName + " "
								+ lastName + " was added.");

					} else if (phone.equalsIgnoreCase("NA")
							&& (!(initial.equals('X')) && !(major
									.equalsIgnoreCase("NA")))) {
						school.addStudent(ID, firstName, lastName, initial,
								address, null, gender, major, date, birthday,
								social);
					} else if (phone.equalsIgnoreCase("NA")
							&& initial.equals('X')
							&& !(major.equalsIgnoreCase("NA"))) {
						school.addStudent(ID, firstName, lastName, null,
								address, null, gender, major, date, birthday,
								social);
						System.out.println("Student " + firstName + " "
								+ lastName + " was added.");

					}
				} catch (DuplicateDataException e) {
					System.out.println("Student ID is already in the system");
					System.exit(1);
				} catch (InvalidDataException e1) {
					System.out
							.println("Invalid data was entered for the student. Exiting...");
					System.exit(1);

				}

				break;

			case 3:// Add a course
				System.out.println("Enter the course ID: ");
				String course = keyboard.next();
				keyboard.nextLine();
				System.out.println("Enter the course description: ");
				String description = keyboard.nextLine();
				System.out
						.println("Enter the amount of credit this course is worth: ");
				Integer credits = keyboard.nextInt();
				keyboard.nextLine(); // swallow the line
				System.out.println("Enter the department ID: ");
				dept = keyboard.nextLine();

				try {
					school.addCourse(course, description, dept, credits);
				} catch (DuplicateDataException e) {
					System.out.println("Course was already added.");
					System.exit(1);
				} catch (InvalidDataException e) {
					System.out
							.println("Invalid data was entered for the course. Exiting...");
					System.exit(1);

				}
				System.out.println("Course " + description + " was added.");

				break;

			case 4: // Add a department
				System.out.println("Enter the department ID: ");
				dept = keyboard.next();
				keyboard.nextLine();
				System.out.println("Enter the department name: ");
				name = keyboard.nextLine();
				System.out.println("Enter the location: ");
				String location = keyboard.nextLine();
				System.out.println("Enter the phone number: ");
				phone = keyboard.next();
				while (!school.isPhoneValid(phone)) {
					System.out
							.println("You did not enter a valid phone number. Please enter a 10 digit phone number: ");
					phone = keyboard.next();
				}
				System.out.println("Enter the fax number: ");
				String fax = keyboard.next();
				while (!school.isPhoneValid(fax)) {
					System.out
							.println("You did not enter a valid phone number. Please enter a 10 digit phone number: ");
					fax = keyboard.next();
				}
				System.out.println("Enter the ID of the department chair: ");
				ID = keyboard.nextInt();

				try {
					school.addDepartment(dept, name, location, phone, fax, ID);
				} catch (DuplicateDataException e) {
					System.out
							.println("That department is already in the system.");
					System.exit(1);
				}
				System.out.println("Department " + name + " was added.");
				break;

			case 5:
				System.out
						.println("Enter the teacher's ID you wish to remove: ");
				ID = keyboard.nextInt();

				try {
					school.removeTeacher(ID);
				} catch (NotFoundException e) {
					System.out
							.println("Teacher could not be found. Exiting...");
					System.exit(1);
				}
				System.out.println("Teacher " + ID + " was removed.");

				break;

			case 6:
				System.out
						.println("Enter the student's ID you wish to remove: ");
				ID = keyboard.nextInt();
				keyboard.nextLine();

				try {
					school.removeStudent(ID);
				} catch (NotFoundException e) {
					System.out
							.println("Student could not be found. Exiting...");
					System.exit(1);
				}
				System.out.println("Student " + ID + " was removed.");

				break;

			case 7:
				keyboard.nextLine();
				System.out
						.println("Enter the Course ID of the course you wish to remove: ");
				course = keyboard.nextLine();

				try {
					school.removeCourse(course);
				} catch (NotFoundException e) {
					System.out.println("Course could not be found. Exiting...");
					System.exit(1);
				}
				System.out.println("Course " + course + " was removed.");

				break;

			case 8:
				System.out
						.println("Enter the teacher's ID you wish to modify: ");
				ID = keyboard.nextInt();

				keyboard.nextLine(); // flush out the buffer

				System.out.println("Enter the teacher's new last name: ");
				lastName = keyboard.nextLine();

				try {
					school.modifyTeacherLastName(ID, lastName);
				} catch (NotFoundException e) {
					System.out
							.println("Teacher could not be found. Exiting...");
					System.exit(1);
				}
				System.out.print("Teacher " + ID + " last name was modified.");

				break;

			case 9:
				System.out
						.println("Enter the teacher's ID you wish to modify: ");
				ID = keyboard.nextInt();

				keyboard.nextLine(); // flush out the buffer

				System.out.println("Enter the street address of the teacher: ");
				street = keyboard.nextLine();
				System.out.println("Enter the city of the teacher: ");
				city = keyboard.nextLine();
				System.out.println("Enter the State of the teacher: ");
				state = keyboard.nextLine();
				System.out.println("Enter the zip code of the teacher: ");
				zip = keyboard.nextLine();

				while (!school.isZipValid(zip)) {
					System.out
							.println("You did not enter a valid zipcode. Please enter a 5 or 9 digit zip: ");
					zip = keyboard.nextLine();
				}

				try {
					address = new Address(street, city, state, zip);
				} catch (NullPointerException e1) {
					System.out
							.println("Not enough info was added for the address. Shutting down the program.");
					System.exit(1);
				} catch (InvalidDataException e) {
					System.out
							.println("Invalid address was set up. Shutting the program");
					System.exit(1);
				}

				try {
					school.modifyTeacherAddress(ID, address);
				} catch (NotFoundException e) {
					System.out
							.println("Teacher could not be found. Exiting...");
					System.exit(1);
				}

				System.out.println("Teacher " + ID + " adress was modified.");

				break;

			case 10:

				System.out
						.println("Enter the teacher's ID you wish to modify: ");
				ID = keyboard.nextInt();

				keyboard.nextLine(); // flush out the buffer

				System.out.println("Enter the teacher's new degree: ");
				degree = keyboard.next();
				System.out.println("Enter the teacher's new major:");
				major = keyboard.next();
				for (Major m : Major.values()) {
					if (m.getMajor().equalsIgnoreCase(major)) {
						major = m.name();
						break;
					}
				}

				try {
					school.modifyTeacherDegree(ID, degree, major);
				} catch (NotFoundException e) {
					System.out
							.println("Teacher could not be found. Exiting...");
					System.exit(1);
				}

				System.out.println("Teacher " + ID + " degree was modified.");

				break;

			case 11:
				System.out
						.println("Enter the teacher's ID you wish to modify: ");
				ID = keyboard.nextInt();
				System.out.println("Enter the percent amount of the raise: ");
				double percent = keyboard.nextDouble();

				try {
					school.giveTeacherRaise(ID, percent);
				} catch (NotFoundException e) {
					System.out
							.println("Teacher could not be found. Exiting...");
					System.exit(1);
				}
				System.out.println("Teacher " + ID + " was given a " + percent
						+ "% raise");

				break;

			case 12:
				System.out
						.println("Enter the student's ID you wish to modify: ");
				ID = keyboard.nextInt();

				keyboard.nextLine(); // flush out the buffer

				System.out.println("Enter the student's new last name: ");
				lastName = keyboard.nextLine();

				try {
					school.modifyStudentLastName(ID, lastName);
				} catch (NotFoundException e) {
					System.out
							.println("Student could not be found. Exiting...");
					System.exit(1);
				}
				System.out
						.println("Student " + ID + " last name was modified.");

				break;

			case 13:
				System.out
						.println("Enter the student's ID you wish to modify: ");
				ID = keyboard.nextInt();

				keyboard.nextLine(); // flush out the buffer

				System.out.println("Enter the student's new phone number: ");
				phone = keyboard.nextLine();
				while (!school.isPhoneValid(phone)) {
					System.out
							.println("You did not enter a valid phone number. Please enter a 10 digit phone number: ");
					phone = keyboard.next();
				}

				try {
					school.modifyStudentPhoneNumber(ID, phone);
				} catch (NotFoundException e) {
					System.out
							.println("Student could not be found. Exiting...");
					System.exit(1);
				}
				System.out.println("Student " + ID
						+ " phone number was modified.");

				break;

			case 14:
				System.out.println("Enter the student's ID: ");
				ID = keyboard.nextInt();

				keyboard.nextLine(); // flush out the buffer

				System.out.println("Enter the course ID: ");
				course = keyboard.nextLine();
				System.out
						.println("Enter the grade from the following choices that you received on the course: \n"
								+ "APLUS (4.0) , A(4.0), AMINUS (3.7), BPLUS (3.3), B(3.0), BMINUS(2.7),CPLUS(2.3), "
								+ "C(2.0),CMINUS(1.7),DPLUS(1.3),D(1.0),DMINUS(.7), F(0.0)");
				String grade = keyboard.nextLine();
				Grade GPA = null;
				for (Grade g : Grade.values()) {
					if (g.name().equalsIgnoreCase(grade)) {
						GPA = g;
					}
				}
				try {
					school.addCompletedCourse(ID, course, GPA);
				} catch (NotFoundException e) {
					System.out
							.println("Student ID or Course ID not Found. Ending the program");
					System.exit(1);
				}
				System.out.println("A completed course was added for student "
						+ ID + ".");
				break;

			case 15:
				System.out.println("Enter the student's ID: ");
				ID = keyboard.nextInt();

				keyboard.nextLine(); // flush out the buffer

				try {
					System.out.println("Student " + ID + " GPA is "
							+ school.getStudentGPA(ID) + ".");
				} catch (NotFoundException e) {
					System.out
							.println("Student could not be found. Exiting...");
					System.exit(1);
				}

				break;

			case 16:
				System.out.println("Enter the student's ID: ");
				ID = keyboard.nextInt();

				keyboard.nextLine(); // flush out the buffer

				System.out.println("Enter the course ID: ");
				course = keyboard.nextLine();

				try {
					System.out.println("The grade of course " + course + " is "
							+ school.getGradeOfCourse(ID, course));
				} catch (NotFoundException e) {
					System.out
							.println("Student or course could not be found. Exiting..");
					System.exit(1);
				}
				break;

			case 17: // get a course by a given department
				System.out.println("Enter the student's ID: ");
				ID = keyboard.nextInt();

				keyboard.nextLine(); // flush out the buffer

				System.out.println("Enter the department ID: ");
				dept = keyboard.nextLine();

				try {
					System.out.println(school.getCourseByDepartment(ID, dept));
				} catch (NotFoundException e) {
					System.out
							.println("Department not found. Shutting the program.");
					System.exit(1);
				}

				break;

			case 18: // get courses by grade
				System.out.println("Enter the student's ID: ");
				ID = keyboard.nextInt();

				keyboard.nextLine(); // flush out the buffer

				System.out
						.println("Enter the grade from the following choices that you received on the course: \n"
								+ "APLUS (4.0) , A(4.0), AMINUS (3.7), BPLUS (3.3), B(3.0), BMINUS(2.7),CPLUS(2.3), "
								+ "C(2.0),CMINUS(1.7),DPLUS(1.3),D(1.0),DMINUS(.7), F(0.0)");
				grade = keyboard.nextLine();

				GPA = null;
				for (Grade g : Grade.values()) {
					if (g.toString().equalsIgnoreCase(grade)) {
						GPA = g;
					}
				}

				try {
					System.out.println(school.getCoursesByGrade(ID, GPA));
				} catch (NotFoundException e) {
					System.out.println("Student or course could not be found");
					System.exit(1);
				}

				break;

			case 19:
				System.out.println(school.getTeachersSortedByName());
				break;

			case 20:
				System.out.println(school.getTeachers());
				break;

			case 21:
				System.out.println(school.getStudents());
				break;

			case 22:
				System.out.println(school.getStudentsByName());
				break;

			case 23:
				System.out.println(school.getCourses());
				break;

			case 24:
				System.out.println(school.getDepartments());
				break;

			case 25:
				System.out.println("Enter the teacher's ID: ");
				ID = keyboard.nextInt();

				keyboard.nextLine(); // flush out the buffer

				System.out.println("Enter the course ID: ");
				course = keyboard.nextLine();
				System.out.println("Enter the year: ");
				Integer year = keyboard.nextInt();

				keyboard.nextLine(); // flush out the buffer

				System.out
						.println("Enter the semester: FALL, SPRING, SUMMER1, SUMMER2");
				String semester = keyboard.nextLine();

				Semester sem = null;
				for (Semester s : Semester.values()) {
					if (s.toString().equalsIgnoreCase(semester)) {
						sem = s;
					}
				}

				System.out
						.println("Enter the section: BA,BB,BC, FA, FB,FD, OL,FC,FE");
				String section = keyboard.nextLine();

				Section sect = null;
				for (Section sec : Section.values()) {
					if (sec.name().equalsIgnoreCase(section)) {
						sect = sec;
					}
				}

				try {
					school.addTaughtCourse(ID, course, year, sem, sect);
				} catch (NotFoundException e) {
					System.out.println("Course or Teacher could not be found");
					System.exit(1);
				} catch (InvalidDataException e) {
					System.out
							.println("You added a course during the time slot that a teacher is already teaching. Exiting..");
					System.exit(1);
				}
				System.out.println("Course " + course
						+ " was added as a taught course.");

				break;

			case 26:
				System.out.println("Enter the teacher's ID: ");
				ID = keyboard.nextInt();

				keyboard.nextLine(); // flush out the buffer

				System.out.println("Enter the course ID: ");
				course = keyboard.nextLine();
				System.out.println("Enter the year: ");
				year = keyboard.nextInt();

				keyboard.nextLine(); // flush out the buffer

				System.out
						.println("Enter the semester: FALL, SPRING, SUMMER1, SUMMER2");
				semester = keyboard.nextLine();

				sem = null;
				for (Semester s : Semester.values()) {
					if (s.toString().equalsIgnoreCase(semester)) {
						sem = s;
					}
				}

				try {
					System.out.println("Teacher " + ID + " taught "
							+ school.howManyCoursesPerSemester(ID, year, sem)
							+ " course(s) during the " + sem + ".");
				} catch (NotFoundException e) {
					System.out
							.println("Information could not be found. Exiting...");
					System.exit(1);
				}

				break;

			case 27:
				System.out.println("Have a good day!");
				System.exit(0);
				break;
			} // end switch
		}// end do while
		while (choice > 0 || choice != 25);

		keyboard.close();
	}// end main

	public static int menu() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("Choose one of the following:");
		System.out.println("1. Add a teacher: \n" + "2. Add a student: \n"
				+ "3. Add a course: \n" + "4. Add a Department: \n"
				+ "5. Remove a teacher: \n" + "6. Remove a student: \n"
				+ "7. Remove a course: \n"
				+ "8. Modify a teacher's last name: \n"
				+ "9. Modify a teacher's address: \n"
				+ "10. Modify a teacher's degree: \n"
				+ "11. Give a teacher a raise \n"
				+ "12. Modify a student's last name: \n"
				+ "13. Modify a student's phone number: \n"
				+ "14. Add a completed course: \n"
				+ "15. Get a student's GPA: \n"
				+ "16. Get the grade of a course: \n"
				+ "17. Get a course by a given department: \n"
				+ "18. Get Courses by grade: \n"
				+ "19. List teacher sorted by name: \n"
				+ "20. List teachers: \n" + "21. List students: \n"
				+ "22. List students sorted by name: \n"
				+ "23. List all courses: \n" + "24. List all departments: \n"
				+ "25. Add a taught course: \n"
				+ "26. Get how many course were taught in a semester: \n"
				+ "27. Exit the program");

		int choice = input.nextInt();

		return choice;
	} // end method

}// end program

