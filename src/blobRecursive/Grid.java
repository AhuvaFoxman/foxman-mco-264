package blobRecursive;

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class Grid<E> {

	private int rows;
	private int columns;
	private Cell[][] grid;
	private Stack<Cell> stack;

	public Grid(int rows, int columns, int percentage) {
		this.rows = rows;
		this.columns = columns;
		grid = new Cell[this.rows][this.columns];
		stack = new Stack<Cell>();

		int rand;
		Random random = new Random();

		for (int i = 0; i < this.rows; i++) {
			for (int k = 0; k < this.columns; k++) {
				rand = random.nextInt(100);
				if (rand < percentage) {
					this.grid[i][k] = new Cell(i, k);
					this.grid[i][k].setBlob(true);
				} else {
					this.grid[i][k] = new Cell(i, k);
					this.grid[i][k].setBlob(false);
				}
			}
		}

	}

	public int countBlobs() {
		Cell currentCell;
		int count = 0;
		Cell check;

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				currentCell = grid[row][col];
				if (!currentCell.isVisited() && currentCell.hasBlob()) {
					currentCell.setVisited();
					count++;
					markBlob(row, col);
				}

				while (!stack.isEmpty()) {
					check = stack.pop();
					markBlob(check.getRow(), check.getCol());
				}

			}
		}

		return count;
	}

	public void markBlob(int row, int col) {

		Cell below;
		Cell toTheRight;
		Cell above;
		Cell toLeft;
		// check below
		if ((row + 1) < this.rows) {
			below = grid[row + 1][col];
			if (below.hasBlob() && !below.isVisited()) {
				below.setVisited();
				stack.push(below);
			}
		}
		// check to the right
		if ((col + 1) < this.columns) {
			toTheRight = grid[row][col + 1];
			if (toTheRight.hasBlob() && !toTheRight.isVisited()) {
				toTheRight.setVisited();
				stack.push(toTheRight);
			}

			// check above
		}
		if ((row - 1) >= 0) {
			above = grid[row - 1][col];
			if (above.hasBlob() && !above.isVisited()) {
				above.setVisited();
				stack.push(above);
			}

			// check to the left
		}
		if ((col - 1) >= 0) {
			toLeft = grid[row][col - 1];
			if (toLeft.hasBlob() && !toLeft.isVisited()) {
				toLeft.setVisited();
				stack.push(toLeft);
			}
		}

	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (int row = 0; row < rows; row++) {
			buffer.append("\n");
			for (int col = 0; col < columns; col++) {

				buffer.append(" " + grid[row][col].toString());
			}
		}
		return buffer.toString();
	}

	static public void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("What percentage of the board do you want to be BLOBS? ");
		int percent = keyboard.nextInt();

		Grid<Character> theGrid = new Grid<Character>(10, 10, percent);

		System.out.println(theGrid);
		System.out.println("\nThere are " + theGrid.countBlobs() + " BLOBs in the board.");
		
		keyboard.close();

	}
}
