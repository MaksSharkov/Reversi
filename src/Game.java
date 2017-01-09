public class Game {
	private Fishka whooseTurn = Fishka.COMPUTER1;

	private Board board;

	public Game(int boardSize) {
		board = new Board(boardSize);
		board.initialize();
	}

	public Game() {
		this(8);
	}

	public Board getBoard() {
		return board;
	}

	// ¬озвращает true если игра должна продолжатьс€
	public boolean doMove() {
		Move move = Player.getMove(board, whooseTurn);
		int destRow = move.getDestinationRow();
		int destCol = move.getDestinationCol();
		board.putFishka(destRow, destCol, whooseTurn);
		switchTurn();

		return board.hasEmptyCells()
				&& (board.getFishkaCount(whooseTurn.getEnemy()) != 0);
	}

	private void switchTurn() {
		whooseTurn = whooseTurn.getEnemy();
	}

	public Fishka getWinner() {
		if (board.hasEmptyCells())
			return Fishka.EMPTY;

		int computer1Count = board.getFishkaCount(Fishka.COMPUTER1);
		int computer2Count = board.getFishkaCount(Fishka.COMPUTER2);
		if (computer1Count > computer2Count)
			return Fishka.COMPUTER1;
		else if (computer1Count == computer2Count)
			return Fishka.EMPTY; // ничь€
		else
			return Fishka.COMPUTER2;

	}

	public String getFishkiCount() {
		String result = "";
		int computer1Count = board.getFishkaCount(Fishka.COMPUTER1);
		int computer2Count = board.getFishkaCount(Fishka.COMPUTER2);

		result += String.format("Computer 1 (+) count: %s", computer1Count);
		result += "\n";
		result += String.format("Computer 2 (-) count: %s", computer2Count);
		result += "\n";

		return result;
	}

}
