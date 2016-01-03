package schnickschnackschnuck;

public class Scissors implements GameChoice {

	private static final String SCISSORS = "Scissors";

	@Override
	public GameChoiceComparisonResult compareTo(GameChoice o) {
		if (o instanceof Scissors) {
			return GameChoiceComparisonResult.tie;
		} else if (o instanceof Paper) {
			return GameChoiceComparisonResult.win;
		} else if (o instanceof Rock) {
			return GameChoiceComparisonResult.lose;
		} else {
			return GameChoiceComparisonResult.unknown;
		}
	}

	@Override
	public String getName() {
		return SCISSORS;
	}

}
