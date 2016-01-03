package schnickschnackschnuck;

public interface GameProgressAnnouncer {
	public void announceGameSeasonStarts();

	public void announceSelectNewPlayers();

	public void announceNewGamePlayers(GamePlayer playerOne,
			GamePlayer playerTwo);

	public void announceNewGame(GamePlayer playerOne, GamePlayer playerTwo);

	public void announcePlayerChoice(int round, GamePlayer player,
			GameChoice choice);

	public void announceUnkownAfterXRounds(int numberOfRetriesWithoutClearResult);

	public void announceTieAfterXRounds(int numberOfRetriesWithoutClearResult);

	public void announceIntermediateResult(int round,
			GameChoiceComparisonResult result);

	public void announceWinner(int round, GamePlayer player);

	public void announceGameSeasonEnds();

}
