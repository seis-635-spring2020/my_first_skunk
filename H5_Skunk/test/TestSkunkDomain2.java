import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestSkunkDomain2 {

	String[] prompts = { "2", "Player 1", "Player 2",
			"n",
			"n",
			"y", "y", "y", "n",
			"y", "y",
			"y", "y", "y", "y", "n",
			"y", "y",
			"y", "y", 
			"y",
			"y", "y", "n",
			"y"	};
	
	int[] die1_rolls = new int[] { 3, 6, 5, 5, 1, 3, 4, 3, 2, 5, 5, 6, 1, 1, 6, 4, 1 };
	int[] die2_rolls = new int[] { 3, 5, 6, 5, 1, 4, 5, 4, 5, 4, 1, 6, 2, 4, 2, 6, 1 };
	
	String case1 = "Welcome to Skunk 0.47\n" + 
			"\n" + 
			"How many players? => 2\n" + 
			"Enter name of player 1 => Player 1\n" + 
			"Enter name of player 2 => Player 2\n" + 
			"Starting game...\n" + 
			"\n" + 
			"Next player is Player 1.\n" + 
			"Roll? y or n => n\n" + 
			"End of turn for Player 1\n" + 
			"Score for this turn is 0, added to...\n" + 
			"Previous round score of 0\n" + 
			"Giving new round score of 0\n" + 
			"\n" + 
			"Scoreboard: \n" + 
			"Kitty has 0\n" + 
			"player name -- turn score -- round score -- chips\n" + 
			"-----------------------\n" + 
			"Player 1 -- 0 -- 0 -- 50\n" + 
			"Player 2 -- 0 -- 0 -- 50\n" + 
			"-----------------------\n" + 
			"Turn passes to right...\n" + 
			"Next player is Player 2.\n" + 
			"Roll? y or n => n\n" + 
			"End of turn for Player 2\n" + 
			"Score for this turn is 0, added to...\n" + 
			"Previous round score of 0\n" + 
			"Giving new round score of 0\n" + 
			"\n" + 
			"Scoreboard: \n" + 
			"Kitty has 0\n" + 
			"player name -- turn score -- round score -- chips\n" + 
			"-----------------------\n" + 
			"Player 1 -- 0 -- 0 -- 50\n" + 
			"Player 2 -- 0 -- 0 -- 50\n" + 
			"-----------------------\n" + 
			"Turn passes to right...\n" + 
			"Next player is Player 1.\n" + 
			"Roll? y or n => y\n" + 
			"Roll of Dice with last roll: 6 => 3 + 3, gives new turn score of 6\n" + 
			"Roll again? y or n => y\n" + 
			"Roll of Dice with last roll: 11 => 6 + 5, gives new turn score of 17\n" + 
			"Roll again? y or n => y\n" + 
			"Roll of Dice with last roll: 11 => 5 + 6, gives new turn score of 28\n" + 
			"Roll again? y or n => n\n" + 
			"End of turn for Player 1\n" + 
			"Score for this turn is 28, added to...\n" + 
			"Previous round score of 0\n" + 
			"Giving new round score of 28\n" + 
			"\n" + 
			"Scoreboard: \n" + 
			"Kitty has 0\n" + 
			"player name -- turn score -- round score -- chips\n" + 
			"-----------------------\n" + 
			"Player 1 -- 28 -- 28 -- 50\n" + 
			"Player 2 -- 0 -- 0 -- 50\n" + 
			"-----------------------\n" + 
			"Turn passes to right...\n" + 
			"Next player is Player 2.\n" + 
			"Roll? y or n => y\n" + 
			"Roll of Dice with last roll: 10 => 5 + 5, gives new turn score of 10\n" + 
			"Roll again? y or n => y\n" + 
			"Two Skunks! You lose the turn, the round score, plus pay 4 chips to the kitty\n" + 
			"End of turn for Player 2\n" + 
			"Score for this turn is 0, added to...\n" + 
			"Previous round score of 0\n" + 
			"Giving new round score of 0\n" + 
			"\n" + 
			"Scoreboard: \n" + 
			"Kitty has 4\n" + 
			"player name -- turn score -- round score -- chips\n" + 
			"-----------------------\n" + 
			"Player 1 -- 28 -- 28 -- 50\n" + 
			"Player 2 -- 0 -- 0 -- 46\n" + 
			"-----------------------\n" + 
			"Turn passes to right...\n" + 
			"Next player is Player 1.\n" + 
			"Roll? y or n => y\n" + 
			"Roll of Dice with last roll: 7 => 3 + 4, gives new turn score of 35\n" + 
			"Roll again? y or n => y\n" + 
			"Roll of Dice with last roll: 9 => 4 + 5, gives new turn score of 44\n" + 
			"Roll again? y or n => y\n" + 
			"Roll of Dice with last roll: 7 => 3 + 4, gives new turn score of 51\n" + 
			"Roll again? y or n => y\n" + 
			"Roll of Dice with last roll: 7 => 2 + 5, gives new turn score of 58\n" + 
			"Roll again? y or n => n\n" + 
			"End of turn for Player 1\n" + 
			"Score for this turn is 58, added to...\n" + 
			"Previous round score of 28\n" + 
			"Giving new round score of 86\n" + 
			"\n" + 
			"Scoreboard: \n" + 
			"Kitty has 4\n" + 
			"player name -- turn score -- round score -- chips\n" + 
			"-----------------------\n" + 
			"Player 1 -- 58 -- 86 -- 50\n" + 
			"Player 2 -- 0 -- 0 -- 46\n" + 
			"-----------------------\n" + 
			"Turn passes to right...\n" + 
			"Next player is Player 2.\n" + 
			"Roll? y or n => y\n" + 
			"Roll of Dice with last roll: 9 => 5 + 4, gives new turn score of 9\n" + 
			"Roll again? y or n => y\n" + 
			"One Skunk! You lose the turn, the turn score, plus pay 1 chip to the kitty\n" + 
			"End of turn for Player 2\n" + 
			"Score for this turn is 0, added to...\n" + 
			"Previous round score of 0\n" + 
			"Giving new round score of 0\n" + 
			"\n" + 
			"Scoreboard: \n" + 
			"Kitty has 5\n" + 
			"player name -- turn score -- round score -- chips\n" + 
			"-----------------------\n" + 
			"Player 1 -- 58 -- 86 -- 50\n" + 
			"Player 2 -- 0 -- 0 -- 45\n" + 
			"-----------------------\n" + 
			"Turn passes to right...\n" + 
			"Next player is Player 1.\n" + 
			"Roll? y or n => y\n" + 
			"Roll of Dice with last roll: 12 => 6 + 6, gives new turn score of 70\n" + 
			"Roll again? y or n => y\n" + 
			"Skunks and Deuce! You lose the turn, the turn score, plus pay 2 chips to the kitty\n" + 
			"End of turn for Player 1\n" + 
			"Score for this turn is 0, added to...\n" + 
			"Previous round score of 86\n" + 
			"Giving new round score of 86\n" + 
			"\n" + 
			"Scoreboard: \n" + 
			"Kitty has 7\n" + 
			"player name -- turn score -- round score -- chips\n" + 
			"-----------------------\n" + 
			"Player 1 -- 0 -- 86 -- 48\n" + 
			"Player 2 -- 0 -- 0 -- 45\n" + 
			"-----------------------\n" + 
			"Turn passes to right...\n" + 
			"Next player is Player 2.\n" + 
			"Roll? y or n => y\n" + 
			"One Skunk! You lose the turn, the turn score, plus pay 1 chip to the kitty\n" + 
			"End of turn for Player 2\n" + 
			"Score for this turn is 0, added to...\n" + 
			"Previous round score of 0\n" + 
			"Giving new round score of 0\n" + 
			"\n" + 
			"Scoreboard: \n" + 
			"Kitty has 8\n" + 
			"player name -- turn score -- round score -- chips\n" + 
			"-----------------------\n" + 
			"Player 1 -- 0 -- 86 -- 48\n" + 
			"Player 2 -- 0 -- 0 -- 44\n" + 
			"-----------------------\n" + 
			"Turn passes to right...\n" + 
			"Next player is Player 1.\n" + 
			"Roll? y or n => y\n" + 
			"Roll of Dice with last roll: 8 => 6 + 2, gives new turn score of 8\n" + 
			"Roll again? y or n => y\n" + 
			"Roll of Dice with last roll: 10 => 4 + 6, gives new turn score of 18\n" + 
			"Roll again? y or n => n\n" + 
			"End of turn for Player 1\n" + 
			"Score for this turn is 18, added to...\n" + 
			"Previous round score of 86\n" + 
			"Giving new round score of 104\n" + 
			"\n" + 
			"Scoreboard: \n" + 
			"Kitty has 8\n" + 
			"player name -- turn score -- round score -- chips\n" + 
			"-----------------------\n" + 
			"Player 1 -- 18 -- 104 -- 48\n" + 
			"Player 2 -- 0 -- 0 -- 44\n" + 
			"-----------------------\n" + 
			"Turn passes to right...\n" + 
			"Last turn for all...\n" + 
			"Last round for player Player 2...\n" + 
			"Roll? y or n => y\n" + 
			"Roll is Dice with last roll: 2 => 1 + 1\n" + 
			"\n" + 
			"Two Skunks! You lose the turn, the round score, plus pay 4 chips to the kitty\n" + 
			"Last roll of Dice with last roll: 2 => 1 + 1, giving final round score of 0\n" + 
			"Final round score for Player 1 is 104.\n" + 
			"Final round score for Player 2 is 0.\n" + 
			"Round winner is Player 1 with score of 104\n" + 
			"\n" + 
			"Round winner earns 12, finishing with 60\n" + 
			"\n" + 
			"Final scoreboard for this round:\n" + 
			"player name -- round score -- total chips\n" + 
			"-----------------------\n" + 
			"Player 1 -- 104 -- 60\n" + 
			"Player 2 -- 0 -- 40\n" + 
			"-----------------------\n";
	
	@Test
	void testRun() {
		Die die1 = new Die(die1_rolls);
		Die die2 = new Die(die2_rolls);
		Dice dice = new Dice(die1, die2);
		MockUI ui = new MockUI(prompts);
		SkunkDomain domain = new SkunkDomain(ui);
		domain.skunkDice = dice;
		ui.setDomain(domain);
		domain.run();
		assertEquals(ui.ActualMessage,case1);
	}

}
