import java.util.ArrayList;
import edu.princeton.cs.introcs.*;

public class SkunkDomain
{
	// Refactoring @author Rhyan Foo Kune
	// Change: make public attribute private
	private SkunkUI ui;
	
	// Refactoring @author Rhyan Foo Kune
	// Change: remove unnecessary attribute - SkunkUI already implements UI
	// private UI ui;
	
	private int numberOfPlayers;

	// Refactoring @author Rhyan Foo Kune
	// Change: move names to player class and remove playerNames attribute
	// public String[] playerNames;
	
	private ArrayList<Player> players;
	private int kitty;

	private Player activePlayer;
	private int activePlayerIndex;

	// Refactoring @author Rhyan Foo Kune
	// Change: remove unused attribute
	// public boolean wantsToQuit;
	
	// Refactoring @author Rhyan Foo Kune
	// Change: remove unused attribute
	// public boolean oneMoreRoll;

	private Dice skunkDice;

	public SkunkDomain(SkunkUI ui)
	{
		this.ui = ui;

		// Refactoring @author Rhyan Foo Kune
		// Change: move player names to player class
		// this.playerNames = new String[20];
		
		this.players = new ArrayList<Player>();
		this.skunkDice = new Dice();
		
		// Refactoring @author Rhyan Foo Kune
		// Change: remove unused attribute
		// this.wantsToQuit = false;
		
		// Refactoring @author Rhyan Foo Kune
		// Change: remove unused attribute
		// this.oneMoreRoll = false;
	}

	public boolean run()
	{
		ui.println("Welcome to Skunk 0.47\n");

		String numberPlayersString = ui.promptReadAndReturn("How many players?");
		this.numberOfPlayers = Integer.parseInt(numberPlayersString);

		for (int playerNumber = 0; playerNumber < numberOfPlayers; playerNumber++)
		{
			// Refactoring @author Rhyan Foo Kune
			// Change: use SkunkUI promptReadAndReturn method to prompt question and return input
			String playerName = ui.promptReadAndReturn("Enter name of player " + (playerNumber + 1) + ": ");
			
			this.players.add(new Player(playerName, 50));
		}
		
		activePlayerIndex = 0;
		activePlayer = players.get(activePlayerIndex);

		ui.println("Starting game...\n");
		boolean gameNotOver = true;

		while (gameNotOver)
		{
			ui.println("Next player is " + activePlayer.getName() + ".");
			String wantsToRollStr = ui.promptReadAndReturn("Roll? y or n");
			
			// Refactoring @author Rhyan Foo Kune
			// Change: make wantsToRoll an attribute in player class
			activePlayer.setWantsToRoll('y' == wantsToRollStr.toLowerCase().charAt(0));

			while (activePlayer.getWantsToRoll())
			{
				activePlayer.setRollScore(0);
				skunkDice.roll();
				
				// Refactoring @author Rhyan Foo Kune
				// Change: extract method - return boolean to indicate if skunk has been rolled
				boolean skunkRolled = this.handleDiceOutcome();
				if (skunkRolled) break;

				wantsToRollStr = ui.promptReadAndReturn("Roll again? y or n");
				activePlayer.setWantsToRoll('y' == wantsToRollStr.toLowerCase().charAt(0));

			}

			int previousRoundScore = activePlayer.getRoundScore();
			int turnScore = activePlayer.getTurnScore();
			activePlayer.setRoundScore(previousRoundScore + turnScore);
			
			// Refactoring @author Rhyan Foo Kune
			// Change: add new printEndTurnReport method in SkunkUI
			ui.printEndTurnReport(activePlayer.getName(), previousRoundScore, turnScore);
			ui.println("");
			
			if (activePlayer.getRoundScore() >= 100)
				gameNotOver = false;

			// Refactoring @author Rhyan Foo Kune
			// Change: extract method
			this.showScoreboard(false);

			ui.println("Turn passes to right...");

			activePlayerIndex = (activePlayerIndex + 1) % numberOfPlayers;
			activePlayer = players.get(activePlayerIndex);

		}
		
		// last round: everyone but last activePlayer gets another shot

		ui.println("Last turn for all...");

		for (int i = activePlayerIndex, count = 0; count < numberOfPlayers - 1; i = (i++) % numberOfPlayers, count++)
		{
			ui.println("Last round for player " + players.get(activePlayerIndex).getName() + "...");
			activePlayer.setTurnScore(0);

			String wantsToRollStr = ui.promptReadAndReturn("Roll? y or n");
			// Refactoring @author Rhyan Foo Kune
			// Change: make wantsToRoll an attribute in player class
			activePlayer.setWantsToRoll('y' == wantsToRollStr.toLowerCase().charAt(0));
			
			while (activePlayer.getWantsToRoll())
			{
				activePlayer.setRollScore(0);
				skunkDice.roll();
				
				// Refactoring @author Rhyan Foo Kune
				// Change: extract method - return boolean to indicate if skunk has been rolled
				boolean skunkRolled = this.handleDiceOutcome();
				if (skunkRolled) break;				

				// Refactoring @author Rhyan Foo Kune
				// Change: extract method
				this.showScoreboard(false);

				wantsToRollStr = ui.promptReadAndReturn("Roll again? y or n");
				activePlayer.setWantsToRoll('y' == wantsToRollStr.toLowerCase().charAt(0));

			}
		}
		
		for (int pNumber = 0; pNumber < numberOfPlayers; pNumber++)
		{
			ui.println("Final round score for " + players.get(pNumber).getName() + " is " + players.get(pNumber).getRoundScore() + ".");
		}

		
		// Refactoring @author Rhyan Foo Kune
		// Change: extract method
		Player winner = this.getWinner();
		
		ui.println("Round winner is " + winner.getName() + " with score of " + winner.getRoundScore());
		
		winner.setNumberChips(winner.getNumberChips() + kitty);
		ui.println("\nRound winner earns " + kitty + ", finishing with " + winner.getNumberChips());
		
		kitty = 0;

		// Refactoring @author Rhyan Foo Kune
		// Change: extract method
		this.showScoreboard(true);
		
		return true;
	}
	
