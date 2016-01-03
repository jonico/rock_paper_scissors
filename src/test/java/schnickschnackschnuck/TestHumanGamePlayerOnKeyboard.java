package schnickschnackschnuck;

import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

public class TestHumanGamePlayerOnKeyboard {

	private static final String KEYBOARD_INPUT_TOO_HIGH = "7\n";

	private static final String KEYBOARD_INPUT_TOO_LOW = "-1\n";

	private static final String KEYBOARD_INPUT_NO_NUMBER = "foobar\n";

	private static final String KEYBOARD_INPUT_EOF = "";

	private static final String KEYBOARD_INPUT_FOR_PAPER = "1\n";

	private static final String HUMAN_PLAYER_NAME = "human";

	private static final String ROCK_PAPER_SCISSORS_CHOICE_ON_SCREEN = "(0) Rock\n(1) Paper\n(2) Scissors\n"
			+ HUMAN_PLAYER_NAME
			+ ", please make your choice by entering the number displayed in (brackets): ";

	@Rule
	public final StandardOutputStreamLog log = new StandardOutputStreamLog();

	@Rule
	public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

	@Test
	public void noChoicesShouldResultInUnknownChoice() {
		HumanGamePlayerOnKeyboard player = initializePlayer();
		GameChoice result = player.provideChoice(new ArrayList<GameChoice>());
		assertTrue(result instanceof UnknownChoice);
	}

	@Test
	public void shouldPresentChoicesOnConsoleAndAskForChoice() {
		systemInMock.provideText(KEYBOARD_INPUT_FOR_PAPER);
		HumanGamePlayerOnKeyboard player = initializePlayer();
		GameChoice result = provideDefaultChoices(player);

		assertTrue(result instanceof Paper);
		assertEquals(ROCK_PAPER_SCISSORS_CHOICE_ON_SCREEN, log.getLog());
	}

	// we have to initialize player inside the test to have the
	// System.in/System.out mocking work
	private HumanGamePlayerOnKeyboard initializePlayer() {
		HumanGamePlayerOnKeyboard player = new HumanGamePlayerOnKeyboard();
		player.setPlayerName(HUMAN_PLAYER_NAME);
		return player;
	}

	@Test
	public void tooHighNumberShouldResultInUnknownChoice() {
		wrongKeyBoardInputShouldResultInUnkownChoice(KEYBOARD_INPUT_TOO_HIGH);
	}
	
	@Test
	public void tooLowNumberShouldResultInUnknownChoice() {
		wrongKeyBoardInputShouldResultInUnkownChoice(KEYBOARD_INPUT_TOO_LOW);
	}
	
	@Test
	public void noNumberShouldResultInUnknownChoice() {
		wrongKeyBoardInputShouldResultInUnkownChoice(KEYBOARD_INPUT_NO_NUMBER);
	}
	
	@Test
	public void eofShouldResultInUnknownChoice() {
		wrongKeyBoardInputShouldResultInUnkownChoice(KEYBOARD_INPUT_EOF);
	}

	private void wrongKeyBoardInputShouldResultInUnkownChoice(String wrongInput) {
		systemInMock.provideText(wrongInput);
		HumanGamePlayerOnKeyboard player = initializePlayer();
		GameChoice result = provideDefaultChoices(player);
		assertTrue(result instanceof UnknownChoice);
	}

	private GameChoice provideDefaultChoices(HumanGamePlayerOnKeyboard player) {
		return player.provideChoice(GameUtils.defaultGameChoices());
	}

}
