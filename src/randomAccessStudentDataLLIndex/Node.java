package randomAccessStudentDataLLIndex;

import java.io.Serializable;

import randomAccessStudentDataCW.StudentIndexRec;

//keeps a reference to a specific data
//keeps a reference to the next Node in the linked list
public class Node<T extends Comparable<T>> implements Serializable { //this ensures that this can be written out as a whole object
   private T data;
   private Node<T> nextNode;
   
   //this accepts the data and then sets the next node to null
   public Node(T data){ 
	   this.data = data;
	   this.nextNode = null;
   }
   
   //this constructor gets the exact data in the next node
   public Node(T data, Node<T> nextNode){
	   this.data = data;
	   this.nextNode = nextNode;
   }
   
   public void setNext(Node<T> nextNode){
	   this.nextNode  = nextNode;
   }
   
   public Node<T> getNext(){
	   return this.nextNode;
   }
   
   public void setData(T data){
	   this.data = data;
   }
   
   public T getData(){
	   return this.data;
   }
   
   public int compareTo(Node<T> otherNode){
	   return this.getData().compareTo(otherNode.getData());
   }
}