	// Refactoring @author Rhyan Foo Kune
	// Change: extract method
	// returns true if outcome is a skunk
	public boolean handleDiceOutcome()
	{					
		if (skunkDice.getLastRoll() == 2)
		{
			// Refactoring @author Rhyan Foo Kune
			// Change: set temp variable
			// Reason: avoid error in future development - only one place to update the penality
			int penality = 4;
			ui.println("Two Skunks! You lose the turn, the round score, plus pay " + penality + " chips to the kitty");
			
			kitty += penality;
			activePlayer.setNumberChips(activePlayer.getNumberChips() - penality);
			activePlayer.setTurnScore(0);
			activePlayer.setRoundScore(0);
			activePlayer.setWantsToRoll(false);
			
			return true;
		}
		else if (skunkDice.getLastRoll() == 3)
		{
			// Refactoring @author Rhyan Foo Kune
			// Change: set temp variable
			// Reason: avoid error in future development - only one place to update the penality
			int penality = 2;
			ui.println("Skunks and Deuce! You lose the turn, the turn score, plus pay " + penality + " chips to the kitty");
			
			kitty += penality;
			activePlayer.setNumberChips(activePlayer.getNumberChips() - penality);
			activePlayer.setTurnScore(0);
			activePlayer.setWantsToRoll(false);
			
			return true;
		}
		else if (skunkDice.getDie1().getLastRoll() == 1 || skunkDice.getDie2().getLastRoll() == 1)
		{
			// Refactoring @author Rhyan Foo Kune
			// Change: set temp variable
			// Reason: avoid error in future development - only one place to update the penality
			int penality = 1;
			ui.println("One Skunk! You lose the turn, the turn score, plus pay " + penality + " chip to the kitty");
			
			kitty += penality;
			activePlayer.setNumberChips(activePlayer.getNumberChips() - penality);
			activePlayer.setTurnScore(0);
			activePlayer.setWantsToRoll(false);
			
			return true;
		}
		
		activePlayer.setRollScore(skunkDice.getLastRoll());
		activePlayer.setTurnScore(activePlayer.getTurnScore() + skunkDice.getLastRoll());
		
		ui.println("Roll of " + skunkDice.toString() + ", gives new turn score of " + activePlayer.getTurnScore());
		
		return false;
	}

	// Refactoring @author Rhyan Foo Kune
	// Change: extract method
	public void showScoreboard(boolean isFinal)
	{
		if (isFinal) 
		{
			ui.println("\nFinal scoreboard for this round:");
			ui.println("player name -- round score -- total chips");
		} else {
			ui.println("Scoreboard: ");
			ui.println("Kitty has " + kitty);
			ui.println("player name -- turn score -- round score -- chips");
		}
		
		ui.println("-----------------------");

		for (int pNumber = 0; pNumber < numberOfPlayers; pNumber++)
		{
			String reportString = players.get(pNumber).getName() + " -- ";
					
			if (!isFinal) 
			{
				reportString += players.get(pNumber).getTurnScore() + " -- ";
			}
			
			reportString += players.get(pNumber).getRoundScore() + " -- " + players.get(pNumber).getNumberChips();
			
			ui.println(reportString);
		}
		
		ui.println("-----------------------");
	}
	
	
	// Refactoring @author Rhyan Foo Kune
	// Change: extract method
	public Player getWinner()
	{
		Player winner = null;
		int maxScore = 0;
		
		for (int player = 0; player < numberOfPlayers; player++)
		{
			Player nextPlayer = players.get(player);
			if (nextPlayer.getRoundScore() > maxScore)
			{
				winner = nextPlayer;
				maxScore = nextPlayer.getRoundScore();
			}
		}
		
		return winner;
	}

}
