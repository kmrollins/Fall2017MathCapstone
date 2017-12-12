import java.util.ArrayList;
import java.util.Random;

/**
 * Mancala player that makes random moves
 * @author kristenmccrary
 *
 */

public class RandomPlayer implements IPlayer {
	
	public String getPlayerType() {
		return "RandomPlayer";
	}
	
	private Random rand;
	private boolean firstPlayer;
	public RandomPlayer(boolean firstPlayer) {
		this.firstPlayer = firstPlayer;
		rand = new Random();
	}
	
	public int chooseMove(int[] board) {
		int range = board.length/2 - 1;
		System.out.print(firstPlayer ? "Player 1: " : "Player 2: ");
		System.out.println("Choose a position to move in the range " + "1-" + range);
		ArrayList<Integer> moves = new ArrayList<Integer>();
		// add possible moves to arraylist
		for(int i=1; i<=range; i++) {
			if(board[convertMoveToArrPos(i,board)] != 0) {
				moves.add(new Integer(i));
			}
		}
		int choice = rand.nextInt(moves.size());
		int input = moves.get(choice);
		System.out.println(input + " chosen");
		return input;
	}
	
	private int convertMoveToArrPos(int move, int[] board) {
		if (firstPlayer) {
			return move - 1;
		}
		else return board.length-1 - move;
	}
	
	public boolean isPlayer1() {
		return this.firstPlayer;
	}

}
