package heap;

import java.util.Random;
public class UseHeap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       Heap<Integer> values = new Heap<Integer>();
      /**
       Random numGenerator = new Random();
       Integer nextVal;
       for (int i = 0 ; i< 5; i++){
    	   nextVal = numGenerator.nextInt(100);
    	   values.add(nextVal);  //add value to the heap
    	   
    	   
       }
       **/
       values.add(4);
       values.add(7);
       values.add(6);
       values.add(2);
       values.add(5);
       values.add(3);
       values.add(1);
       //display the heap
       values.display();
       
       //sort the heap
       values.sort();
       
       //display the sorted heap
       values.display();
	}

}
