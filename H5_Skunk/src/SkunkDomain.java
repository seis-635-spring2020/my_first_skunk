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

		this.playerNames = new String[20]; //Why 20?
		this.players = new ArrayList<Player>();
		this.skunkDice = new Dice();
		this.wantsToQuit = false;
		this.oneMoreRoll = false;
	}
	
	public void getPlayerInfo(int playerNumber) { //Moved this to a new mthod
		ui.print("Enter name of player " + (playerNumber + 1) + ": ");
		playerNames[playerNumber] = StdIn.readLine();
		this.players.add(new Player(50));
	}
	
	public boolean Set_Kitty_And_TurnScore_For_Skunk(int chipsLost) {
		kitty += chipsLost;
		activePlayer.setNumberChips(activePlayer.getNumberChips() - chipsLost);
		activePlayer.setTurnScore(0);
		return false;
	}
	
	public boolean prompt_for_keep_rolling() {
		String wantsToRollStr = ui.promptReadAndReturn("Roll? y or n");
		boolean wantsToRoll = ('y' == wantsToRollStr.toLowerCase().charAt(0)); //Just to make it more clear.
		return wantsToRoll;
	}

	public boolean run()
	{
		ui.println("Welcome to Skunk 0.47\n");

		String numberPlayersString = skunkUI.promptReadAndReturn("How many players?");
		this.numberOfPlayers = Integer.parseInt(numberPlayersString);

		for (int playerNumber = 0; playerNumber < numberOfPlayers; playerNumber++)
		{
			getPlayerInfo(playerNumber);
		}
		activePlayerIndex = 0;
		activePlayer = players.get(activePlayerIndex);

		ui.println("Starting game...\n");
		boolean gameNotOver = true;

		while (gameNotOver)
		{
			ui.println("Next player is " + playerNames[activePlayerIndex] + ".");
			boolean wantsToRoll =  prompt_for_keep_rolling(); //Just to make it more clear.

			while (wantsToRoll)
			{
				activePlayer.setRollScore(0);
				skunkDice.roll();
				if (skunkDice.getLastRoll() == 2)
				{
					
					ui.println("Two Skunks! You lose the turn, the round score, plus pay 4 chips to the kitty");
					activePlayer.setRoundScore(0);
					wantsToRoll = Set_Kitty_And_TurnScore_For_Skunk(4);
					break;
				}
				else if (skunkDice.getLastRoll() == 3)
				{
					ui.println("Skunks and Deuce! You lose the turn, the turn score, plus pay 2 chips to the kitty");
					
					wantsToRoll = Set_Kitty_And_TurnScore_For_Skunk(2);
					break;
				}
				else if (skunkDice.getDie1().getLastRoll() == 1 || skunkDice.getDie2().getLastRoll() == 1)
				{
					ui.println("One Skunk! You lose the turn, the turn score, plus pay 1 chip to the kitty");
					Set_Kitty_And_TurnScore_For_Skunk(1);
					break;

				}

				activePlayer.setRollScore(skunkDice.getLastRoll());
				activePlayer.setTurnScore(activePlayer.getTurnScore() + skunkDice.getLastRoll());
				ui.println(
						"Roll of " + skunkDice.toString() + ", gives new turn score of " + activePlayer.getTurnScore());

				wantsToRoll =  prompt_for_keep_rolling();

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

			boolean wantsToRoll =  prompt_for_keep_rolling();

			while (wantsToRoll)
			{
				skunkDice.roll();
				ui.println("Roll is " + skunkDice.toString() + "\n");

				if (skunkDice.getLastRoll() == 2)
				{
					ui.println("Two Skunks! You lose the turn, the turn score, plus pay 4 chips to the kitty");
					activePlayer.setRoundScore(0);
					wantsToRoll = Set_Kitty_And_TurnScore_For_Skunk(4);
					break;
				}
				else if (skunkDice.getLastRoll() == 3)
				{
					ui.println("Skunks and Deuce! You lose the turn, the turn score, plus pay 2 chips to the kitty");
					wantsToRoll = Set_Kitty_And_TurnScore_For_Skunk(2);

				}
				else if (skunkDice.getDie1().getLastRoll() == 1 || skunkDice.getDie2().getLastRoll() == 1)
				{
					ui.println("One Skunk! You lose the turn, the turn core, plus pay 1 chip to the kitty");
					wantsToRoll = Set_Kitty_And_TurnScore_For_Skunk(1);
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
						ui.println(playerNames[pNumber] + " -- " + players.get(pNumber).turnScore + " -- "
								+ players.get(pNumber).roundScore + " -- " + players.get(pNumber).getNumberChips());
					}
					ui.println("-----------------------");

					
					wantsToRoll = prompt_for_keep_rolling();
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

}
