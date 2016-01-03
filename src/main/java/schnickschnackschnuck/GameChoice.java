package schnickschnackschnuck;

public interface GameChoice {
	public String getName();

	public GameChoiceComparisonResult compareTo(GameChoice otherChoice);
}
