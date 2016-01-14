package sorting;

import java.util.ArrayList;
import java.util.Collections;

public class StudentCollections {

	public static void main(String[] args){
		ArrayList<Student> students= new ArrayList<Student>();
	
		students.add(new Student("2", "Rena", "K", 2000.00));
		students.add(new Student("3", "Miri", "U", 1300.00));
		students.add(new Student("1", "Libby", "J", 1000.00));

		System.out.println(students);
		
		Collections.sort(students);
		System.out.println("natural sorted students:");
		System.out.println(students);
		
		Collections.sort(students, new StudentBalanceComparator());
		System.out.println("comparator sorted students:");
		System.out.println(students);
		
		System.out.println("Generic Sorted does the same thing: ");
		Sorter<Student> sorter = new Sorter<Student>();
		sorter.sort(students, new StudentBalanceComparator());
		System.out.println(students);
			
	}
}
