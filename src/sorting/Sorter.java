package sorting;
import java.util.ArrayList;
import java.util.Comparator;

public class Sorter <T extends Comparable<T>>{
	
	public ArrayList<T> sort(ArrayList<T> collection){
		T element;
		
		//first one is in the right place
		for(int i = 1;i < collection.size(); i++){
			element = collection.get(i);
			int position = i;
			while(position > 0 && element.compareTo(collection.get(position-1)) < 0 ){
				collection.set(position, collection.get(position-1));
				position--;
			}
			
			//now you know where the value belongs
			
			collection.set(position, element);
			
			
		}
		
		
		return collection;
	}
	
	public ArrayList<T> sort(ArrayList<T> collection, Comparator<T> comparator){
T element;
		
		//first one is in the right place
		for(int i = 1;i < collection.size(); i++){
			element = collection.get(i);
			int position = i;
		//	while(position > 0 && element.compareTo(collection.get(position-1)) < 0 )
			while(position > 0 && comparator.compare(element, collection.get(position - 1)) < 0 ){
				collection.set(position, collection.get(position-1));
				position--;
			}
			
			//now you know where the value belongs
			
			collection.set(position, element);
			
			
		}
		
		
		return collection;
	}

}
