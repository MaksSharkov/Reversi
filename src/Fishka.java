public enum Fishka {
	EMPTY, COMPUTER1, COMPUTER2;

	public String toString() {
		switch (this) {
		case EMPTY:
			return " ";
		case COMPUTER1:
			return "+";
		case COMPUTER2:
			return "-";
		}
		return " ";
	}

	public Fishka getEnemy() {
		switch (this) {
		case EMPTY:
			return EMPTY;
		case COMPUTER1:
			return COMPUTER2;
		case COMPUTER2:
			return COMPUTER1;
		}

		return EMPTY;
	}
}
