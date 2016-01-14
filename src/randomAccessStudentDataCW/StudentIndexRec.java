package randomAccessStudentDataCW;

import java.io.Serializable;

public class StudentIndexRec implements Serializable, Comparable<StudentIndexRec> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer studentID;
	private Long fileLocation;
	
	public StudentIndexRec(Integer studentID, Long fileLocation){
		this.studentID = studentID;
		this.fileLocation = fileLocation;
	}

	
	public Integer getStudentID(){
		return studentID;
	}
	
	public Long getFileLocation (){
		return fileLocation;
	}
	
	//compare based on studentID
	public int compareTo(StudentIndexRec indexRec ){
		return this.getStudentID().compareTo(indexRec.getStudentID());
	}
	
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (obj instanceof StudentIndexRec) {
			StudentIndexRec indexRec = (StudentIndexRec) obj;
			return studentID.equals(indexRec.studentID);
		}
		return false;
	}
}
