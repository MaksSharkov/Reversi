public enum Direction {
	NULL, UP_LEFT, UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT;

	public static int getDcol(Direction direction) {
		int dcol = 0;
		if ((direction == Direction.UP_LEFT) || (direction == Direction.LEFT)
				|| (direction == Direction.DOWN_LEFT))
			dcol = -1;

		if ((direction == Direction.UP_RIGHT) || (direction == Direction.RIGHT)
				|| (direction == Direction.DOWN_RIGHT))
			dcol = 1;
		return dcol;
	}

	public static int getDrow(Direction direction) {
		int drow = 0;
		if ((direction == Direction.UP_LEFT) || (direction == Direction.UP)
				|| (direction == Direction.UP_RIGHT))
			drow = -1;

		if ((direction == Direction.DOWN_LEFT) || (direction == Direction.DOWN)
				|| (direction == Direction.DOWN_RIGHT))
			drow = 1;
		return drow;
	}

}
