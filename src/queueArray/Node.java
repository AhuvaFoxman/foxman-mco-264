package queueArray;

import java.io.Serializable;

import randomAccessStudentDataCW.StudentIndexRec;

//keeps a reference to a specific data
//keeps a reference to the next Node in the linked list
public class Node<T> { 
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
   
}
