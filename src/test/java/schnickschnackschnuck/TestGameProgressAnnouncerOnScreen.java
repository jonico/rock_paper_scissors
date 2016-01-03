package schnickschnackschnuck;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestGameProgressAnnouncerOnScreen {

	private static final String EXPECTED_PLAYER_SELECETION_ANNOUNCEMENT = "Please select your players!\n\n";
	private static final String EXPECTED_CLEAR_WINNER_ANNOUNCEMENT = "After round 5, we have a clear winner: player two.\n";
	private static final String EXPECTED_NEW_SEASON_OF_GAMES_ANNOUNCEMENT = "Welcome to a new season of games!\n\n";
	private static final String EXPECTED_NEW_GAME_PLAYERS_ANNOUNCEMENT = "For player 1, you have selected: player one (description one).\nFor player 2, you have selected: player two (description two).\n";
	private static final String EXPECTED_NEW_GAME_ANNOUNCEMENT = "A new game between player one and player two begins ...\n";
	private static final String EXPECTED_GAME_SEASON_ENDS_ANNOUNCEMENT = "Game season ends,  see you next time!\n";
	private static final String EXPECTED_INTERMEDIATE_RESULT_ANNOUNCEMENT = "No clear winner in round 3 as result was tie.\n";
	private static final String EXPECTED_PLAYER_CHOICE_ANNOUNCEMENT = "Player player one chose Unknown in round 2.\n";
	private static final String EXPECTED_TIE_RESULT_ANNOUNCEMENT = "After 7 rounds we had a tie result, so single game result is tie.\n";
	private static final String EXPECTED_UNKNOWN_RESULT_ANNOUNCEMENT = "After 7 rounds we had an unknown result, so single game result is unknown.\n";
	private static final int NUMBER_OF_MAX_RETRIES_WITHOUT_CLEAR_WINNER = 7;
	private static final String PLAYER_TWO_DESCRIPTION = "description two";
	private static final String PLAYER_TWO_NAME = "player two";
	private static final String PLAYER_ONE_DESCRIPTION = "description one";
	private static final String PLAYER_ONE_NAME = "player one";
	private final GameProgressAnnouncerOnScreen announcer = new GameProgressAnnouncerOnScreen();

	@Rule
	public final StandardOutputStreamLog log = new StandardOutputStreamLog();

	@Mock
	GamePlayer playerOne;
	@Mock
	GamePlayer playerTwo;

	@Before
	public void initializeMocks() {
		when(playerOne.getPlayerName()).thenReturn(PLAYER_ONE_NAME);
		when(playerOne.getPlayerDescription()).thenReturn(
				PLAYER_ONE_DESCRIPTION);
		when(playerTwo.getPlayerName()).thenReturn(PLAYER_TWO_NAME);
		when(playerTwo.getPlayerDescription()).thenReturn(
				PLAYER_TWO_DESCRIPTION);
	}

	@Test
	public void testAnnounceUnkownAfterXRounds() {
		announcer
				.announceUnkownAfterXRounds(NUMBER_OF_MAX_RETRIES_WITHOUT_CLEAR_WINNER);
		checkOutput(EXPECTED_UNKNOWN_RESULT_ANNOUNCEMENT);
	}

	@Test
	public void testAnnounceTieAfterXRounds() {
		announcer
				.announceTieAfterXRounds(NUMBER_OF_MAX_RETRIES_WITHOUT_CLEAR_WINNER);
		checkOutput(EXPECTED_TIE_RESULT_ANNOUNCEMENT);
	}

	@Test
	public void testAnnouncePlayerChoice() {
		announcer.announcePlayerChoice(2, playerOne, new UnknownChoice());
		checkOutput(EXPECTED_PLAYER_CHOICE_ANNOUNCEMENT);
	}

	@Test
	public void testAnnounceIntermediateResult() {
		announcer.announceIntermediateResult(3, GameChoiceComparisonResult.tie);
		checkOutput(EXPECTED_INTERMEDIATE_RESULT_ANNOUNCEMENT);
	}

	@Test
	public void testAnnounceWinner() {
		announcer.announceWinner(5, playerTwo);
		checkOutput(EXPECTED_CLEAR_WINNER_ANNOUNCEMENT);
	}

	@Test
	public void testAnnounceGameSeasonStarts() {
		announcer.announceGameSeasonStarts();
		checkOutput(EXPECTED_NEW_SEASON_OF_GAMES_ANNOUNCEMENT);
	}

	@Test
	public void testAnnounceSelectNewPlayers() {
		announcer.announceSelectNewPlayers();
		checkOutput(EXPECTED_PLAYER_SELECETION_ANNOUNCEMENT);
	}

	@Test
	public void testAnnounceNewGamePlayers() {
		announcer.announceNewGamePlayers(playerOne, playerTwo);
		checkOutput(EXPECTED_NEW_GAME_PLAYERS_ANNOUNCEMENT);
	}

	@Test
	public void testAnnounceNewGame() {
		announcer.announceNewGame(playerOne, playerTwo);
		checkOutput(EXPECTED_NEW_GAME_ANNOUNCEMENT);
	}

	@Test
	public void testAnnounceGameSeasonEnds() {
		announcer.announceGameSeasonEnds();
		checkOutput(EXPECTED_GAME_SEASON_ENDS_ANNOUNCEMENT);
	}

	private void checkOutput(String expectedOutput) {
		assertEquals(expectedOutput, log.getLog());
	}

}
