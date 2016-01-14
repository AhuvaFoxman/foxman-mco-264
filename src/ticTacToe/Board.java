package ticTacToe;

public class Board {

	private String[][] board; // a two dimensional array to hold the board
	private Player one;
	private Player two;

	// constructor
	public Board(Player one, Player two) throws InvalidDataException {
		// make a 3x3 board
		this.board = new String[3][3];
		// make all elements of the array "a" inorder not to have any nulls

		for (int j = 0; j < 3; j++) {
			for (int k = 0; k < 3; k++) {
				this.board[j][k] = "a";
			}
		}

		// sending in a copy
		this.one = new Player(one.getPlayerName(), one.getPlayerLetter());
		this.two = new Player(two.getPlayerName(), two.getPlayerLetter());
	}

	public String[][] getBoard() {
		String[][] copy = new String[3][3];
		for (int i = 0; i < copy.length; i++) {
			for (int k = 0; k < copy[i].length; k++) {
				copy[i][k] = board[i][k];
			}
		}

		return copy;
	}

	public void setBoard(String player, int row, int col) {
		this.board[row][col] = player;
	}

	public void printBoard() {

		int row;
		int col;
		// display the 3x3 board to the user
		System.out.println("-------");

		for (row = 0; row < board.length; row++) {
			for (col = 0; col < board[row].length; col++) {
				if (!(board[row][col]).equals("a")) // if it doesnt = "a" then
				{
					System.out.print("|" + board[row][col]);
				} else {
					System.out.print("| ");
				}
			} // end inner loop
			System.out.println("| ");
			System.out.println("-------");
		} // end outer loop
	} // end method

	public String checkBoard() {
		for (int row = 0; row < 3; row++) {
			// if the spot in the array is not an "a", then compare to see if
			// the rows have 3 in a row.
			if ((!(board[row][0]).equals("a"))
					&& (!(board[row][1]).equals("a"))
					&& (!(board[row][2]).equals("a"))) {
				if ((board[row][0]).equals(board[row][1])
						&& (board[row][1]).equals(board[row][2])) {
					return board[row][0];

				}
			}
			for (int col = 0; col < 3; col++) {
				// if there's not a "a", then compare to see if column has 3 in
				// a row
				if ((!(board[0][col]).equals("a"))
						&& (!(board[1][col]).equals("a"))
						&& (!(board[2][col]).equals("a"))) {
					if ((board[0][col]).equals(board[1][col])
							&& (board[1][col]).equals(board[2][col])) {
						return board[0][col];
					}
				} // end if
			} // end inner loop
		} // end outer loop

		// check to see if the diagonals have 3 in a row
		if ((!(board[0][0]).equals("a")) && (!(board[1][1]).equals("a"))
				&& (!(board[2][2]).equals("a"))) {
			if ((board[0][0]).equals(board[1][1])
					&& (board[1][1]).equals(board[2][2])) {
				return board[0][0];
			}
		}

		if ((!(board[0][2]).equals("a")) && (!(board[1][1]).equals("a"))
				&& (!(board[2][0]).equals("a"))) {
			if ((board[0][2]).equals(board[1][1])
					&& (board[1][1]).equals(board[2][0])) {
				return board[0][2];
			}

		}
		return null;

	}

	//clear the board
	public void clearBoard() {
		for (int j = 0; j < 3; j++) {
			for (int k = 0; k < 3; k++) {
				this.board[j][k] = "a";
			}
		}
	}
	
	//method to switch the players turn
	public Player switchPlayer(Player player) throws InvalidDataException {
		if (player.getPlayerLetter().equalsIgnoreCase(one.getPlayerLetter())) {
			return new Player(two.getPlayerName(), two.getPlayerLetter());
		} else {
			return new Player(one.getPlayerName(), one.getPlayerLetter());

		}
	}

}
