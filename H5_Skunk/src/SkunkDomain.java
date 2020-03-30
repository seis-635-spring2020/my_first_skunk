import java.util.ArrayList;
import edu.princeton.cs.introcs.*;

public class SkunkDomain
{
	public SkunkUI skunkUI;
	public UI ui;
	public int numberOfPlayers;
	public String[] playerNames;
	public ArrayList<Player> players;
	public int kitty;

	public Player activePlayer;
	public int activePlayerIndex;

	public boolean wantsToQuit;
	public boolean oneMoreRoll;

	public Dice skunkDice;

	public SkunkDomain(SkunkUI ui)
	{
		this.skunkUI = ui;
		this.ui = ui; // hide behind the interface UI

		this.playerNames = new String[20];
		this.players = new ArrayList<Player>();
		this.skunkDice = new Dice();
		this.wantsToQuit = false;
		this.oneMoreRoll = false;
	}
	
	// Second Refactor.
	// This function was added to facilitate the unit testing of getRollMessage.
	// It enables execution of the this.skunkDice.roll() function from unit tests.
	void roll() {
		this.skunkDice.roll();
	}
	
	// Second Refactor.
	// This function extracts the logic for generating the turn Ui message from the this.run() method.
	String getRollMessage(int currentTurnScore) {
		if (skunkDice.getLastRoll() == 2)
		{
			return "Two Skunks! You lose the turn, the round score, plus pay 4 chips to the kitty";
		}
		else if (skunkDice.getLastRoll() == 3)
		{
			return "Skunks and Deuce! You lose the turn, the turn score, plus pay 2 chips to the kitty";
		}
		else if (skunkDice.getDie1().getLastRoll() == 1 || skunkDice.getDie2().getLastRoll() == 1)
		{
			return "One Skunk! You lose the turn, the turn score, plus pay 1 chip to the kitty";
		}
		return "Roll of " + skunkDice.toString() + ", gives new turn score of " + currentTurnScore;
	}
	
	// Third Refactor.
	// Expose Add Player logic at a package level to facilitate testing and refactoring of kitty logic.
	void addPlayer(String name, int playerNumber) {
		this.players.add(new Player(50));
		this.playerNames[playerNumber] = name;	
	}
	
	//Third Refactor.
	// Pull Chip penalty logic into a separate function.
	int getChipPenalty() {
		if (skunkDice.getLastRoll() == 2)
		{
			return 4;
		}
		else if (skunkDice.getLastRoll() == 3)
		{
			return 2;
		}
		else if (skunkDice.getDie1().getLastRoll() == 1 || skunkDice.getDie2().getLastRoll() == 1)
		{
			return 1;
		}
		return 0;
	}
	
	int getTurnScore() {
		if (skunkDice.getDie1().getLastRoll() == 1 || skunkDice.getDie2().getLastRoll() == 1)
		{
			return 0;
		}
		return this.activePlayer.getTurnScore() + this.skunkDice.getLastRoll();
	}

