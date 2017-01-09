import java.util.ArrayList;
import java.util.LinkedHashSet;

public class MoveSequence extends ArrayList<Move> {

	public MoveSequence(Move move) {
		super();
		this.add(move);
	}

	public MoveSequence(MoveSequence sequence) {
		super(sequence);
	}

	public MoveSequence(ArrayList<Move> sequence) {
		super(sequence);
	}

	public MoveSequence(LinkedHashSet<Move> linkedHashSet) {
		super(linkedHashSet);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2452977553766484071L;
}
