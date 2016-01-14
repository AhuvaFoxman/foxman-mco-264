package stacksHomework;

import java.util.Scanner;
import java.util.Stack;

import summerHomework.InvalidDataException;

public class DecimalToBinary {

	private Stack<String> stack;

	public DecimalToBinary() {
		stack = new Stack<String>();
	}

	public String convertToBinary(int decimal) {
		if(decimal < 0){
			throw new InvalidDataException();
		}
		StringBuffer b = new StringBuffer();
		int remainder;
		if (decimal == 0) {
			return String.valueOf(decimal);
		}
		while (decimal > 0) {
			remainder = decimal % 2;
			stack.push(String.valueOf(remainder));
			decimal /= 2;
		}

		while (!(stack.empty())) {
			b.append(stack.pop());
		}

		return b.toString();
	}

	public static void main(String[] args) {

		DecimalToBinary convert = new DecimalToBinary();
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please enter a positive integer: ");
		int integer = keyboard.nextInt();
		String result = convert.convertToBinary(integer);
		System.out.println("Binary result: " + result);

		keyboard.close();

	}
}
