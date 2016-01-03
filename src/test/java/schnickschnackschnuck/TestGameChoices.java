package schnickschnackschnuck;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestGameChoices {

	private Rock rock = new Rock();
	private Scissors scissors = new Scissors();
	private Paper paper = new Paper();

	@Test
	public void testCompareToWithSameClassShouldTie() {
		shouldTie(rock, new Rock());
		shouldTie(scissors, new Scissors());
		shouldTie(paper, new Paper());
	}

	@Test
	public void differentChoicesShouldHaveDifferentNames() {
		nameShouldDiffer(rock, scissors);
		nameShouldDiffer(rock, paper);
		nameShouldDiffer(paper, scissors);
	}

	@Test
	public void sameChoicesShouldHaveSameNames() {
		nameShouldBeSame(rock, new Rock());
		nameShouldBeSame(scissors, new Scissors());
		nameShouldBeSame(paper, new Paper());
		nameShouldBeSame(new UnknownChoice(), new UnknownChoice());
	}

	private void nameShouldBeSame(GameChoice choice1, GameChoice choice2) {
		assertEquals(choice1.getName(), choice2.getName());
	}

	private void nameShouldDiffer(GameChoice choice1, GameChoice choice2) {
		assertNotEquals(choice1.getName(), choice2.getName());
	}

	@Test
	public void testCompareWithUnknownShouldResultInUnkown() {
		UnknownChoice unknownChoice = new UnknownChoice();
		shouldResultInUnkownResult(rock, unknownChoice);
		shouldResultInUnkownResult(scissors, unknownChoice);
		shouldResultInUnkownResult(paper, unknownChoice);
	}

	private void shouldResultInUnkownResult(GameChoice choice,
			UnknownChoice unknown) {
		assertEquals(GameChoiceComparisonResult.unknown,
				choice.compareTo(unknown));
		assertEquals(GameChoiceComparisonResult.unknown,
				unknown.compareTo(choice));
	}

	@Test
	public void testScissorsPaper() {
		firstShouldLose(paper, scissors);
	}

	@Test
	public void testScissorsRock() {
		firstShouldLose(rock, paper);
	}

	@Test
	public void testPaperRock() {
		firstShouldLose(rock, paper);
	}

	private void firstShouldLose(GameChoice loser, GameChoice winner) {
		assertEquals(GameChoiceComparisonResult.win, winner.compareTo(loser));
		assertEquals(GameChoiceComparisonResult.lose, loser.compareTo(winner));
	}

	private void shouldTie(GameChoice one, GameChoice two) {
		assertEquals(GameChoiceComparisonResult.tie, one.compareTo(two));
		assertEquals(GameChoiceComparisonResult.tie, two.compareTo(one));
	}

}
