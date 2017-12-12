/**
 * Mancala board for Mancala simulation 
 * @author kristenmccrary
 *
 */
public class MancalaBoard {
	
	private IPlayer player1;
	private IPlayer player2;
	private IPlayer currentPlayer;
	private int size;
	private int[] board;
	/**
	 * Initialize board based on size of each player's
	 * possible moves, and the number of initial stones
	 * in those pits
	 * @param size
	 * @param numStones
	 */
	public MancalaBoard(int size, int numStones, IPlayer player1, IPlayer player2) {
		if (size <= 0 || numStones < 0)
			throw new IllegalArgumentException();
		initBoard(size, numStones);
		initPlayers(player1,player2);
	}
	
	private void initBoard(int size, int numStones) {
		this.size = size;
		int boardSize = 2*(this.size+1);
		this.board = new int[boardSize];
		// put stones in pits
		for(int i = 0; i < boardSize; i++) {
			if(i == boardSize-1 || i == this.size)
				this.board[i] = 0;
			else this.board[i] = numStones;
		}
	}
	
	private void initPlayers(IPlayer player1, IPlayer player2) {
		this.player1 = player1;
		this.player2 = player2;
		this.currentPlayer = this.player1;
	}
	
	public void makeMove(int move) {
		int arrPos = convertMoveToArrPos(move);
		int numStones = this.board[arrPos];
		this.board[arrPos] = 0;
		for(int i=0; i < numStones; i++) {
			// increment board position
			arrPos++;
			// if in opponent's store, skip over
			if(currentPlayer.equals(player1) 
					&& (arrPos % this.board.length) == this.board.length-1) arrPos++;
			else if(currentPlayer.equals(player2) 
					&& (arrPos % this.board.length) == this.size) arrPos++;
			// add one to each pit
			this.board[arrPos % this.board.length]++;
		}
		
		int boardPos = arrPos % this.board.length;
		// check if landed in empty position on own side, capture stones
		if(this.board[boardPos] == 1) {
			if(onOwnSide(boardPos)) {
				captureStones(boardPos);
			}
		}
		
		// check if landed in own store, switch players if not
		if(currentPlayer.equals(player1) && !(boardPos == this.size)) {
			switchCurrentPlayer(); 
		} else if(currentPlayer.equals(player2) && !(boardPos == this.board.length-1)) {
			switchCurrentPlayer(); 
		}
	}
	
	private boolean onOwnSide(int boardPos) {
		if(currentPlayer.equals(player1) && boardPos >= 0 && boardPos < this.size) {
			return true;
		} else if(currentPlayer.equals(player2) && boardPos > this.size && boardPos < this.board.length-1) {
			return true;
		} else return false;
	}
	
	private void captureStones(int boardPos) {
		if(currentPlayer.equals(player1)) {
			int oppPos = boardPos + 2 * (this.size - boardPos);
			int toAdd = this.board[boardPos] + this.board[oppPos];
			this.board[this.size] += toAdd;
			this.board[oppPos] = 0;
			this.board[boardPos] = 0;
		} else {
			int oppPos = (boardPos + 2 * (this.board.length-1 - boardPos)) % this.board.length;
			int toAdd = this.board[boardPos] + this.board[oppPos];
			this.board[this.board.length-1] += toAdd;
			this.board[oppPos] = 0;
			this.board[boardPos] = 0;
		}
	}
	
	private int convertMoveToArrPos(int move) {
		if (currentPlayer.equals(player1)) {
			return move - 1;
		}
		else return move + size;
	}
	
	private void switchCurrentPlayer() {
		if(currentPlayer.equals(player1))
			currentPlayer = player2;
		else currentPlayer = player1;
	}
	
	public boolean gameOver() {
		// check player1's side
		// if all 0s, game over
		boolean gameOver = true;
		for(int i=0; i < this.size; i++) {
			gameOver = gameOver && this.board[i] == 0;
		}
		if(gameOver) return true;
		// check player2's side
		// if all 0s, game over
		gameOver = true;
		for(int i=this.size+1; i<this.board.length-1; i++) {
			gameOver = gameOver && this.board[i] == 0;
		}
		return gameOver;
	}
	
	public void displayResults() {
		if(!gameOver()) return;
		for(int i=0; i < this.size; i++) {
			this.board[this.size] += this.board[i];
			this.board[i] = 0;
		}
		for(int i=this.size+1; i<this.board.length-1; i++) {
			this.board[this.board.length-1] += this.board[i];
			this.board[i] = 0;
		}
		System.out.println("--------------------------");
		printBoard();
		int p1 = getPlayer1Score();
		int p2 = getPlayer2Score();
		System.out.println("Player 1 score: " + p1);
		System.out.println("Player 2 score: " + p2);
		if(p1 > p2) System.out.println("Player 1 wins!");
		else if(p2 > p1) System.out.println("Player 2 wins!");
		else System.out.println("It's a draw!");	
	}
	
	public IPlayer getCurrentPlayer() {
		return currentPlayer;
	}
	
	public int[] getBoard() {
		return this.board;
	}
	
	//Print out current state of the board
	public void printBoard() {
		for(int i=this.board.length-1; i>this.size; i--) {
			System.out.print(this.board[i] + "\t");
		} System.out.println(); System.out.println();
		System.out.print("\t");
		for(int i=0; i <= this.size; i++) {
			System.out.print(this.board[i] + "\t");
		} System.out.println();
	}
	
	public int getPlayer1Score() {
		return this.board[this.size];
	}
	public int getPlayer2Score() {
		return this.board[this.board.length-1];
	}
	
}
