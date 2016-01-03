package schnickschnackschnuck;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestUnknownGamePlayer {

	private final UnknownGamePlayer unkownPlayer = new UnknownGamePlayer();

	@Test
	public void testCorrectDescription() {
		assertEquals(UnknownGamePlayer.UNKNOWN_GAME_PLAYER,
				unkownPlayer.getPlayerDescription());
	}

	@Test
	public void shouldProvideUnknownChoiceNoMatterWhat() {
		assertTrue(GameUtils.isUnkownChoice(unkownPlayer
				.provideChoice(GameUtils.emptyGameChoiceList())));
		assertFalse(GameUtils.isUnkownChoice(unkownPlayer
				.provideChoice(GameUtils.generateGameChoiceList(new Paper(),
						new Scissors()))));
	}

}
