import java.util.Scanner;

/**
 * Mancala player based on user input
 * @author kristenmccrary
 *
 */

public class InputPlayer implements IPlayer {
	
	public String getPlayerType() {
		return "InputPlayer";
	}

	private Scanner console;
	private boolean firstPlayer;
	public InputPlayer(boolean firstPlayer) {
		this.firstPlayer = firstPlayer;
		console = new Scanner(System.in);
	}
	
	public int chooseMove(int[] board) {
		int range = board.length/2 - 1;
		System.out.print(firstPlayer ? "Player 1: " : "Player 2: ");
		System.out.println("Choose a position to move in the range " + "1-" + range);
		boolean validInput = false;
		int input = 0;
		while(!validInput) {
			if(!console.hasNextInt())  {
				System.out.println("Please enter an integer");
				console.next();
			} else {
				input = console.nextInt();
				if(input < 1 || input > range) {
					System.out.println("Enter a position in the range " + "1-" + range);
				} else if (board[convertMoveToArrPos(input,board)] == 0){
					System.out.println("Choose a non-empty position");
				} else {
					validInput = true;
				}
			}
		}
		return input;
	}
	
	private int convertMoveToArrPos(int move, int[] board) {
		if (firstPlayer) {
			return move - 1;
		}
		else return move + board.length/2 - 1;
	}
	
	public boolean isPlayer1() {
		return this.firstPlayer;
	}

}
