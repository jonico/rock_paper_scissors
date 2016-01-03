package schnickschnackschnuck;

public class Rock implements GameChoice {

	public static final String ROCK = "Rock";

	@Override
	public GameChoiceComparisonResult compareTo(GameChoice o) {
		if (o instanceof Rock) {
			return GameChoiceComparisonResult.tie;
		} else if (o instanceof Paper) {
			return GameChoiceComparisonResult.lose;
		} else if (o instanceof Scissors) {
			return GameChoiceComparisonResult.win;
		} else {
			return GameChoiceComparisonResult.unknown;
		}
	}

	@Override
	public String getName() {
		return ROCK;
	}

}
