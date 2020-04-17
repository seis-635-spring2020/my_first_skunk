
public class Player
{
	public int rollScore;
	public int turnScore;
	public int roundScore;
	public int gameScore; // for now, same as roundScore
	public int numberChips;
	
	// Refactoring @author Rhyan Foo Kune
	// Change: add wantsToRoll attribute
	// Reason: encapsulation of information
	public boolean wantsToRoll;

	public Player()
	{
		this.rollScore = 0;
		this.turnScore = 0;
		this.roundScore = 0;
		this.gameScore = 0;
		this.numberChips = 50; // for now
		
		this.wantsToRoll = true;
	}

	public Player(int startingChipsPerPlayer)
	{
		this();
		this.numberChips = startingChipsPerPlayer;
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
	}

	public void addToRollScore(int lastTotal)
	{
		rollScore += lastTotal;
	}

	public void setRollScore(int newRollScore)
	{
		this.rollScore = newRollScore;
	}

	public int getRollScore()
	{
		return this.rollScore;
	}

	public int getNumberChips()
	{
		return this.numberChips;
	}

	public void setNumberChips(int newChips)
	{
		this.numberChips = newChips;
	}

	public void setTurnScore(int newScore)
	{
		this.turnScore = newScore;
	}

	public int getTurnScore()
	{
		return this.turnScore;
	}
	
	// Refactoring @author Rhyan Foo Kune
	// Change: add setter and getters for new wantsToRoll attribute
	public void setWantsToRoll(boolean wantsToQuit)
	{
		this.wantsToRoll = wantsToQuit;
	}

	public boolean getWantsToRoll()
	{
		return this.wantsToRoll;
	}
	
	

	public String getName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void setRoundScore(int i)
	{
		this.roundScore = i;
	}

	public int getRoundScore()
	{
		return this.roundScore;
	}

}
