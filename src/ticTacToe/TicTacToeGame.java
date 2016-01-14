package ticTacToe;

import java.util.Scanner;

public class TicTacToeGame {

	public static void main(String[] args) throws InvalidDataException {

		Scanner keyboard = new Scanner(System.in);

		System.out.println("Rules of the Game: ");
		System.out
				.println("Two players take turns entering either an 'X' or an 'O', if you get "
						+ "3 in a row, you win! Good Luck!!");

		String answer;
		//do as long as the user wants to play again
		do { 
			System.out.print("Player One: Please enter your name: ");
			String name = keyboard.nextLine();
			System.out.print(name + ": What letter do you want to be? X/O ");
			String letter = keyboard.nextLine();
			
			//set up an instance of a player
			Player playerOne = new Player(name, letter);

			System.out.print("Player Two: Please enter your name: ");
			name = keyboard.nextLine();
			Player playerTwo = null;
			
			//make playerTwo's letter the opposite of what was chosen by playerOne
			if (letter.equalsIgnoreCase("x")) {
				playerTwo = new Player(name, "O");
				System.out.println(playerTwo.getPlayerName()
						+ " is the letter 'O'.");

			} else if (letter.equalsIgnoreCase("O")) {
				playerTwo = new Player(name, "X");
				System.out.println(playerTwo.getPlayerName()
						+ " is the letter 'X'");

			}
			//make an instance of the Board Class
			Board board = new Board(playerOne, playerTwo);

			// print the empty board
			board.printBoard();
			
			
			
			//current player will always start off with playerOne
			Player currentPlayer = new Player(playerOne.getPlayerName(),
					playerOne.getPlayerLetter());
			
			int row;
			int col;
			int i = 0;
			// while there are enough turns to fill the board
			while (i < 9) {

				System.out.print(currentPlayer.getPlayerName()
						+ ": Enter the row: ");
				row = keyboard.nextInt();
				// flush the buffer
				keyboard.nextLine();

				System.out.print(currentPlayer.getPlayerName()
						+ ": Enter the column: ");
				col = keyboard.nextInt();
				// flush the buffer
				keyboard.nextLine();

				// make a variable for the board so that the method getBoard does
				// not
				// have to be called each time.
				String[][] playerBoard = board.getBoard();
				
				//if the spot was already taken, let the user enter row and col again
				while (!(playerBoard[row - 1][col - 1].equals("a"))) {
					System.out
							.print("This spot was already taken.\nPlease enter the row again: ");
					row = keyboard.nextInt();
					System.out.print("Enter column again: ");
					col = keyboard.nextInt();
				}
				currentPlayer.playerTurn(row, col, board);

				i++; // increment the turn

				String winner;

				winner = board.checkBoard(); // check to see if the player won
												// yet
				if (winner != null) {
					System.out.println("Player " + winner + " won!");
					break;
				} else if ((i == 9) && (winner == null)) {// total amount of
															// turns
					System.out.println("Tie Game! Thanks for playing!");
					break;

				}

				currentPlayer = board.switchPlayer(currentPlayer);
			}

			System.out.print("Do you want to play again? Y/N ");

			answer = keyboard.nextLine();

			if (answer.equalsIgnoreCase("N")) {
				System.out.println("Have a good day.");
				System.exit(0);
			}

		} while (answer.equalsIgnoreCase("Y"));

		keyboard.close(); // close the keyboard

	}

}
