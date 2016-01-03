package schnickschnackschnuck;

public abstract class AbstractGamePlayer implements GamePlayer {

	private String playerName;

	public AbstractGamePlayer() {
		super();
	}

	@Override
	public void receiveFeedback(GameChoice yourChoice,
			GameChoice opponentChoice, GameChoiceComparisonResult result) {
		// by default, just ignore the feedback
	}

	@Override
	public String getPlayerName() {
		return playerName == null ? getPlayerDescription() : playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

}