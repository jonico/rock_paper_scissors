package schnickschnackschnuck;

import java.util.List;

public class SingleGameController {
	private final int maxNumberOfRoundsWithoutClearWinner;
	private final GameProgressAnnouncer announcer;

	public SingleGameController(int maxNumberOfRoundsWithoutClearWinner,
			GameProgressAnnouncer announcer) {
		this.maxNumberOfRoundsWithoutClearWinner = maxNumberOfRoundsWithoutClearWinner;
		this.announcer = announcer;
	}

	public SingleGameResult play(GamePlayer playerOne, GamePlayer playerTwo,
			List<GameChoice> gameChoices) {
		announcer.announceNewGame(playerOne, playerTwo);

		GameChoiceComparisonResult result = GameChoiceComparisonResult.unknown;
		GameChoice choiceOfPlayerOne = new UnknownChoice();
		GameChoice choiceOfPlayerTwo = new UnknownChoice();

		for (int round = 1; round <= maxNumberOfRoundsWithoutClearWinner; ++round) {
			choiceOfPlayerOne = playerOne.provideChoice(gameChoices);
			choiceOfPlayerTwo = playerTwo.provideChoice(gameChoices);
			announcePlayerChoice(round, playerOne, choiceOfPlayerOne);
			announcePlayerChoice(round, playerTwo, choiceOfPlayerTwo);

			result = choiceOfPlayerOne.compareTo(choiceOfPlayerTwo);
			playerOne.receiveFeedback(choiceOfPlayerOne, choiceOfPlayerTwo,
					result);
			playerTwo.receiveFeedback(choiceOfPlayerTwo, choiceOfPlayerOne,
					result.negate());

			if (GameChoiceComparisonResult.win.equals(result)) {
				announceWinner(round, playerOne);
				return SingleGameResult.playerOneWon;
			} else if (GameChoiceComparisonResult.lose.equals(result)) {
				announceWinner(round, playerTwo);
				return SingleGameResult.playerTwoWon;
			} else {
				announceIntermediateResult(round, result);
			}
		}
		// number of unclear tries exceeded
		if (GameChoiceComparisonResult.tie.equals(result)) {
			announceTieAfterXRounds();
			return SingleGameResult.tie;
		} else {
			announceUnkownAfterXRounds();
			return SingleGameResult.unknown;
		}
	}

	private void announceWinner(int round, GamePlayer player) {
		announcer.announceWinner(round, player);
	}

	private void announcePlayerChoice(int round, GamePlayer player,
			GameChoice choice) {
		announcer.announcePlayerChoice(round, player, choice);
	}

	private void announceUnkownAfterXRounds() {
		announcer
				.announceUnkownAfterXRounds(maxNumberOfRoundsWithoutClearWinner);

	}

	private void announceTieAfterXRounds() {
		announcer.announceTieAfterXRounds(maxNumberOfRoundsWithoutClearWinner);
	}

	void announceIntermediateResult(int round, GameChoiceComparisonResult result) {
		announcer.announceIntermediateResult(round, result);
	}

}
