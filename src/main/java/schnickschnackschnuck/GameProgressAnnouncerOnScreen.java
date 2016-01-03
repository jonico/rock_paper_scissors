package schnickschnackschnuck;

public class GameProgressAnnouncerOnScreen implements GameProgressAnnouncer {

	@Override
	public void announceUnkownAfterXRounds(int numberOfRetriesWithoutClearResult) {
		System.out
				.printf("After %d rounds we had an unknown result, so single game result is unknown.\n",
						numberOfRetriesWithoutClearResult);
	}

	@Override
	public void announceTieAfterXRounds(int numberOfRetriesWithoutClearResult) {
		System.out
				.printf("After %d rounds we had a tie result, so single game result is tie.\n",
						numberOfRetriesWithoutClearResult);
	}

	@Override
	public void announcePlayerChoice(int round, GamePlayer player,
			GameChoice choice) {
		System.out.printf("Player %s chose %s in round %d.\n",
				player.getPlayerName(), choice.getName(), round);
	}

	@Override
	public void announceIntermediateResult(int round,
			GameChoiceComparisonResult result) {
		System.out.printf("No clear winner in round %d as result was %s.\n",
				round, result);
	}

	@Override
	public void announceWinner(int round, GamePlayer player) {
		System.out.printf("After round %d, we have a clear winner: %s.\n",
				round, player.getPlayerName());
	}

	@Override
	public void announceGameSeasonStarts() {
		System.out.printf("Welcome to a new season of games!\n\n");

	}

	@Override
	public void announceSelectNewPlayers() {
		System.out.printf("Please select your players!\n\n");
	}

	@Override
	public void announceNewGamePlayers(GamePlayer playerOne,
			GamePlayer playerTwo) {
		announcePlayer(1, playerOne);
		announcePlayer(2, playerTwo);
	}

	private void announcePlayer(int playerNumber, GamePlayer player) {
		System.out.printf("For player %d, you have selected: %s (%s).\n",
				playerNumber, player.getPlayerName(),
				player.getPlayerDescription());
	}

	@Override
	public void announceNewGame(GamePlayer playerOne, GamePlayer playerTwo) {
		System.out.printf("A new game between %s and %s begins ...\n",
				playerOne.getPlayerName(), playerTwo.getPlayerName());
	}

	@Override
	public void announceGameSeasonEnds() {
		System.out.printf("Game season ends,  see you next time!\n");

	}

}
