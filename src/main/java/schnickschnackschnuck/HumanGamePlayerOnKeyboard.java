package schnickschnackschnuck;

import java.util.List;
import java.util.Scanner;

public class HumanGamePlayerOnKeyboard extends AbstractGamePlayer {

	private static final String HUMAN_GAME_PLAYER_ON_KEYBOARD = "Human player on keyboard";
	private final Scanner scanner = new Scanner(System.in);

	@Override
	public GameChoice provideChoice(List<GameChoice> choices) {
		int choice = 0;
		if (choices.isEmpty()) {
			return new UnknownChoice();
		} else {
			try {
				printChoices(choices);
				choice = scanner.nextInt();
			} catch (Exception e) {
				return new UnknownChoice();
			}

			if (choice >= choices.size() || choice < 0) {
				return new UnknownChoice();
			} else {
				return choices.get(choice);
			}
		}
	}

	private void printChoices(List<GameChoice> choices) {
		int i = 0;
		for (GameChoice gameChoice : choices) {
			System.out.printf("(%d) %s\n", i, gameChoice.getName());
			++i;
		}
		System.out
				.printf("%s, please make your choice by entering the number displayed in (brackets): ",
						getPlayerName());
	}

	@Override
	public String getPlayerDescription() {
		return HUMAN_GAME_PLAYER_ON_KEYBOARD;
	}

}
