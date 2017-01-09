import java.util.ArrayList;
import java.util.List;

public class Board {
	public Board(int boardSize) {
		board = new Fishka[boardSize][boardSize];
		for (int row = 0; row < boardSize; row++)
			for (int col = 0; col < boardSize; col++)
				board[row][col] = Fishka.EMPTY;

		this.boardSize = boardSize;
	}

	public Board() {
		this(8);
	}

	public Board(Board board) {
		this(board.boardSize);
		for (int row = 0; row < boardSize; row++)
			for (int col = 0; col < boardSize; col++)
				this.board[row][col] = board.board[row][col];
	}

	private Fishka[][] board;
	private int boardSize;

	public Fishka[][] getBoard() {
		return board;
	}

	void initialize() {
		// board[3][3] = Fishka.COMPUTER1;
		// board[3][4] = Fishka.COMPUTER2;
		// board[4][3] = Fishka.COMPUTER2;
		// board[4][4] = Fishka.COMPUTER1;

		board[3][3] = Fishka.COMPUTER1;
		board[3][4] = Fishka.COMPUTER2;
		board[4][3] = Fishka.COMPUTER2;
		board[4][4] = Fishka.COMPUTER1;
	}

	public String toString() {
		String result = "";
		for (int row = 0; row < boardSize; row++) {
			result += '|';
			for (int col = 0; col < boardSize; col++)
				result += board[row][col].toString() + '|';

			result += '\n';
		}

		return result;
	}

	public boolean isEmpty(int row, int col) {
		assert (isValidCoordinates(row, col));
		return board[row][col] == Fishka.EMPTY;
	}

	public boolean belongsTo(int row, int col, Fishka owner) {
		if (isValidCoordinates(row, col))
			return board[row][col] == owner;
		else
			return false;
	}

	public boolean hasEmptyCells() {
		boolean result = false;
		for (int row = 0; (row < boardSize) && !result; row++)
			for (int col = 0; col < boardSize; col++)
				if (isEmpty(row, col)) {
					result = true;
					break;
				}

		return result;
	}

	public List<Direction> getAvailableDirections(int row, int col) {
		if (!isValidCoordinates(row, col))
			return null;

		ArrayList<Direction> result = new ArrayList<Direction>();
		if (isValidCoordinates(row + 1, col)) {
			result.add(Direction.DOWN);
			if (isValidCoordinates(row + 1, col + 1))
				result.add(Direction.DOWN_RIGHT);
			if (isValidCoordinates(row + 1, col - 1))
				result.add(Direction.DOWN_LEFT);
		}
		if (isValidCoordinates(row - 1, col)) {
			result.add(Direction.UP);
			if (isValidCoordinates(row - 1, col + 1))
				result.add(Direction.UP_RIGHT);
			if (isValidCoordinates(row - 1, col - 1))
				result.add(Direction.UP_LEFT);
		}
		if (isValidCoordinates(row, col + 1)) {
			result.add(Direction.RIGHT);
		}
		if (isValidCoordinates(row, col - 1)) {
			result.add(Direction.LEFT);
		}

		return result;
	}

	public Fishka getOwner(int row, int col) {
		if (isValidCoordinates(row, col))
			return board[row][col];
		else
			return Fishka.EMPTY;
	}

	public int putFishka(int row, int col, Fishka owner) {
		if (!isValidCoordinates(row, col))
			return 0;

		assert (this.isValidCoordinates(row, col));

		board[row][col] = owner;

		int result = 0;
		result += paint(row, col, owner, Direction.UP_LEFT);
		result += paint(row, col, owner, Direction.UP);
		result += paint(row, col, owner, Direction.UP_RIGHT);

		result += paint(row, col, owner, Direction.RIGHT);

		result += paint(row, col, owner, Direction.DOWN_RIGHT);
		result += paint(row, col, owner, Direction.DOWN);
		result += paint(row, col, owner, Direction.DOWN_LEFT);

		result += paint(row, col, owner, Direction.LEFT);

		return result;
	}

	private int paint(int row, int col, Fishka owner, Direction direction) {
		int drow = Direction.getDrow(direction);
		int dcol = Direction.getDcol(direction);

		boolean needPaint = false;

		// check if paint is needed
		int subRow = row + drow;
		int subCol = col + dcol;
		while (isValidCoordinates(subRow, subCol) && !isEmpty(subRow, subCol)
				&& !needPaint) {
			if (!isEmpty(subRow, subCol)) {
				needPaint = (board[subRow][subCol] == owner);
			} else {
				needPaint = false;
			}
			subRow += drow;
			subCol += dcol;
		}

		// paint
		int paintedCount = 0;
		if (needPaint) {
			subRow = row + drow;
			subCol = col + dcol;
			while (board[subRow][subCol] != owner) {
				if (board[subRow][subCol] != owner) {
					board[subRow][subCol] = owner;
					paintedCount++;
				}

				subRow += drow;
				subCol += dcol;
			}
		}

		return paintedCount;
	}

	public boolean isValidCoordinates(int row, int col) {
		return !((row >= boardSize) || (col >= boardSize) || (row < 0) || (col < 0));
	}

	public int getFishkaCount(Fishka owner) {
		int result = 0;
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				if (board[row][col] == owner)
					result++;
			}
		}

		return result;
	}

	public int getBoardSize() {
		return boardSize;
	}

	public void fillWithPlussesExceptBottomRight() {
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				board[row][col] = Fishka.COMPUTER1;
			}
		}

		board[7][7] = Fishka.EMPTY;
	}
}
