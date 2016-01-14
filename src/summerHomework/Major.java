package summerHomework;

public enum Major {

	ACCT("Accounting"), ART("Art"), BIOL("Biology"), CHEM("Chemistry"), CPSC(
			"Computer Science"), ECON("Economics"), EDUC("Education"), ENGL(
			"English"), ENGR("Engineering"), FRCH("French"), GEOG("Geology"), GERM(
			"German"), GREE("Greek"), HIST("History"), MATH("Math"), MUSC(
			"Music"), NURS("Nursing"), PHIL("Philosophy"), PE("Physical Ed"), PHYS(
			"Physics"), POLS("Political Science"), PSYC("Psycology"), RELI(
			"Religion"), SOCI("Sociology"), SPEE("Speech"), UDCD("Undecided");

	private String major;

	private Major(String major) {
		this.major = major;
	}

	public String getMajor() {
		return this.major;
	}

}