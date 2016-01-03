package schnickschnackschnuck;

import java.util.ArrayList;
import java.util.List;

public class GameUtils {

	static List<GameChoice> generateGameChoiceList(GameChoice... choices) {
		ArrayList<GameChoice> resultList = new ArrayList<GameChoice>();
		for (int i = 0; i < choices.length; ++i) {
			resultList.add(choices[i]);
		}
		return resultList;
	}

	static List<GamePlayer> generateGamePlayerList(GamePlayer... players) {
		ArrayList<GamePlayer> resultList = new ArrayList<GamePlayer>();
		for (int i = 0; i < players.length; ++i) {
			resultList.add(players[i]);
		}
		return resultList;
	}

	public static boolean isUnkownChoice(GameChoice choice) {
		return choice instanceof UnknownChoice;
	}

	public static boolean isUnkownPlayer(GamePlayer player) {
		return player instanceof UnknownGamePlayer;
	}

	public static List<GameChoice> defaultGameChoices() {
		return generateGameChoiceList(new Rock(), new Paper(), new Scissors());
	}

	public static List<GameChoice> defaultGameChoicesReverseOrder() {
		return generateGameChoiceList(new Scissors(), new Paper(), new Rock());
	}

	public static List<GameChoice> emptyGameChoiceList() {
		return generateGameChoiceList();
	}

}
