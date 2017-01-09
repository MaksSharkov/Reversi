public class Main {

	static final int maxSimulationCount = 61;// 8*8+1

	public static void main(String[] args) {
		Game game = new Game(8);
		System.out.println(game.getBoard().toString());

		boolean gameIsOver = false;
		for (int simulateNumber = 0; simulateNumber < maxSimulationCount
				&& !gameIsOver; simulateNumber++) {
			gameIsOver = !game.doMove();
			System.out.println("NEXT MOVE:");
			System.out.println(game.getBoard().toString());
			System.out.println(String.format("Current MOVE: %d",
					simulateNumber + 1));
			System.out.println("========================");
		}

		System.out.println(String.format("Winner : %s", game.getWinner()
				.toString()));
		System.out.println(game.getFishkiCount());
	}

}
