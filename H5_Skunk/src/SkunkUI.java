import edu.princeton.cs.introcs.*;

public class SkunkUI implements UI
{

	public SkunkDomain skunkDomain;

	public void setDomain(SkunkDomain skunkDomain)
	{
		this.skunkDomain = skunkDomain;

	}

	public String promptReadAndReturn(String question)
	{
		StdOut.print(question + " => ");
		String result = StdIn.readLine();
		return result;
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

	public void print(String toPrint)
	{
		StdOut.print(toPrint);

	}

	public void println(String toPrint)
	{
		StdOut.println(toPrint);

	}
	
	public void welcome()
	{
		StdOut.println("Welcome to Skunk 0.47\n");
	}
	
	public String numberOfPlayers()
	{
		return promptReadAndReturn("How many players?");
		
	}
	public String nameOfPlayer(int playerNum)
	{	
		return promptReadAndReturn("Enter name of player " + (playerNum) + ": ");

	}

	public void gameBegins()
	{
		StdOut.println("Starting game...\n");
	}
	
	public String wantToRoll(String playerName)
	{
		StdOut.println("Next player is " + playerName + ".\n");
		return promptReadAndReturn("Roll? y or n");
	}
	
	public String wantToRollLast()
	{
		return promptReadAndReturn("Roll? y or n");
	}
	
	public String wantToRollAgain() 
	{
		return promptReadAndReturn("Roll again? y or n");
	}
	
	public void doubleSkunk()
	{
		StdOut.println("Two Skunks! You lose the turn, the round score, plus pay 4 chips to the kitty");
	}
	
	public void skunkDeuce() //REFACTORED- moved message to SkunkUI
	{
		StdOut.println("Skunks and Deuce! You lose the turn, the turn score, plus pay 2 chips to the kitty");
	}
	
	public void singleSkunk() //REFACTORED- moved message to SkunkUI
	{
		StdOut.println("One Skunk! You lose the turn, the turn score, plus pay 1 chip to the kitty");
	}
	
	public void endTurn(String playerName, int turnScore, int prevRoundScore, int roundScore)
	{
		StdOut.println("");
		StdOut.println("End of turn for " + playerName);
		StdOut.println("Score for this turn is " + turnScore + ", added to...");
		StdOut.println("Previous round score of " + prevRoundScore);
		StdOut.println("Giving new round score of " + roundScore);

		StdOut.println("");
	}
	
	public void scoreBoard(int kitty)
	{
		StdOut.println("Scoreboard: ");
		StdOut.println("Kitty has " + kitty);
		StdOut.println("player name -- turn score -- round score -- chips");
		StdOut.println("-----------------------");
	}
	
	public void finalScoreBoard(String playerName, int roundScore, int kitty, int chipTotal)
	{
		StdOut.println("Round winner is " + playerName + " with score of " + roundScore);
		StdOut.println("\nRound winner earns " + kitty + ", finishing with " + chipTotal);

		StdOut.println("\nFinal scoreboard for this round:");
		StdOut.println("player name -- round score -- total chips");
		StdOut.println("-----------------------");
	}
	
	public void scoreBoardRows(String name, int turnScore, int roundScore, int chips)
	{
		StdOut.println(name + " -- " + turnScore + " -- " + roundScore
				+ " -- " + chips);
	}

	public void scoreBoardRows(String name, int roundScore, int chips)
	{
		StdOut.println(name + " -- " + roundScore + " -- " + chips);
	}
	
	public void dividerLine()
	{
		StdOut.println("-----------------------");
	}
	public void passTurn()
	{
		dividerLine();

		StdOut.println("Turn passes to right...");
	}

	public void lastTurnMsg()
	{
		StdOut.println("Last turn for all...");
	}
	
	public void lastTurn(String playerName)
	{
		StdOut.println("Last round for player " + playerName + "...");
	
	}
	
	public void rollResponse(String diceRoll, int turnScore)
	{
		StdOut.println("Roll of " + diceRoll + ", gives new turn score of " + turnScore);
	}
	
	public void rollIs(String diceRoll)
	{
		StdOut.println("Roll is " + diceRoll + "\n");
	}
	
	public void finalRoundResponse(String diceRoll, int playerRoll)
	{
		StdOut.println("Last roll of " + diceRoll + ", giving final round score of "
				+ playerRoll);
	}
	
	public void finalRoundScore(String playerName, int playerScore)
	{
		StdOut.println("Final round score for " + playerName + " is " + playerScore + ".");
	}
}
