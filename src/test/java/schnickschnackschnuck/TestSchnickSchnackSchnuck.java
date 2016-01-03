package schnickschnackschnuck;

import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

public class TestSchnickSchnackSchnuck {

	private static final String GAME_BEGINS = "A new game between foo and bar begins ...";

	private static final String PROLOG = "Welcome to a new season of games!\n\n"
			+ "Please select your players!\n\n"
			+ "(0) Computer player that selects a random choice\n"
			+ "(1) Human player on keyboard\n"
			+ "Please make your choice for player 1 by entering the number displayed in (brackets): "
			+ "Please provide a name for this player: "
			+ "(0) Computer player that selects a random choice\n"
			+ "(1) Human player on keyboard\n"
			+ "Please make your choice for player 2 by entering the number displayed in (brackets): "
			+ "Please provide a name for this player: "
			+ "For player 1, you have selected: foo (Computer player that selects a random choice).\n"
			+ "For player 2, you have selected: bar (Computer player that selects a random choice).\n";

	private static final Object EPILOG = "Do you like another game with the same players (y/n): "
			+ "Do you like another game with different players (y/n): "
			+ "Game season ends,  see you next time!\n";

	@Rule
	public final StandardOutputStreamLog log = new StandardOutputStreamLog();

	@Rule
	public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

	@Test
	public void testMainWithTwoComputerPlayersInOneGame() {
		systemInMock.provideText("0\nfoo\n0\nbar\n");
		SchnickSchnackSchnuck.main(null);
		String consoleOutput = log.getLog();
		int middleMarker = consoleOutput.indexOf(GAME_BEGINS);
		String prolog = consoleOutput.substring(0, middleMarker);
		int epilogMarker = consoleOutput
				.indexOf(AskForAnotherGameOnKeyboard.DO_YOU_LIKE_ANOTHER_GAME_Y_N);
		String epilog = consoleOutput.substring(epilogMarker,
				consoleOutput.length());
		// as two computers are playing each other with random algorithms, we do
		// not compare the middle piece
		assertEquals(PROLOG, prolog);
		assertEquals(EPILOG, epilog);
	}

}
