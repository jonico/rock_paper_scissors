package schnickschnackschnuck;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestMultipleGameController {

	@InjectMocks
	MultipleGamesController multiGameController;

	@Mock
	GameProgressAnnouncer announcer;
	@Mock
	List<GameChoice> availableGameChoices;
	@Mock
	List<GamePlayer> availablePlayersPlayerOne;
	@Mock
	List<GamePlayer> availablePlayersPlayerTwo;
	@Mock
	SelectGamePlayer gamePlayerSelector;
	@Mock
	AskForAnotherGame askForAnotherGame;
	@Mock
	GamePlayer playerOne;
	@Mock
	GamePlayer playerTwo;
	@Mock
	SingleGameController singleGameController;

	@Before
	public void intializeMocks() {
		when(playerOne.provideChoice(anyListOf(GameChoice.class))).thenReturn(
				new Scissors());
		when(playerOne.provideChoice(anyListOf(GameChoice.class))).thenReturn(
				new Paper());
	}

	@Test
	public void testTheMainFLowWithOneGame() {
		when(
				gamePlayerSelector.selectGamePlayer(eq(1),
						anyListOf(GamePlayer.class))).thenReturn(playerOne);
		when(
				gamePlayerSelector.selectGamePlayer(eq(2),
						anyListOf(GamePlayer.class))).thenReturn(playerTwo);
		multiGameController.play();
		InOrder inOrder = inOrder(announcer, askForAnotherGame,
				gamePlayerSelector, singleGameController);
		inOrder.verify(announcer).announceGameSeasonStarts();
		inOrder.verify(announcer).announceSelectNewPlayers();
		inOrder.verify(gamePlayerSelector).selectGamePlayer(1,
				availablePlayersPlayerOne);
		inOrder.verify(gamePlayerSelector).selectGamePlayer(2,
				availablePlayersPlayerTwo);
		inOrder.verify(announcer).announceNewGamePlayers(playerOne, playerTwo);
		inOrder.verify(singleGameController).play(playerOne, playerTwo,
				availableGameChoices);
		inOrder.verify(askForAnotherGame).askForAnotherGameWithSamePlayers();
		inOrder.verify(askForAnotherGame)
				.askForAnotherGameWithDifferentPlayers();
		inOrder.verify(announcer).announceGameSeasonEnds();
	}

	@Test
	public void testTheMainFLowWithMultipleGames() {
		when(
				gamePlayerSelector.selectGamePlayer(eq(1),
						anyListOf(GamePlayer.class))).thenReturn(playerOne);
		when(
				gamePlayerSelector.selectGamePlayer(eq(2),
						anyListOf(GamePlayer.class))).thenReturn(playerTwo);
		when(askForAnotherGame.askForAnotherGameWithSamePlayers()).thenReturn(
				true, true, false);
		multiGameController.play();
		verify(singleGameController, times(3)).play(playerOne, playerTwo,
				availableGameChoices);
		verify(gamePlayerSelector).selectGamePlayer(eq(1),
				anyListOf(GamePlayer.class));
		verify(gamePlayerSelector).selectGamePlayer(eq(2),
				anyListOf(GamePlayer.class));
	}

	@Test
	public void testTheMainFLowWithMultipleGamesAndPlayers() {
		when(
				gamePlayerSelector.selectGamePlayer(eq(1),
						anyListOf(GamePlayer.class))).thenReturn(playerOne,
				playerTwo, playerOne);
		when(
				gamePlayerSelector.selectGamePlayer(eq(2),
						anyListOf(GamePlayer.class))).thenReturn(playerTwo,
				playerOne, playerTwo);
		when(askForAnotherGame.askForAnotherGameWithSamePlayers()).thenReturn(
				true, true, false, true, false);
		when(askForAnotherGame.askForAnotherGameWithDifferentPlayers())
				.thenReturn(true, true, false);
		multiGameController.play();
		verify(singleGameController, times(4)).play(playerOne, playerTwo,
				availableGameChoices);
		verify(singleGameController, times(2)).play(playerTwo, playerOne,
				availableGameChoices);
		verify(gamePlayerSelector, times(3)).selectGamePlayer(eq(1),
				anyListOf(GamePlayer.class));
		verify(gamePlayerSelector, times(3)).selectGamePlayer(eq(2),
				anyListOf(GamePlayer.class));
	}
}
