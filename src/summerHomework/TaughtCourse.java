package summerHomework;

public class TaughtCourse extends Course {
	
	private Integer teacherID;
	private Integer year;
	private Section sectionID;
	private Semester semesterID;
	
	
	public TaughtCourse(String courseID, String desc, String dept, Integer credits,
				Integer teachID, Integer year, Section section, Semester semester){
		super(courseID,desc,dept,credits);
		
		
		
		if (teachID == null || year == null || section == null || semester == null  ){
			throw new InvalidDataException();
		}
			this.teacherID = teachID;
			this.year= year;
			this.sectionID = section;
			this.semesterID = semester;
	}
	
	public Integer getTeacherID(){
		return this.teacherID;
	}
	
	public Section getSection(){
		return this.sectionID;
	}
	
	public Semester getSemester(){
		return this.semesterID;
	}
	public Integer getYear(){
		return this.year;
	}
	
	public String toString(){
		StringBuffer b = new StringBuffer();
		b.append(super.toString());
		b.append("\nTeacher ID: " + this.teacherID);
		b.append("\nYear: " + this.year);
		b.append("\nSection: " + this.sectionID);
		b.append("\nSemester: " + this.semesterID);
		
		return b.toString();
	}

}
