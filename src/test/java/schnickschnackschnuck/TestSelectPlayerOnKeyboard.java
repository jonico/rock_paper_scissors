package schnickschnackschnuck;

import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

public class TestSelectPlayerOnKeyboard {

	private static final String HUMAN_PLAYER_NAME = "Human";

	private static final String NO_KEYBOARD_INPUT = "";

	private static final String KEYBOARD_INPUT_NO_NUMBER = "foobar\n";

	private static final String KEYBOARD_INPUT_TOO_LOW = "-3\n";

	private static final String KEYBOARD_INPUT_TOO_HIGH = "3\n";

	private static final String SELECTION_OUTPUT_PREFIX = "(0) Computer player that selects a random choice\n(1) Human player on keyboard\nPlease make your choice for player ";

	private static final String SELECTION_OUTPUT_SUFFIX = " by entering the number displayed in (brackets): Please provide a name for this player: ";

	private static final String KEYBOARD_INPUT_HUMAN = "1\n"
			+ HUMAN_PLAYER_NAME + "\n";

	private static final String KEYBOARD_INPUT_COMPUTER = "0\nComputer\n";

	private static final String DEFAULT_PLAYER_SELECTION_OUTPUT_PLAYER_1 = SELECTION_OUTPUT_PREFIX
			+ "1" + SELECTION_OUTPUT_SUFFIX;
	private static final String DEFAULT_PLAYER_SELECTION_OUTPUT_PLAYER_2 = SELECTION_OUTPUT_PREFIX
			+ "2" + SELECTION_OUTPUT_SUFFIX;

	@Rule
	public final StandardOutputStreamLog log = new StandardOutputStreamLog();

	@Rule
	public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

	private SelectGamePlayerOnKeyboard selectPlayer;

	@Test
	public void shouldSelectComputerPlayer() {
		initializeSelectPlayer();
		systemInMock.provideText(KEYBOARD_INPUT_COMPUTER);
		assertTrue(selectPlayer.selectGamePlayer(1, getDefaultPlayerList()) instanceof ComputerPlayerRandomChoice);
		assertEquals(DEFAULT_PLAYER_SELECTION_OUTPUT_PLAYER_1, log.getLog());
	}

	@Test
	public void shouldSelectHumanPlayer() {
		initializeSelectPlayer();
		systemInMock.provideText(KEYBOARD_INPUT_HUMAN);
		GamePlayer player = selectPlayer.selectGamePlayer(2,
				getDefaultPlayerList());
		assertTrue(player instanceof HumanGamePlayerOnKeyboard);
		assertEquals(DEFAULT_PLAYER_SELECTION_OUTPUT_PLAYER_2, log.getLog());
		assertEquals(HUMAN_PLAYER_NAME, player.getPlayerName());
	}

	@Test
	public void shouldSelectUnknownPlayerIfPlayersWereNotProvided() {
		initializeSelectPlayer();
		assertTrue(GameUtils.isUnkownPlayer(selectPlayer.selectGamePlayer(1,
				getEmptyPlayerList())));
	}

	private List<GamePlayer> getEmptyPlayerList() {
		return GameUtils.generateGamePlayerList();
	}

	@Test
	public void shouldSelectUnknownPlayerIfKeyboardInputIsTooHigh() {
		shouldSelectUnkownPlayerGivenInput(KEYBOARD_INPUT_TOO_HIGH);
	}

	@Test
	public void shouldSelectUnknownPlayerIfKeyboardInputIsTooLow() {
		shouldSelectUnkownPlayerGivenInput(KEYBOARD_INPUT_TOO_LOW);
	}

	@Test
	public void shouldSelectUnknownPlayerIfKeyboardInputIsNoNumber() {
		shouldSelectUnkownPlayerGivenInput(KEYBOARD_INPUT_NO_NUMBER);
	}

	@Test
	public void shouldSelectUnknownPlayerIfKeyboardInputIsEOF() {
		shouldSelectUnkownPlayerGivenInput(NO_KEYBOARD_INPUT);
	}

	private void shouldSelectUnkownPlayerGivenInput(String input) {
		initializeSelectPlayer();
		List<GamePlayer> players = getDefaultPlayerList();

		systemInMock.provideText(input);
		assertUnknownGamePlayer(players);
	}

	private void assertUnknownGamePlayer(List<GamePlayer> players) {
		GamePlayer result;
		result = selectPlayer.selectGamePlayer(1, players);
		assertTrue(GameUtils.isUnkownPlayer(result));
	}

	private List<GamePlayer> getDefaultPlayerList() {
		return GameUtils.generateGamePlayerList(
				new ComputerPlayerRandomChoice(),
				new HumanGamePlayerOnKeyboard());
	}

	private void initializeSelectPlayer() {
		// have to initialize here in order to use System Rules
		selectPlayer = new SelectGamePlayerOnKeyboard();
	}

}
