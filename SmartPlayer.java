import java.util.ArrayList;
import java.util.Random;

/**
 * "Smart" Mancala player that chooses a move
 * that gets them an extra move if it
 * exists, otherwise chooses randomly.
 * @author kristenmccrary
 *
 */

public class SmartPlayer implements IPlayer {

	public String getPlayerType() {
		return "SmartPlayer";
	}
	
	private Random rand;
	private boolean firstPlayer;
	public SmartPlayer(boolean firstPlayer) {
		this.firstPlayer = firstPlayer;
		rand = new Random();
	}
	
	public int chooseMove(int[] board) {
		int range = board.length/2 - 1;
		System.out.print(firstPlayer ? "Player 1: " : "Player 2: ");
		System.out.println("Choose a position to move in the range " + "1-" + range);
		ArrayList<Integer> moves = new ArrayList<Integer>();
		// add possible moves to arraylist
		for(int i=range; i>0; i--) {
			int stones = board[convertMoveToArrPos(i,board)];
			if(stones != 0) {
				moves.add(new Integer(i));
				if(stones % (board.length-1) == range + 1 - i) {
					System.out.println(i + " chosen (get extra move)");
					return i;
				}
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
		else return move + board.length/2 - 1;
	}
	
	public boolean isPlayer1() {
		return this.firstPlayer;
	}
}
