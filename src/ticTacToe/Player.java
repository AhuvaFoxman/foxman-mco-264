package ticTacToe;

public class Player {

	private String playerLetter;
	private String name;

	// constructor
	// in order to be a player you need to have a letter, either x or o and a name
	public Player(String name, String letter) throws InvalidDataException {
		if ((letter.equalsIgnoreCase("O")) || (letter.equalsIgnoreCase("x"))) {
			this.playerLetter = letter;
		} else {
			throw new InvalidDataException();
		}

		this.name = name;

	}

	public String getPlayerLetter() {
		return this.playerLetter;
	}

	public String getPlayerName() {
		return this.name;
	}

	// accepts the user's input from the main class to choose a row and column
	// will do validation in main to ensure that the row and column are valid
	// values
	public void playerTurn(int row, int col, Board board)
			throws InvalidDataException {

		while ((row < 1 || row > 3) || (col < 1 || col > 3)) {
			throw new InvalidDataException();
		}

		//do row-1 and col-1 so it will be more user friendly
		if (board.getBoard()[row - 1][col - 1].equals("a")) {
			board.setBoard(playerLetter, row - 1, col - 1);
			
			//print board after each turn
			board.printBoard();

		}

	}
}
