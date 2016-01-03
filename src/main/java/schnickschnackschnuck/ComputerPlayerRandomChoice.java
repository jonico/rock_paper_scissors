package schnickschnackschnuck;

import java.util.List;
import java.util.Random;

public class ComputerPlayerRandomChoice extends AbstractGamePlayer {

	private Random randomizer = new Random();

	public static final String COMPUTER_PLAYER_RANDOM_CHOICE_DESCRIPTION = "Computer player that selects a random choice";

	@Override
	public GameChoice provideChoice(List<GameChoice> choices) {
		if (choices.isEmpty()) {
			return new UnknownChoice();
		} else {
			return choices.get(randomizer.nextInt(choices.size()));
		}
	}

	@Override
	public String getPlayerDescription() {
		return COMPUTER_PLAYER_RANDOM_CHOICE_DESCRIPTION;
	}

}
