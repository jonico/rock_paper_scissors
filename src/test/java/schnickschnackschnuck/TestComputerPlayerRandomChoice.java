package schnickschnackschnuck;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestComputerPlayerRandomChoice extends TestAbstractGamePlayer {

	private ComputerPlayerRandomChoice player = new ComputerPlayerRandomChoice();

	@Test
	public void shouldReturnPlayerDescripptionIfNotGivenAName() {
		shouldReturnPlayerDescripptionIfNotGivenAName(new ComputerPlayerRandomChoice());
	}

	@Test
	public void shouldReturnPlayerNameIfGivenAName() {
		ComputerPlayerRandomChoice namedPlayer = new ComputerPlayerRandomChoice();
		String playerName = "John Doe";
		namedPlayer.setPlayerName(playerName);
		shouldReturnPlayerNameIfGivenAName(namedPlayer);
	}

	@Test
	public void noChoicesShouldResultInUnknownChoice() {
		GameChoice result = player.provideChoice(new ArrayList<GameChoice>());
		assertTrue(result instanceof UnknownChoice);
	}

	@Test
	public void providedChoiceShouldBeInstanceOfInputChoices() {
		List<GameChoice> onlyScissors = GameUtils.generateGameChoiceList(
				new Scissors(), new Scissors());
		assertTrue(player.provideChoice(onlyScissors) instanceof Scissors);

		List<GameChoice> onlyRocks = GameUtils
				.generateGameChoiceList(new Rock());
		assertTrue(player.provideChoice(onlyRocks) instanceof Rock);

		List<GameChoice> onlyPaper = GameUtils.generateGameChoiceList(
				new Paper(), new Paper(), new Paper());
		assertTrue(player.provideChoice(onlyPaper) instanceof Paper);

		List<GameChoice> onlyPapersAndRocks = GameUtils.generateGameChoiceList(
				new Paper(), new Rock(), new Paper(), new Rock());
		GameChoice providedChoice = player.provideChoice(onlyPapersAndRocks);
		assertTrue(providedChoice instanceof Paper
				|| providedChoice instanceof Rock);

		List<GameChoice> onlyScissorsAndRocks = GameUtils
				.generateGameChoiceList(new Scissors(), new Rock(),
						new Scissors(), new Rock(), new Rock());
		providedChoice = player.provideChoice(onlyScissorsAndRocks);
		assertTrue(providedChoice instanceof Scissors
				|| providedChoice instanceof Rock);

		List<GameChoice> onlyScissorsAndPaper = GameUtils
				.generateGameChoiceList(new Scissors(), new Rock(),
						new Scissors(), new Rock(), new Rock());
		providedChoice = player.provideChoice(onlyScissorsAndPaper);
		assertTrue(providedChoice instanceof Scissors
				|| providedChoice instanceof Rock);

		List<GameChoice> allChoices = GameUtils.generateGameChoiceList(
				new Scissors(), new Rock(), new Paper());
		providedChoice = player.provideChoice(allChoices);
		assertTrue(providedChoice instanceof Scissors
				|| providedChoice instanceof Rock
				|| providedChoice instanceof Paper);

	}

}
