package schnickschnackschnuck;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestGameChoiceComparisonResult {

	@Test
	public void testNegation() {
		assertEquals(GameChoiceComparisonResult.tie,
				GameChoiceComparisonResult.tie.negate());
		assertEquals(GameChoiceComparisonResult.lose,
				GameChoiceComparisonResult.win.negate());
		assertEquals(GameChoiceComparisonResult.win,
				GameChoiceComparisonResult.lose.negate());
		assertEquals(GameChoiceComparisonResult.unknown,
				GameChoiceComparisonResult.unknown.negate());
	}

}
