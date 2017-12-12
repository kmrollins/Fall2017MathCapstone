/**
 * Simulation of Mancala
 * @author kristenmccrary
 *
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class MancalaSimulation {

	public static void main(String[] args) {
		playMancala();
	}
	
	public static void playMancala() {
		for(int j=0; j<100; j++) { // iterations of game
		
		// keep records to write to file
		ArrayList<String> record = new ArrayList<String>();
		// define players
		IPlayer player1 = new InputPlayer(true); // can change to another player
		IPlayer player2 = new InputPlayer(false); // can change to another player
		// keep records
		int size = 6;
		int numStones = 4;
		record.add(size + "");
		record.add(numStones + "");
		record.add(player1.getPlayerType());
		record.add(player2.getPlayerType());
		// initialize board
		MancalaBoard board = new MancalaBoard(size,numStones,player1,player2);
		boolean gameOver = false;
		IPlayer curPlayer;
		while(!gameOver) {
			record.add(Arrays.toString(board.getBoard()));
			System.out.println(Arrays.toString(board.getBoard()));
			board.printBoard();
			curPlayer = board.getCurrentPlayer();
			int i = curPlayer.chooseMove(board.getBoard());
			board.makeMove(i);
			gameOver = board.gameOver();
		}
		record.add(Arrays.toString(board.getBoard()));
		board.printBoard();
		board.displayResults();
		record.add(Arrays.toString(board.getBoard()));
		
		try {
			String str = record.toString();
		    BufferedWriter writer = new BufferedWriter(new FileWriter("Mancala_Data", true));
		    writer.append('\n');
		    writer.append(str);		     
		    writer.close();
		} catch (Exception e) {
			System.out.println("Exception occurred");
		}
		}
	}

}

