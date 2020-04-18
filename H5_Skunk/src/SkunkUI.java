import edu.princeton.cs.introcs.*;

public class SkunkUI implements UI
{
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
	
	public void printEndTurnReport(String playerName, int previousRoundScore, int turnScore)
	{
		int newRoundScore = previousRoundScore + turnScore;
		
		println("End of turn for " + playerName);
		println("Score for this turn is " + previousRoundScore + ", added to...");
		println("Previous round score of " + turnScore);
		println("Giving new round score of " + newRoundScore);
	}

}
