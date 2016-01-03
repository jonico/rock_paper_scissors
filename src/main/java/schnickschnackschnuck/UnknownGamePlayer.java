package schnickschnackschnuck;

import java.util.List;

public class UnknownGamePlayer extends AbstractGamePlayer {

	static final String UNKNOWN_GAME_PLAYER = "Unknown Game Player";

	@Override
	public GameChoice provideChoice(List<GameChoice> choices) {
		return new UnknownChoice();
	}

	@Override
	public String getPlayerDescription() {
		return UNKNOWN_GAME_PLAYER;
	}

}
