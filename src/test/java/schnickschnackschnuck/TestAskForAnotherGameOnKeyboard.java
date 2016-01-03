package schnickschnackschnuck;

import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

public class TestAskForAnotherGameOnKeyboard {

	private static final String KEYBOARD_NO_INPUT = "";

	private static final String KEYBOARD_BOGUS_CHOICE = "hadjadjhadj\n";

	private static final String KEYBOARD_YES_CHOICE = "y\n";

	private static final String KEYBOARD_NO_CHOICE = "n\n";

	@Rule
	public final StandardOutputStreamLog log = new StandardOutputStreamLog();

	@Rule
	public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

	@Test
	public void yesShouldImplyAnotherGameWithSamePlayers() {
		provideYesInput();
		AskForAnotherGameOnKeyboard askForAnotherGame = initializeAskForAnotherGameKeyboardObject();
		assertTrue(askForAnotherGame.askForAnotherGameWithSamePlayers());
		assertSameGameQuestionOutput();
	}

	private void assertSameGameQuestionOutput() {
		assertEquals(AskForAnotherGameOnKeyboard.DO_YOU_LIKE_ANOTHER_GAME_Y_N,
				log.getLog());
	}

	@Test
	public void yesShouldImplyAnotherGameWithDifferentPlayers() {
		provideYesInput();
		AskForAnotherGameOnKeyboard askForAnotherGame = initializeAskForAnotherGameKeyboardObject();
		assertTrue(askForAnotherGame.askForAnotherGameWithDifferentPlayers());
		assertDifferentGameQuestionOutput();
	}

	@Test
	public void noShouldImplyNoOtherGameWithSamePlayers() {
		provideNoInout();
		AskForAnotherGameOnKeyboard askForAnotherGame = initializeAskForAnotherGameKeyboardObject();
		assertFalse(askForAnotherGame.askForAnotherGameWithSamePlayers());
		assertSameGameQuestionOutput();
	}

	@Test
	public void unrecognizedChoiceShouldImplyNoOtherGameWithDifferentPlayers() {
		provideBogusInput();
		AskForAnotherGameOnKeyboard askForAnotherGame = initializeAskForAnotherGameKeyboardObject();
		assertFalse(askForAnotherGame.askForAnotherGameWithDifferentPlayers());
		assertDifferentGameQuestionOutput();
	}

	@Test
	public void noShouldImplyNoOtherGameWithDifferentPlayers() {
		provideNoInout();
		AskForAnotherGameOnKeyboard askForAnotherGame = initializeAskForAnotherGameKeyboardObject();
		assertFalse(askForAnotherGame.askForAnotherGameWithDifferentPlayers());
		assertDifferentGameQuestionOutput();
	}

	@Test
	public void unrecognizedChoiceShouldImplyNoOtherGameWithSamePlayers() {
		provideBogusInput();
		AskForAnotherGameOnKeyboard askForAnotherGame = initializeAskForAnotherGameKeyboardObject();
		assertFalse(askForAnotherGame.askForAnotherGameWithSamePlayers());
	}

	@Test
	public void eofShouldImplyNoOtherGameWithSamePlayers() {
		provideNoInput();
		AskForAnotherGameOnKeyboard askForAnotherGame = initializeAskForAnotherGameKeyboardObject();
		assertFalse(askForAnotherGame.askForAnotherGameWithSamePlayers());
	}

	private void provideNoInput() {
		systemInMock.provideText(KEYBOARD_NO_INPUT);
	}

	private AskForAnotherGameOnKeyboard initializeAskForAnotherGameKeyboardObject() {
		AskForAnotherGameOnKeyboard askForAnotherGame = new AskForAnotherGameOnKeyboard();
		return askForAnotherGame;
	}

	private void assertDifferentGameQuestionOutput() {
		assertEquals(
				AskForAnotherGameOnKeyboard.DO_YOU_LIKE_ANOTHER_GAME_DIFFERENT_Y_N,
				log.getLog());
	}

	private void provideNoInout() {
		systemInMock.provideText(KEYBOARD_NO_CHOICE);
	}

	private void provideYesInput() {
		systemInMock.provideText(KEYBOARD_YES_CHOICE);
	}

	private void provideBogusInput() {
		systemInMock.provideText(KEYBOARD_BOGUS_CHOICE);
	}

}
