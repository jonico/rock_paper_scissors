package schnickschnackschnuck;

import java.util.List;

public class SchnickSchnackSchnuck {

	public static void main(String[] args) {
		MultipleGamesController gamePlayer = new MultipleGamesController();
		GameProgressAnnouncer announcer = new GameProgressAnnouncerOnScreen();
		List<GameChoice> availableGameChoices = GameUtils.defaultGameChoices();
		List<GamePlayer> playerChoicesPlayer1 = GameUtils
				.generateGamePlayerList(new ComputerPlayerRandomChoice(),
						new HumanGamePlayerOnKeyboard());
		List<GamePlayer> playerChoicesPlayer2 = GameUtils
				.generateGamePlayerList(new ComputerPlayerRandomChoice(),
						new HumanGamePlayerOnKeyboard());
		SelectGamePlayer playerSelector = new SelectGamePlayerOnKeyboard();
		AskForAnotherGame askForAnotherGame = new AskForAnotherGameOnKeyboard();
		SingleGameController singleGameController = new SingleGameController(
				10, announcer);

		gamePlayer.setAnnouncer(announcer);
		gamePlayer.setSingleGameController(singleGameController);
		gamePlayer.setAskForAnotherGame(askForAnotherGame);
		gamePlayer.setAvailableGameChoices(availableGameChoices);
		gamePlayer.setAvailablePlayersPlayerOne(playerChoicesPlayer1);
		gamePlayer.setAvailablePlayersPlayerTwo(playerChoicesPlayer2);
		gamePlayer.setGamePlayerSelector(playerSelector);
		gamePlayer.play();
	}

}
