import java.util.ArrayList;
import java.util.List;

public class Situation {
	private Board board;

	public Situation(Board board) {
		this.board = new Board(board);
	}

	public Situation(Situation situation) {
		this(situation.board);
	}

	public int applyMove(Move move) {
		int drow = Direction.getDrow(move.getDirection());
		int dcol = Direction.getDcol(move.getDirection());

		int destRow = move.getRow() + drow;
		int destCol = move.getCol() + dcol;

		return board.putFishka(destRow, destCol, move.getOwner());
	}

	public int applyMoves(List<Move> moves) {
		int myProfit = 0;
		int enemyProfit = 0;

		int currentProfit = 0;

		int iterationNumber = 0;
		for (Move move : moves) {
			currentProfit += applyMove(move);
			if ((iterationNumber % 2) == 1)
				enemyProfit += currentProfit;
			else
				myProfit += currentProfit;

			iterationNumber++;
		}
		return myProfit - enemyProfit;
	}

	// ќценочна€ функци€
	public int compareTo(Situation other, Fishka owner) {
		int firstPlayerBeforeCount = board.getFishkaCount(owner);
		int secondPlayerBeforeCount = board.getFishkaCount(owner.getEnemy());

		int firstPlayerAfterCount = other.board.getFishkaCount(owner);
		int secondPlayerAfterCount = other.board.getFishkaCount(owner
				.getEnemy());

		// минимакс
		if (secondPlayerAfterCount != 0)
			return (firstPlayerAfterCount - firstPlayerBeforeCount)
					- (secondPlayerAfterCount - secondPlayerBeforeCount);
		else
			return board.getBoardSize() + 1;
	}

	public ArrayList<Move> getAllAvailableMoves(Fishka player) {
		ArrayList<Move> result = new ArrayList<Move>();

		for (int row = 0; row < board.getBoardSize(); row++) {
			for (int col = 0; col < board.getBoardSize(); col++) {
				if (!board.isEmpty(row, col)) {// если отправна€ точка непуста
												// (т.е. содержит фишку)
					for (Direction direction : Direction.values()) {// то дл€
																	// каждого
																	// из всех
																	// направлений

						// найдЄм координаты точки назначени€
						int drow = Direction.getDrow(direction);
						int dcol = Direction.getDcol(direction);
						int destinationRow = row + drow;
						int destinationCol = col + dcol;

						// если точка назначени€ пуста
						if (!board.isValidCoordinates(destinationRow,
								destinationCol))
							continue;

						if (board.isEmpty(destinationRow, destinationCol)) {
							result.add(new Move(row, col, direction, player));// то
																				// такой
																				// ход
																				// возможен
						}
					}
				}
			}
		}

		return result;
	}

	public int getRate(MoveSequence sequence, Fishka player) {
		Situation newSituation = new Situation(this);
		int profit = newSituation.applyMoves(sequence);
		return this.compareTo(newSituation, player);
		// return profit;
	}

	public Board getBoard() {
		return board;
	}

}
