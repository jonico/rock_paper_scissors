package schnickschnackschnuck;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestAbstractGamePlayer {

	public TestAbstractGamePlayer() {
		super();
	}

	public void shouldReturnPlayerDescripptionIfNotGivenAName(
			AbstractGamePlayer anonymousPlayer) {
		assertEquals(anonymousPlayer.getPlayerName(),
				anonymousPlayer.getPlayerDescription());
	}

	public void shouldReturnPlayerNameIfGivenAName(
			AbstractGamePlayer namedPlayer) {
		String playerName = "John Doe";
		namedPlayer.setPlayerName(playerName);
		assertEquals(playerName + " test", namedPlayer.getPlayerName());
	}

	@Test
	public void shouldReturnPlayerDescripptionIfNotGivenAName() {
		shouldReturnPlayerDescripptionIfNotGivenAName(new HumanGamePlayerOnKeyboard());
	}

	@Test
	public void shouldReturnPlayerNameIfGivenAName() {
		HumanGamePlayerOnKeyboard namedPlayer = new HumanGamePlayerOnKeyboard();
		String playerName = "Hugo Human";
		namedPlayer.setPlayerName(playerName);
		shouldReturnPlayerNameIfGivenAName(namedPlayer);
	}

}
