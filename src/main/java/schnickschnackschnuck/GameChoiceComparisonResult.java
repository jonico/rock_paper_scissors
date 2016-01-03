package schnickschnackschnuck;

public enum GameChoiceComparisonResult {
	win, lose, tie, unknown;

	public GameChoiceComparisonResult negate() {
		switch (this) {
		case win:
			return lose;
		case lose:
			return win;
		case tie:
			return tie;
		case unknown:
			return unknown;
		default:
			return unknown;
		}
	}
}
