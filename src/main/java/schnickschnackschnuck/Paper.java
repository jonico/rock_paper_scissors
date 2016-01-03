package schnickschnackschnuck;

public class Paper implements GameChoice {

	private static final String PAPER = "Paper";

	@Override
	public GameChoiceComparisonResult compareTo(GameChoice o) {
		if (o instanceof Paper) {
			return GameChoiceComparisonResult.tie;
		} else if (o instanceof Rock) {
			return GameChoiceComparisonResult.win;
		} else if (o instanceof Scissors) {
			return GameChoiceComparisonResult.lose;
		} else {
			return GameChoiceComparisonResult.unknown;
		}
	}

	@Override
	public String getName() {
		return PAPER;
	}
}
