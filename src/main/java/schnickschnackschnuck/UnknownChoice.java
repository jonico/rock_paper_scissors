package schnickschnackschnuck;

public class UnknownChoice implements GameChoice {

	public static final String UNKNOWN = "Unknown";

	@Override
	public String getName() {
		return UNKNOWN;
	}

	@Override
	public GameChoiceComparisonResult compareTo(GameChoice otherChoice) {
		return GameChoiceComparisonResult.unknown;
	}

}
