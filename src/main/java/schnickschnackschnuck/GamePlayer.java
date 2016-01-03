package schnickschnackschnuck;

import java.util.List;

public interface GamePlayer {
	public GameChoice provideChoice(List<GameChoice> choices);

	public void receiveFeedback(GameChoice yourChoice,
			GameChoice opponentChoice, GameChoiceComparisonResult result);

	public String getPlayerDescription();

	public String getPlayerName();

	public void setPlayerName(String playerName);
}
