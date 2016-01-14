package binaryTreeHomework;

public class UseBTree {

	public static void main(String[] args) {
		BinaryTree<Integer> myData = new BinaryTree<Integer>();

		myData.insertRecur(7);
		myData.insertRecur(2);
		myData.insertRecur(4);
		myData.insertRecur(3);
		myData.insertRecur(1);
		myData.insertRecur(6);
		//myData.insertRecur(5);

		myData.traverseInOrder();
		System.out.println(myData.getSortedValues());
		myData.balancedTree(myData.getSortedValues(), 0);

		myData.traversePreOrder();

	}

}
