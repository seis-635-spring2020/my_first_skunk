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
				
				if (skunkDice.getLastRoll() == 2)
				{
					ui.println("Two Skunks! You lose the turn, the round score, plus pay 4 chips to the kitty");
					kitty += 4;
					activePlayer.setNumberChips(activePlayer.getNumberChips() - 4);
					activePlayer.setTurnScore(0);
					activePlayer.setRoundScore(0);
					activePlayer.setWantsToRoll(false);
					break;
				}
				else if (skunkDice.getLastRoll() == 3)
				{
					ui.println("Skunks and Deuce! You lose the turn, the turn score, plus pay 2 chips to the kitty");
					kitty += 2;
					activePlayer.setNumberChips(activePlayer.getNumberChips() - 2);
					activePlayer.setTurnScore(0);
					activePlayer.setWantsToRoll(false);
					break;
				}
				else if (skunkDice.getDie1().getLastRoll() == 1 || skunkDice.getDie2().getLastRoll() == 1)
				{
					ui.println("One Skunk! You lose the turn, the turn score, plus pay 1 chip to the kitty");
					kitty += 1;
					activePlayer.setNumberChips(activePlayer.getNumberChips() - 1);
					activePlayer.setTurnScore(0);
					activePlayer.setWantsToRoll(false);
					break;

				}

				activePlayer.setRollScore(skunkDice.getLastRoll());
				activePlayer.setTurnScore(activePlayer.getTurnScore() + skunkDice.getLastRoll());
				ui.println(
						"Roll of " + skunkDice.toString() + ", gives new turn score of " + activePlayer.getTurnScore());

				wantsToRollStr = ui.promptReadAndReturn("Roll again? y or n");
				activePlayer.setWantsToRoll('y' == wantsToRollStr.toLowerCase().charAt(0));

			}

			ui.println("End of turn for " + activePlayer.getName());
			ui.println("Score for this turn is " + activePlayer.getTurnScore() + ", added to...");
			ui.println("Previous round score of " + activePlayer.getRoundScore());
			activePlayer.setRoundScore(activePlayer.getRoundScore() + activePlayer.getTurnScore());
			ui.println("Giving new round score of " + activePlayer.getRoundScore());

			ui.println("");
			if (activePlayer.getRoundScore() >= 100)
				gameNotOver = false;

			ui.println("Scoreboard: ");
			ui.println("Kitty has " + kitty);
			ui.println("player name -- turn score -- round score -- chips");
			ui.println("-----------------------");

			for (int i = 0; i < numberOfPlayers; i++)
			{
				ui.println(players.get(i).getName() + " -- " + players.get(i).getTurnScore() + " -- " + players.get(i).getRoundScore()
						+ " -- " + players.get(i).getNumberChips());
			}
			ui.println("-----------------------");

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
				skunkDice.roll();
				ui.println("Roll is " + skunkDice.toString() + "\n");

				if (skunkDice.getLastRoll() == 2)
				{
					ui.println("Two Skunks! You lose the turn, the turn score, plus pay 4 chips to the kitty");
					kitty += 4;
					activePlayer.setNumberChips(activePlayer.getNumberChips() - 4);
					activePlayer.setTurnScore(0);
					activePlayer.setWantsToRoll(false);
					break;
				}
				else if (skunkDice.getLastRoll() == 3)
				{
					ui.println("Skunks and Deuce! You lose the turn, the turn score, plus pay 2 chips to the kitty");
					kitty += 2;
					activePlayer.setNumberChips(activePlayer.getNumberChips() - 2);
					activePlayer.setTurnScore(0);
					activePlayer.setWantsToRoll(false);

				}
				else if (skunkDice.getDie1().getLastRoll() == 1 || skunkDice.getDie2().getLastRoll() == 1)
				{
					ui.println("One Skunk! You lose the turn, the turn core, plus pay 1 chip to the kitty");
					kitty += 1;
					activePlayer.setNumberChips(activePlayer.getNumberChips() - 1);
					activePlayer.setTurnScore(0);
					activePlayer.setRoundScore(0);
					activePlayer.setWantsToRoll(false);
				}
				else
				{
					activePlayer.setTurnScore(activePlayer.getRollScore() + skunkDice.getLastRoll());
					ui.println("Roll of " + skunkDice.toString() + ", giving new turn score of "
							+ activePlayer.getTurnScore());

					ui.println("Scoreboard: ");
					ui.println("Kitty has " + kitty);
					ui.println("player name -- turn score -- round score -- total chips");
					ui.println("-----------------------");

					for (int pNumber = 0; pNumber < numberOfPlayers; pNumber++)
					{
						ui.println(players.get(pNumber).getName() + " -- " + players.get(pNumber).getTurnScore() + " -- "
								+ players.get(pNumber).getRoundScore() + " -- " + players.get(pNumber).getNumberChips());
					}
					ui.println("-----------------------");

					wantsToRollStr = ui.promptReadAndReturn("Roll again? y or n");
					activePlayer.setWantsToRoll('y' == wantsToRollStr.toLowerCase().charAt(0));
				}

			}

			activePlayer.setTurnScore(activePlayer.getRollScore() + skunkDice.getLastRoll());
			ui.println("Last roll of " + skunkDice.toString() + ", giving final round score of "
					+ activePlayer.getRollScore());

		}

		int winner = 0;
		int winnerScore = 0;

		for (int player = 0; player < numberOfPlayers; player++)
		{
			Player nextPlayer = players.get(player);
			ui.println("Final round score for " + nextPlayer.getName() + " is " + nextPlayer.getRoundScore() + ".");
			if (nextPlayer.getRoundScore() > winnerScore)
			{
				winner = player;
				winnerScore = nextPlayer.getRoundScore();
			}
		}

		ui.println("Round winner is " + players.get(winner).getName() + " with score of " + players.get(winner).getRoundScore());
		players.get(winner).setNumberChips(players.get(winner).getNumberChips() + kitty);
		ui.println("\nRound winner earns " + kitty + ", finishing with " + players.get(winner).getNumberChips());

		ui.println("\nFinal scoreboard for this round:");
		ui.println("player name -- round score -- total chips");
		ui.println("-----------------------");

		for (int pNumber = 0; pNumber < numberOfPlayers; pNumber++)
		{
			ui.println(players.get(pNumber).getName() + " -- " + players.get(pNumber).getRoundScore() + " -- "
					+ players.get(pNumber).getNumberChips());
		}

		ui.println("-----------------------");
		return true;
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

}
