import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestSkunkDomain {

	SkunkDomain createDomain() {
		int[] die1_rolls = new int[] { 1, 1, 2, 1, 6, 1, 5, 1, 4, 1, 3, 3 };
		int[] die2_rolls = new int[] { 1, 2, 1, 6, 1, 5, 1, 4, 1, 3, 1, 6 };

		Die die1 = new Die(die1_rolls);
		Die die2 = new Die(die2_rolls);
		Dice dice = new Dice(die1, die2);
		
		MockUI ui = new MockUI(new String[] {"y"});
		SkunkDomain domain = new SkunkDomain(ui);
		domain.skunkDice = dice;
		ui.setDomain(domain);

		return domain;
	}
	
	@Test
	void test_score_roll_returns_expected_output() {
		String expected_doubleSkunk = "Two Skunks! You lose the turn, the round score, plus pay 4 chips to the kitty";
		String expected_skunkDeuce  = "Skunks and Deuce! You lose the turn, the turn score, plus pay 2 chips to the kitty";
		String expected_singleSkunk = "One Skunk! You lose the turn, the turn score, plus pay 1 chip to the kitty";
		String expected_scoreRoll   = "Roll of Dice with last roll: 9 => 3 + 6, gives new turn score of 21";
		
		SkunkDomain domain = createDomain();
		
		domain.roll();
		assertEquals(expected_doubleSkunk, domain.getRollMessage(0));
		
		for (int i = 0; i < 2; i++) {
			domain.roll();
			assertEquals(expected_skunkDeuce, domain.getRollMessage(0));
		}
		
		for (int i = 0; i < 8; i++) {
			domain.roll();
			assertEquals(expected_singleSkunk, domain.getRollMessage(0));
		}
		
		
		domain.roll();
		assertEquals(expected_scoreRoll, domain.getRollMessage(21));
	}

	@Test
	void test_player_is_added_when_addPlayer_is_called() {
		String expected_PlayerName = "Ready Player 1";
		
		SkunkDomain domain = createDomain();
		
		domain.addPlayer(expected_PlayerName, 0);
		
		assertEquals(expected_PlayerName, domain.playerNames[0]);
		assertEquals(50, domain.players.get(0).getNumberChips());
		assertEquals(0, domain.players.get(0).getRoundScore());
		assertEquals(0, domain.players.get(0).getTurnScore());

	}
	
	@Test
	void test_correct_penalty_is_calculated() {
		SkunkDomain domain = createDomain();
		domain.addPlayer("Player 1", 0);
		domain.activePlayerIndex = 0;
		
		domain.roll();
		assertEquals(4, domain.getChipPenalty());
		
		for (int i = 0; i < 2; i++) {
			domain.roll();
			assertEquals(2, domain.getChipPenalty());
		}
		
		for (int i = 0; i < 8; i++) {
			domain.roll();
			assertEquals(1, domain.getChipPenalty());
		}
		
		domain.roll();
		assertEquals(0, domain.getChipPenalty());
	}
	
	@Test
	void test_correct_turn_score_is_calculated() {
		SkunkDomain domain = createDomain();

		domain.addPlayer("Player 1",  0);
		domain.activePlayerIndex = 0;
		
		for (int i = 0; i < 11; i++) {
			domain.roll();
			domain.activePlayer.setTurnScore(5);
			assertEquals(0, domain.getTurnScore());
		}
		
		domain.roll();
		domain.activePlayer.setTurnScore(5);
		assertEquals(14, domain.getTurnScore());
	}
}
