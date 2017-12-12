/**
 * Mancala player interface
 * @author kristenmccrary
 *
 */
public interface IPlayer {
	
	int chooseMove(int[] board);
	boolean isPlayer1();
	String getPlayerType();
	
}
