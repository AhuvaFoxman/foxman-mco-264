package randomAccessStudentDataLLIndex;

import java.util.Iterator;

import randomAccessExceptions.NotFoundException;

public class UseIterators {

	public static void main(String[] args) throws NotFoundException {

		LinkedList<String> names = new LinkedList<String>();
		
		names.add("Ahuva");
		names.add("Rena");
		names.add("Libby");
		
		 names.remove("Rena");
		Iterator<String> theIter = names.iterator();
		while(theIter.hasNext()){
			System.out.println(theIter.next());
		}
		
		
	}

}
