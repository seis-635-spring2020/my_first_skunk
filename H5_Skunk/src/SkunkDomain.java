import java.util.ArrayList;
import edu.princeton.cs.introcs.*;

public class SkunkDomain
{
	public SkunkUI skunkUI;
	public int numberOfPlayers;
	public String[] playerNames;
	public ArrayList<Player> players;
	public int kitty;
	private int prevRoundScore;

	public Player activePlayer;
	public int activePlayerIndex;

	public boolean wantsToQuit;
	public boolean oneMoreRoll;

	public Dice skunkDice;

	public SkunkDomain(SkunkUI ui)
	{
		this.skunkUI = ui;

		this.playerNames = new String[20];
		this.players = new ArrayList<Player>();
		this.skunkDice = new Dice();
		this.wantsToQuit = false;
		this.oneMoreRoll = false;
	}

	public boolean run()
	{
		skunkUI.welcome(); //REFACTORED
		
		String numberPlayersString = skunkUI.numberOfPlayers(); //REFACTORED - moved message to SkunkUI
		this.numberOfPlayers = Integer.parseInt(numberPlayersString);

		for (int playerNumber = 0; playerNumber < numberOfPlayers; playerNumber++)
		{
			playerNames[playerNumber] = skunkUI.nameOfPlayer(playerNumber + 1); //REFACTORED- moved message to SkunkUI
			this.players.add(new Player(50));
		}
		
		activePlayerIndex = 0;
		activePlayer = players.get(activePlayerIndex);

		skunkUI.gameBegins(); //REFACTORED- moved message to SkunkUI
		boolean gameNotOver = true;

		while (gameNotOver)
		{
			
			
			String wantsToRollStr = skunkUI.wantToRoll(playerNames[activePlayerIndex]); //REFACTORED- moved message to SkunkUI
			boolean wantsToRoll = 'y' == wantsToRollStr.toLowerCase().charAt(0);

			while (wantsToRoll)
			{
				activePlayer.setRollScore(0);
				skunkDice.roll();
				if (skunkDice.getLastRoll() == 2) //if double skunk
				{
					doubleSkunk(); //REFACTORED - moved code to method below.
					wantsToRoll = false;
					break;
				}
				else if (skunkDice.getLastRoll() == 3) //if skunk deuce
				{
					skunkDeuce();//REFACTORED - moved code to method below.
					wantsToRoll = false;
					break;
				}
				else if (skunkDice.getDie1().getLastRoll() == 1 || skunkDice.getDie2().getLastRoll() == 1) //if single skunk
				{
					singleSkunk();//REFACTORED - moved code to method below.
					wantsToRoll = false;
					break;

				}

				activePlayer.setRollScore(skunkDice.getLastRoll());
				activePlayer.setTurnScore(activePlayer.getTurnScore() + skunkDice.getLastRoll());
				
				skunkUI.rollResponse(skunkDice.toString(), activePlayer.getTurnScore());//REFACTORED- moved message to SkunkUI
			

				wantsToRollStr = skunkUI.wantToRollAgain(); //REFACTORED- moved message to SkunkUI
				wantsToRoll = 'y' == wantsToRollStr.toLowerCase().charAt(0);
				
			}
			prevRoundScore = activePlayer.getRoundScore(); //REFACTORED - moved messages to SkunkUI
			activePlayer.setRoundScore(activePlayer.getRoundScore() + activePlayer.getTurnScore());
			skunkUI.endTurn(playerNames[activePlayerIndex], activePlayer.getTurnScore(), prevRoundScore, activePlayer.getRoundScore());

			if (activePlayer.getRoundScore() >= 100)
				gameNotOver = false;
				
				skunkUI.scoreBoard(kitty); //REFACTORED - moved messages to SkunkUI

			for (int i = 0; i < numberOfPlayers; i++)
			{
				skunkUI.scoreBoardRows(playerNames[i], players.get(i).turnScore, players.get(i).roundScore, players.get(i).getNumberChips());

			}
			
			skunkUI.passTurn(); //REFACTORED - moved messages to SkunkUI


			activePlayerIndex = (activePlayerIndex + 1) % numberOfPlayers;
			activePlayer = players.get(activePlayerIndex);

		}
		// last round: everyone but last activePlayer gets another shot
		skunkUI.lastTurnMsg(); //REFACTORED - moved message to SkunkUI
		for (int i = activePlayerIndex, count = 0; count < numberOfPlayers - 1; i = (i++) % numberOfPlayers, count++)
		{
			activePlayer.setTurnScore(0);
			skunkUI.lastTurn(playerNames[activePlayerIndex]);//REFACTORED - moved message to SkunkUI
			String wantsToRollStr = skunkUI.wantToRollLast(); //REFACTORED- moved message to SkunkUI
			boolean wantsToRoll = 'y' == wantsToRollStr.toLowerCase().charAt(0);


			while (wantsToRoll)
			{
				skunkDice.roll();
				skunkUI.rollIs(skunkDice.toString());
			
				if (skunkDice.getLastRoll() == 2) //BUG FIX - Double skunk handled as Single Skunk originally
				{
					doubleSkunk(); //REFACTORED - moved code to method below.
					wantsToRoll = false;
					break;
				}
				else if (skunkDice.getLastRoll() == 3)
				{
					skunkDeuce(); //REFACTORED - moved code to method below.
					wantsToRoll = false;

				}
				else if (skunkDice.getDie1().getLastRoll() == 1 || skunkDice.getDie2().getLastRoll() == 1)
				{//BUG FIX - Single Skunk handled as Double Skunk originally
					singleSkunk(); //REFACTORED - moved code to method below.
					wantsToRoll = false;
				}
				else
				{
					activePlayer.setTurnScore(activePlayer.getRollScore() + skunkDice.getLastRoll());
					skunkUI.rollResponse(skunkDice.toString(), activePlayer.getTurnScore());//REFACTORED- moved message to SkunkUI
					
					skunkUI.scoreBoard(kitty);//REFACTORED- moved message to SkunkUI

					for (int pNumber = 0; pNumber < numberOfPlayers; pNumber++)
					{
						skunkUI.scoreBoardRows(playerNames[pNumber], players.get(pNumber).turnScore, players.get(pNumber).roundScore, players.get(pNumber).getNumberChips());
					}
					skunkUI.dividerLine();
					wantsToRollStr = skunkUI.wantToRoll(playerNames[activePlayerIndex]);//REFACTORED- moved message to SkunkUI
					wantsToRoll = 'y' == wantsToRollStr.toLowerCase().charAt(0);
				}

			}

			activePlayer.setTurnScore(activePlayer.getRollScore() + skunkDice.getLastRoll());
			skunkUI.finalRoundResponse(skunkDice.toString(), activePlayer.getRollScore());//REFACTORED- moved message to SkunkUI

		}

		int winner = 0;
		int winnerScore = 0;

		for (int player = 0; player < numberOfPlayers; player++)
		{
			Player nextPlayer = players.get(player);
			skunkUI.finalRoundScore(playerNames[player], nextPlayer.getRoundScore());
			if (nextPlayer.getRoundScore() > winnerScore)
			{
				winner = player;
				winnerScore = nextPlayer.getRoundScore();
			}
		}
		players.get(winner).setNumberChips(players.get(winner).getNumberChips() + kitty);
		skunkUI.finalScoreBoard(playerNames[winner], players.get(winner).getRoundScore(), kitty, players.get(winner).getNumberChips());


		for (int pNumber = 0; pNumber < numberOfPlayers; pNumber++)
		{
			skunkUI.scoreBoardRows(playerNames[pNumber], players.get(pNumber).roundScore, players.get(pNumber).getNumberChips());
		}

		skunkUI.dividerLine();
		return true;
	}

	private void doubleSkunk()
	{
		skunkUI.doubleSkunk(); //REFACTORED- moved message to SkunkUI
		kitty += 4;
		activePlayer.setNumberChips(activePlayer.getNumberChips() - 4);
		activePlayer.setTurnScore(0);
		activePlayer.setRoundScore(0);
	}
	
	private void skunkDeuce()
	{
		skunkUI.skunkDeuce(); //REFACTORED- moved message to SkunkUI
		kitty += 2;
		activePlayer.setNumberChips(activePlayer.getNumberChips() - 2);
		activePlayer.setTurnScore(0);
	}

	private void singleSkunk()
	{
		skunkUI.singleSkunk(); //REFACTORED- moved message to SkunkUI
		kitty += 1;
		activePlayer.setNumberChips(activePlayer.getNumberChips() - 1);
		activePlayer.setTurnScore(0);
	}



}
