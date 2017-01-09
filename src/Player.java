import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Player {

	static final int depth = 3;
	static final int printLimit = 5;

	static Move getMove(Board board, Fishka player) {
		ArrayList<ArrayList<MoveSequence>> tree = new ArrayList<ArrayList<MoveSequence>>();
		for (int depth = 0; depth < Player.depth; depth++) {
			tree.add(new ArrayList<MoveSequence>());
		}

		Situation initSituation = new Situation(board);
		MoveSequence initialMoves = new MoveSequence(
				initSituation.getAllAvailableMoves(player));

		// удалить повторы
		LinkedHashSet<Move> initialMovesAsHashSet = new LinkedHashSet<Move>(
				initialMoves);
		initialMoves = new MoveSequence(initialMovesAsHashSet);

		if (initialMoves.size() == 1)
			return initialMoves.get(0);

		for (Move move : initialMoves) {
			if (isGameOverMove(player, player, initSituation, move))
				return move;

			MoveSequence newSequence = new MoveSequence(move);
			tree.get(0).add(newSequence);
		}

		Fishka currentPlayer = player.getEnemy();
		for (int depth = 1; depth < Player.depth; depth++) {
			for (MoveSequence previousSequence : tree.get(depth - 1)) {
				currentPlayer = (needSwitchTurn(previousSequence)) ? player
						: player.getEnemy();
				Situation currentSituation = new Situation(board);
				currentSituation.applyMoves(previousSequence);
				ArrayList<Move> currentVariants = currentSituation
						.getAllAvailableMoves(currentPlayer);
				for (Move variant : currentVariants) {

					MoveSequence currentSequence = new MoveSequence(
							previousSequence);
					currentSequence.add(variant);
					tree.get(depth).add(currentSequence);
				}
			}
		}

		int finalVariantNumber = tree.size() - 1;
		while (tree.get(finalVariantNumber).isEmpty()) {
			finalVariantNumber--;
		}

		// Find the best move sequence
		Situation initialSituation = new Situation(board);
		int currentRate = initialSituation.getRate(tree.get(finalVariantNumber)
				.get(0), player);
		MoveSequence bestMoves = tree.get(finalVariantNumber).get(0);
		int maxRate = currentRate;
		for (MoveSequence sequence : tree.get(finalVariantNumber)) {
			currentRate = initialSituation.getRate(sequence, player);
			if (currentRate > maxRate) {
				maxRate = currentRate;
				bestMoves = sequence;
			}
		}

		System.out.println(String.format("The chosen best variant = %s\n",
				bestMoves.toString()));

		return bestMoves.get(0);
	}

	private static boolean isGameOverMove(Fishka player, Fishka currentPlayer,
			Situation currentSituation, Move variant) {

		int enemyFishkaCountAfterMoves = 0;

		Situation gameOverCheckSituation = new Situation(currentSituation);
		gameOverCheckSituation.applyMove(variant);
		enemyFishkaCountAfterMoves = gameOverCheckSituation.getBoard()
				.getFishkaCount(currentPlayer.getEnemy());
		boolean moveIsFinal = enemyFishkaCountAfterMoves == 0
				&& currentPlayer.equals(player);
		return moveIsFinal;
	}

	private static boolean needSwitchTurn(MoveSequence sequence) {
		return (sequence.size() % 2) == 0;
	}
}
