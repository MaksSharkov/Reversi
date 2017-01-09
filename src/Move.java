public class Move {
	private int row;
	private int col;
	private Direction direction;
	private Fishka owner;

	public Move(int row, int col, Direction direction, Fishka owner) {
		super();
		this.row = row;
		this.col = col;
		this.direction = direction;
		this.owner = owner;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public Direction getDirection() {
		return direction;
	}

	public Fishka getOwner() {
		return owner;
	}

	public String toString() {
		return String.format("[ %d-%d | %s]", row, col, direction.toString());
	}

	public int getDestinationRow() {
		int drow = Direction.getDrow(direction);
		return row + drow;
	}

	public int getDestinationCol() {
		int dcol = Direction.getDcol(direction);
		return col + dcol;
	}

	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Move)) {
			return false;
		}
		Move other = (Move) obj;
		return this.hashCode() == (other.hashCode());
	}

	public int hashCode() {
		int destRow = getDestinationRow();
		int destCol = getDestinationCol();

		String hashString = String.format("%d%d", destCol, destRow);

		return hashString.hashCode();
	}
}
