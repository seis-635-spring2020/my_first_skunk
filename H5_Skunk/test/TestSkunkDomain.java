import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestSkunkDomain {

	@Test
	void test_score_roll_returns_expected_output() {
		String expected_doubleSkunk = "Two Skunks! You lose the turn, the round score, plus pay 4 chips to the kitty";
		String expected_skunkDeuce  = "Skunks and Deuce! You lose the turn, the turn score, plus pay 2 chips to the kitty";
		String expected_singleSkunk = "One Skunk! You lose the turn, the turn score, plus pay 1 chip to the kitty";
		String expected_scoreRoll   = "Roll of 9, gives new turn score of 21";
		
		int[] die1_rolls = new int[] { 1, 1, 2, 1, 6, 1, 5, 1, 4, 1, 3, 3 };
		int[] die2_rolls = new int[] { 1, 2, 1, 6, 1, 5, 1, 4, 1, 3, 1, 6 };

		Die die1 = new Die(die1_rolls);
		Die die2 = new Die(die2_rolls);
		Dice dice = new Dice(die1, die2);
		
		MockUI ui = new MockUI(new String[] {"y"});
		SkunkDomain domain = new SkunkDomain(ui);
		domain.skunkDice = dice;
		ui.setDomain(domain);

		
		dice.roll();
		domain.roll();
		assert(expected_doubleSkunk == domain.getRollMessage(0));
		
		for (int i = 0; i < 2; i++) {
			domain.roll();
			assert(expected_skunkDeuce == domain.getRollMessage(0));
		}
		
		for (int i = 0; i < 8; i++) {
			domain.roll();
			assert(expected_singleSkunk == domain.getRollMessage(0));
		}
		
		
		domain.roll();
		assert(expected_scoreRoll == domain.getRollMessage(12));
	}

}
