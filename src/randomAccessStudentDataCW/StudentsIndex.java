package randomAccessStudentDataCW;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class StudentsIndex implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<StudentIndexRec> index;

	/**
	 * set up an index for the first time
	 * 
	 */
	public StudentsIndex() {
		index = new ArrayList<StudentIndexRec>();

	}

	/**
	 * 
	 * @param studentID
	 * @param fileLocation
	 * @throws Exception
	 *             if duplicate
	 */
	public void addStudentToIndex(Integer studentID, Long fileLocation)
			throws DuplicateDataException {
		StudentIndexRec indexRec = new StudentIndexRec(studentID, fileLocation);
		if (index.contains(indexRec))
			throw new DuplicateDataException();
		index.add(indexRec);
		sortIndex(); //imp because if not sorted, it will take longer
	}

	/**
	 * 
	 * @param studentID
	 * @return Long - location of record in the data file
	 * @throws NotFoundException
	 */

	public Long findStudentLocation(Integer studentID) throws NotFoundException {
		//set up dummy StudentIndexRec
				int elem = findStudent(studentID);
				return index.get(elem).getFileLocation();
	}

	/**
	 * 
	 * @param studentID
	 * @return element number of the studentindexrec in the array
	 * @throws NotFoundException
	 */
	private int findStudent(Integer studentID) throws NotFoundException {
		StudentIndexRec dummy = new StudentIndexRec(studentID, 0L);
		int elemIndex = Collections.binarySearch(index, dummy);
		if (elemIndex < 0)
			throw new NotFoundException();
		return elemIndex;
	}

	/**
	 * 
	 * @param studentID
	 * @return true if studentid appears in the index array
	 */

	public boolean hasStudent(Integer studentID) {
		StudentIndexRec dummyRec = new StudentIndexRec(studentID, 0L);
		return index.contains(dummyRec);
	}

	public void removeStudent(Integer studentID) throws NotFoundException {
		int elemIndex = findStudent(studentID);
		index.remove(elemIndex);
	}

	private void sortIndex() {
    	Collections.sort(index); 

	}

	

	

}
