package schnickschnackschnuck;

import java.util.List;
import java.util.Scanner;

public class SelectGamePlayerOnKeyboard implements SelectGamePlayer {

	private final Scanner scanner = new Scanner(System.in);

	@Override
	public GamePlayer selectGamePlayer(int playerNumber,
			List<GamePlayer> players) {
		if (players.isEmpty()) {
			return new UnknownGamePlayer();
		} else {
			int playerSelection = 0;
			printPlayers(playerNumber, players);
			try {
				playerSelection = scanner.nextInt();
				if (playerSelection < 0 || playerSelection >= players.size()) {
					return new UnknownGamePlayer();
				} else {
					askForPlayerName();
					// skip the new line
					scanner.nextLine();
					String playerName = scanner.nextLine();
					GamePlayer player = players.get(playerSelection);
					player.setPlayerName(playerName);
					return player;
				}
			} catch (Exception e) {
				return new UnknownGamePlayer();
			}

		}

	}

	private void askForPlayerName() {
		System.out.printf("Please provide a name for this player: ");
	}

	private void printPlayers(int playerNumber, List<GamePlayer> players) {
		int i = 0;
		for (GamePlayer player : players) {
			System.out.printf("(%d) %s\n", i, player.getPlayerDescription());
			++i;
		}
		System.out
				.printf("Please make your choice for player %d by entering the number displayed in (brackets): ",
						playerNumber);
	}

}
