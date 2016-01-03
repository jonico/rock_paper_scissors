package schnickschnackschnuck;

import java.util.List;

public class MultipleGamesController {
	private List<GamePlayer> availablePlayersPlayerOne;
	private List<GamePlayer> availablePlayersPlayerTwo;
	private List<GameChoice> availableGameChoices;
	private SelectGamePlayer gamePlayerSelector;
	private AskForAnotherGame askForAnotherGame;
	private GameProgressAnnouncer announcer;
	private SingleGameController singleGameController;

	public void play() {
		announcer.announceGameSeasonStarts();
		do {
			announcer.announceSelectNewPlayers();
			GamePlayer playerOne = gamePlayerSelector.selectGamePlayer(1,
					availablePlayersPlayerOne);
			GamePlayer playerTwo = gamePlayerSelector.selectGamePlayer(2,
					availablePlayersPlayerTwo);
			announcer.announceNewGamePlayers(playerOne, playerTwo);
			playWithSamePlayers(playerOne, playerTwo);
		} while (askForAnotherGame.askForAnotherGameWithDifferentPlayers());
		announcer.announceGameSeasonEnds();
	}

	private void playWithSamePlayers(GamePlayer playerOne, GamePlayer playerTwo) {
		do {
			singleGameController.play(playerOne, playerTwo,
					availableGameChoices);
		} while (askForAnotherGame.askForAnotherGameWithSamePlayers());
	}

	/**
	 * @param availableGameChoices
	 *            the availableGameChoices to set
	 */
	public void setAvailableGameChoices(List<GameChoice> availableGameChoices) {
		this.availableGameChoices = availableGameChoices;
	}

	/**
	 * @param gamePlayerSelector
	 *            the gamePlayerSelector to set
	 */
	public void setGamePlayerSelector(SelectGamePlayer gamePlayerSelector) {
		this.gamePlayerSelector = gamePlayerSelector;
	}

	/**
	 * @param askForAnotherGame
	 *            the askForAnotherGame to set
	 */
	public void setAskForAnotherGame(AskForAnotherGame askForAnotherGame) {
		this.askForAnotherGame = askForAnotherGame;
	}

	/**
	 * @param announcer
	 *            the announcer to set
	 */
	public void setAnnouncer(GameProgressAnnouncer announcer) {
		this.announcer = announcer;
	}

	/**
	 * @param availablePlayersPlayerOne
	 *            the availablePlayersPlayerOne to set
	 */
	public void setAvailablePlayersPlayerOne(
			List<GamePlayer> availablePlayersPlayerOne) {
		this.availablePlayersPlayerOne = availablePlayersPlayerOne;
	}

	/**
	 * @param availablePlayersPlayerTwo
	 *            the availablePlayersPlayerTwo to set
	 */
	public void setAvailablePlayersPlayerTwo(
			List<GamePlayer> availablePlayersPlayerTwo) {
		this.availablePlayersPlayerTwo = availablePlayersPlayerTwo;
	}

	/**
	 * @param singleGameController
	 *            the singleGameController to set
	 */
	public void setSingleGameController(
			SingleGameController singleGameController) {
		this.singleGameController = singleGameController;
	}
}
