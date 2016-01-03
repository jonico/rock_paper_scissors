package schnickschnackschnuck;

import java.util.Scanner;

public class AskForAnotherGameOnKeyboard implements AskForAnotherGame {

	static final String DO_YOU_LIKE_ANOTHER_GAME_Y_N = "Do you like another game with the same players (y/n): ";
	static final String DO_YOU_LIKE_ANOTHER_GAME_DIFFERENT_Y_N = "Do you like another game with different players (y/n): ";
	private final Scanner scanner = new Scanner(System.in);

	@Override
	public boolean askForAnotherGameWithSamePlayers() {
		return askForAnotherGameWithMessage(DO_YOU_LIKE_ANOTHER_GAME_Y_N);
	}

	private boolean askForAnotherGameWithMessage(String message) {
		System.out.print(message);
		try {
			String answer = scanner.nextLine();
			return "y".equals(answer);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean askForAnotherGameWithDifferentPlayers() {
		return askForAnotherGameWithMessage(DO_YOU_LIKE_ANOTHER_GAME_DIFFERENT_Y_N);
	}

}
