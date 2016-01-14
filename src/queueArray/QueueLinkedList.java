package queueArray;

public class QueueLinkedList<T> {
	
	private Node<T> head;
	private Node<T> tail;
	
	public QueueLinkedList(){
		head = tail = null;
		
	}
	
	public void enqueue(T data){
		
		
		if(head == null && tail == null){
			Node<T> newNode = new Node<T>(data);
			head = tail = newNode;
		}
		else{
			Node<T> newNode = new Node<T>(data);
			tail.setNext(newNode);//first make the connection
			tail = newNode; //then move the tail
				}
	}
	
	public T dequeue(){
		if(isEmpty()){
			return null;
		}
		else{
			T value = head.getData();
			head = head.getNext();
			
			if(head == null){
				tail = null;
			}
			return value;
		}
	}
	
	public void removeAll(){
		head = tail = null;
	}
	
	public boolean isEmpty(){
		return head == null;
	}
	
	public static void main (String[] args){
		QueueLinkedList<String> names = new QueueLinkedList<String>();
		names.enqueue("Libby");
		names.enqueue("Rena");
		names.enqueue("Ahuva");
		names.enqueue("Leba");
		
		while (!names.isEmpty()){
			System.out.println(names.dequeue());
		}
		names.enqueue("Chava");
		while(!names.isEmpty()){
		System.out.println(names.dequeue());
		}

	}
	
}