	public boolean run()
	{
		ui.println("Welcome to Skunk 0.47\n");

		String numberPlayersString = skunkUI.promptReadAndReturn("How many players?");
		this.numberOfPlayers = Integer.parseInt(numberPlayersString);

		for (int playerNumber = 0; playerNumber < numberOfPlayers; playerNumber++)
		{
			// First Refactoring.
			// The logic in this loop repeated logic already present in SkunkUI.java.
			// In addition to being repetitive, it also impairs my ability to test the
			// domain class because it introduces user interface logic into the domain
			// controller logic.
			String name = ui.promptReadAndReturn("Enter name of player " + (playerNumber + 1));
			
			this.addPlayer(name, playerNumber);
		}
		activePlayerIndex = 0;
		activePlayer = players.get(activePlayerIndex);

		ui.println("Starting game...\n");
		boolean gameNotOver = true;

		while (gameNotOver)
		{
			ui.println("Next player is " + playerNames[activePlayerIndex] + ".");
			String wantsToRollStr = ui.promptReadAndReturn("Roll? y or n");
			boolean wantsToRoll = 'y' == wantsToRollStr.toLowerCase().charAt(0);

			while (wantsToRoll)
			{
				activePlayer.setRollScore(0);
				skunkDice.roll();
				kitty += this.getChipPenalty();
				activePlayer.setNumberChips(activePlayer.getNumberChips() - this.getChipPenalty());
				activePlayer.setTurnScore(this.getTurnScore());
				
				if (skunkDice.getLastRoll() == 2)
				{
					activePlayer.setRoundScore(0);
					wantsToRoll = false;
					ui.println(this.getRollMessage(activePlayer.getTurnScore()));
					break;
				}
				else if (skunkDice.getLastRoll() == 3)
				{
					wantsToRoll = false;
					ui.println(this.getRollMessage(activePlayer.getTurnScore()));
					break;
				}
				else if (skunkDice.getDie1().getLastRoll() == 1 || skunkDice.getDie2().getLastRoll() == 1)
				{
					wantsToRoll = false;
					ui.println(this.getRollMessage(activePlayer.getTurnScore()));
					break;

				}

				activePlayer.setRollScore(skunkDice.getLastRoll());
				ui.println(this.getRollMessage(activePlayer.getTurnScore()));
				wantsToRollStr = ui.promptReadAndReturn("Roll again? y or n");
				wantsToRoll = 'y' == wantsToRollStr.toLowerCase().charAt(0);

			}

			ui.println("End of turn for " + playerNames[activePlayerIndex]);
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
				ui.println(playerNames[i] + " -- " + players.get(i).turnScore + " -- " + players.get(i).roundScore
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
			ui.println("Last round for player " + playerNames[activePlayerIndex] + "...");
			activePlayer.setTurnScore(0);

			String wantsToRollStr = ui.promptReadAndReturn("Roll? y or n");
			boolean wantsToRoll = 'y' == wantsToRollStr.toLowerCase().charAt(0);

			while (wantsToRoll)
			{
				skunkDice.roll();
				ui.println("Roll is " + skunkDice.toString() + "\n");

				kitty += this.getChipPenalty();
				activePlayer.setNumberChips(activePlayer.getNumberChips() - this.getChipPenalty());
				activePlayer.setTurnScore(this.getTurnScore());
				
				if (skunkDice.getLastRoll() == 2)
				{
					wantsToRoll = false;
					ui.println(this.getRollMessage(activePlayer.getTurnScore()));
					break;
				}
				else if (skunkDice.getLastRoll() == 3)
				{
					wantsToRoll = false;
					ui.println(this.getRollMessage(activePlayer.getTurnScore()));
				}
				else if (skunkDice.getDie1().getLastRoll() == 1 || skunkDice.getDie2().getLastRoll() == 1)
				{
					activePlayer.setRoundScore(0);
					wantsToRoll = false;
					ui.println(this.getRollMessage(activePlayer.getTurnScore()));
				}
				else
				{
					ui.println(this.getRollMessage(activePlayer.getTurnScore()));
					ui.println("Scoreboard: ");
					ui.println("Kitty has " + kitty);
					ui.println("player name -- turn score -- round score -- total chips");
					ui.println("-----------------------");

					for (int pNumber = 0; pNumber < numberOfPlayers; pNumber++)
					{
						ui.println(playerNames[pNumber] + " -- " + players.get(pNumber).turnScore + " -- "
								+ players.get(pNumber).roundScore + " -- " + players.get(pNumber).getNumberChips());
					}
					ui.println("-----------------------");

					wantsToRollStr = ui.promptReadAndReturn("Roll again? y or n");
					wantsToRoll = 'y' == wantsToRollStr.toLowerCase().charAt(0);
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
			ui.println("Final round score for " + playerNames[player] + " is " + nextPlayer.getRoundScore() + ".");
			if (nextPlayer.getRoundScore() > winnerScore)
			{
				winner = player;
				winnerScore = nextPlayer.getRoundScore();
			}
		}

		ui.println("Round winner is " + playerNames[winner] + " with score of " + players.get(winner).getRoundScore());
		players.get(winner).setNumberChips(players.get(winner).getNumberChips() + kitty);
		ui.println("\nRound winner earns " + kitty + ", finishing with " + players.get(winner).getNumberChips());

		ui.println("\nFinal scoreboard for this round:");
		ui.println("player name -- round score -- total chips");
		ui.println("-----------------------");

		for (int pNumber = 0; pNumber < numberOfPlayers; pNumber++)
		{
			ui.println(playerNames[pNumber] + " -- " + players.get(pNumber).roundScore + " -- "
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
