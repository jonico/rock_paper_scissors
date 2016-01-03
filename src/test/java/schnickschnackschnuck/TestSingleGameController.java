package schnickschnackschnuck;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TestSingleGameController {

	private static final int MAX_NUMBER_OF_ROUNDS_WITHOUT_CLEAR_WINNER = 10;
	private SingleGameController singleGameController;

	@Mock
	private GamePlayer alwaysFirstChoice;
	@Mock
	private GamePlayer alwaysLastChoice;
	@Mock
	private GamePlayer alwaysScissors;
	@Mock
	private GamePlayer alwaysRock;
	@Mock
	private GamePlayer alwaysPaper;
	@Mock
	private GamePlayer nineTimesScissorsThenRock;
	@Mock
	private GamePlayer nineTimesScissorsThenPaper;
	@Mock
	private GamePlayer tenTimesScissorsThenRock;
	@Mock
	private GamePlayer tenTimesScissorsThenPaper;
	@Mock
	private GameProgressAnnouncer announcer;

	private final List<GameChoice> defaultGameChoices = GameUtils
			.defaultGameChoices();
	private final List<GameChoice> defaultGameChoicesReverseOrder = GameUtils
			.defaultGameChoicesReverseOrder();
	private final Rock rock = new Rock();
	private final Scissors scissors = new Scissors();
	private final Paper paper = new Paper();

	@Before
	public void initializeMocks() {

		singleGameController = new SingleGameController(
				MAX_NUMBER_OF_ROUNDS_WITHOUT_CLEAR_WINNER, announcer);

		when(alwaysFirstChoice.provideChoice(anyListOf(GameChoice.class)))
				.thenAnswer(new Answer<GameChoice>() {
					public GameChoice answer(InvocationOnMock invocation) {
						@SuppressWarnings("unchecked")
						List<GameChoice> choices = (List<GameChoice>) (invocation
								.getArguments()[0]);
						return choices.isEmpty() ? new UnknownChoice()
								: choices.get(0);
					}
				});

		when(alwaysLastChoice.provideChoice(anyListOf(GameChoice.class)))
				.thenAnswer(new Answer<GameChoice>() {
					public GameChoice answer(InvocationOnMock invocation) {
						@SuppressWarnings("unchecked")
						List<GameChoice> choices = (List<GameChoice>) (invocation
								.getArguments()[0]);
						return choices.isEmpty() ? new UnknownChoice()
								: choices.get(choices.size() - 1);
					}
				});

		when(alwaysRock.provideChoice(anyListOf(GameChoice.class))).thenReturn(
				rock);

		when(alwaysScissors.provideChoice(anyListOf(GameChoice.class)))
				.thenReturn(scissors);

		when(alwaysPaper.provideChoice(anyListOf(GameChoice.class)))
				.thenReturn(paper);

		when(
				nineTimesScissorsThenPaper
						.provideChoice(anyListOf(GameChoice.class)))
				.thenReturn(scissors, scissors, scissors, scissors, scissors,
						scissors, scissors, scissors, scissors, paper);

		when(
				nineTimesScissorsThenRock
						.provideChoice(anyListOf(GameChoice.class)))
				.thenReturn(scissors, scissors, scissors, scissors, scissors,
						scissors, scissors, scissors, scissors, rock);

		when(
				tenTimesScissorsThenPaper
						.provideChoice(anyListOf(GameChoice.class)))
				.thenReturn(scissors, scissors, scissors, scissors, scissors,
						scissors, scissors, scissors, scissors, scissors, paper);

		when(
				tenTimesScissorsThenRock
						.provideChoice(anyListOf(GameChoice.class)))
				.thenReturn(scissors, scissors, scissors, scissors, scissors,
						scissors, scissors, scissors, scissors, scissors, rock);
	}

	@Test
	public void firstPlayerShouldWin() {
		firstPlayerShouldWin(alwaysFirstChoice, alwaysLastChoice,
				defaultGameChoices);
		verify(alwaysFirstChoice).receiveFeedback(defaultGameChoices.get(0),
				defaultGameChoices.get(2), GameChoiceComparisonResult.win);
		verify(alwaysLastChoice).receiveFeedback(defaultGameChoices.get(2),
				defaultGameChoices.get(0), GameChoiceComparisonResult.lose);

		firstPlayerShouldWin(alwaysLastChoice, alwaysFirstChoice,
				defaultGameChoicesReverseOrder);
		verify(alwaysFirstChoice).receiveFeedback(
				defaultGameChoicesReverseOrder.get(0),
				defaultGameChoicesReverseOrder.get(2),
				GameChoiceComparisonResult.lose);
		verify(alwaysLastChoice).receiveFeedback(
				defaultGameChoicesReverseOrder.get(2),
				defaultGameChoicesReverseOrder.get(0),
				GameChoiceComparisonResult.win);

		firstPlayerShouldWin(alwaysRock, alwaysScissors, defaultGameChoices);
		verify(alwaysRock).receiveFeedback(rock, scissors,
				GameChoiceComparisonResult.win);
		verify(alwaysScissors).receiveFeedback(scissors, rock,
				GameChoiceComparisonResult.lose);

		firstPlayerShouldWin(alwaysScissors, alwaysPaper, defaultGameChoices);
		firstPlayerShouldWin(alwaysPaper, alwaysRock, defaultGameChoices);
	}

	@Test
	public void secondPlayerShouldWin() {
		secondPlayerShouldWin(alwaysFirstChoice, alwaysLastChoice,
				defaultGameChoicesReverseOrder);
		verify(alwaysFirstChoice).receiveFeedback(
				defaultGameChoicesReverseOrder.get(0),
				defaultGameChoicesReverseOrder.get(2),
				GameChoiceComparisonResult.lose);
		verify(alwaysLastChoice).receiveFeedback(
				defaultGameChoicesReverseOrder.get(2),
				defaultGameChoicesReverseOrder.get(0),
				GameChoiceComparisonResult.win);
		verify(announcer).announceNewGame(alwaysFirstChoice, alwaysLastChoice);
		verify(announcer).announceWinner(1, alwaysLastChoice);
		verify(announcer).announcePlayerChoice(1, alwaysFirstChoice,
				defaultGameChoicesReverseOrder.get(0));
		verify(announcer).announcePlayerChoice(1, alwaysLastChoice,
				defaultGameChoicesReverseOrder.get(2));
		verify(announcer, never()).announceIntermediateResult(anyInt(),
				any(GameChoiceComparisonResult.class));

		secondPlayerShouldWin(alwaysScissors, alwaysRock, defaultGameChoices);
		verify(alwaysRock).receiveFeedback(rock, scissors,
				GameChoiceComparisonResult.win);
		verify(alwaysScissors).receiveFeedback(scissors, rock,
				GameChoiceComparisonResult.lose);

		secondPlayerShouldWin(nineTimesScissorsThenRock,
				nineTimesScissorsThenPaper, defaultGameChoices);

		InOrder inOrder = inOrder(nineTimesScissorsThenPaper);
		inOrder.verify(nineTimesScissorsThenPaper, times(9)).receiveFeedback(
				scissors, scissors, GameChoiceComparisonResult.tie);
		inOrder.verify(nineTimesScissorsThenPaper).receiveFeedback(paper, rock,
				GameChoiceComparisonResult.win);
	}

	@Test
	public void TieAfter10Rounds() {
		tie(alwaysFirstChoice, alwaysFirstChoice, defaultGameChoices);
		verify(alwaysFirstChoice, times(20)).receiveFeedback(
				defaultGameChoices.get(0), defaultGameChoices.get(0),
				GameChoiceComparisonResult.tie);

		verify(announcer).announceNewGame(alwaysFirstChoice, alwaysFirstChoice);
		verify(announcer, never()).announceWinner(anyInt(),
				any(GamePlayer.class));
		verify(announcer, times(2)).announcePlayerChoice(1, alwaysFirstChoice,
				defaultGameChoices.get(0));
		verify(announcer, times(10)).announceIntermediateResult(anyInt(),
				eq(GameChoiceComparisonResult.tie));
		verify(announcer).announceTieAfterXRounds(
				MAX_NUMBER_OF_ROUNDS_WITHOUT_CLEAR_WINNER);

		tie(alwaysFirstChoice, alwaysRock, defaultGameChoices);
		verify(alwaysFirstChoice, times(10))
				.receiveFeedback(defaultGameChoices.get(0), rock,
						GameChoiceComparisonResult.tie);

		verify(alwaysRock, times(10)).receiveFeedback(rock,
				defaultGameChoices.get(0), GameChoiceComparisonResult.tie);

		tie(tenTimesScissorsThenPaper, tenTimesScissorsThenRock,
				defaultGameChoices);
		verify(tenTimesScissorsThenPaper, times(10)).receiveFeedback(scissors,
				scissors, GameChoiceComparisonResult.tie);
		verify(tenTimesScissorsThenPaper, never()).receiveFeedback(paper, rock,
				GameChoiceComparisonResult.tie);
		verify(tenTimesScissorsThenRock, times(10)).receiveFeedback(scissors,
				scissors, GameChoiceComparisonResult.tie);
		verify(tenTimesScissorsThenRock, never()).receiveFeedback(rock, paper,
				GameChoiceComparisonResult.tie);

	}

	@Test
	public void UnknownAfter10Rounds() {
		unknown(alwaysFirstChoice, alwaysLastChoice,
				GameUtils.emptyGameChoiceList());
		verify(announcer).announceNewGame(alwaysFirstChoice, alwaysLastChoice);
		verify(announcer, never()).announceWinner(anyInt(),
				any(GamePlayer.class));
		verify(announcer, never()).announceTieAfterXRounds(anyInt());
		verify(announcer).announceUnkownAfterXRounds(
				MAX_NUMBER_OF_ROUNDS_WITHOUT_CLEAR_WINNER);
	}

	private void unknown(GamePlayer playerOne, GamePlayer playerTwo,
			List<GameChoice> gameChoices) {
		assertEquals(SingleGameResult.unknown,
				singleGameController.play(playerOne, playerTwo, gameChoices));
	}

	private void firstPlayerShouldWin(GamePlayer playerOne,
			GamePlayer playerTwo, List<GameChoice> gameChoices) {
		assertEquals(SingleGameResult.playerOneWon,
				singleGameController.play(playerOne, playerTwo, gameChoices));
	}

	private void tie(GamePlayer playerOne, GamePlayer playerTwo,
			List<GameChoice> gameChoices) {
		assertEquals(SingleGameResult.tie,
				singleGameController.play(playerOne, playerTwo, gameChoices));
	}

	private void secondPlayerShouldWin(GamePlayer playerOne,
			GamePlayer playerTwo, List<GameChoice> gameChoices) {
		assertEquals(SingleGameResult.playerTwoWon,
				singleGameController.play(playerOne, playerTwo, gameChoices));
	}
}
