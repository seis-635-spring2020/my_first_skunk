
public class Player
{
	private int rollScore;
	private int turnScore;
	private int roundScore;
	private int gameScore; // for now, same as roundScore
	private int numberChips;
	
	// Refactoring @author Rhyan Foo Kune
	// Change: add wantsToRoll attribute
	// Reason: encapsulation of information
	public boolean wantsToRoll;
	
	// Refactoring @author Rhyan Foo Kune
	// Change: add name attribute
	// Reason: encapsulation of information
	public String name;

	public Player()
	{
		this.rollScore = 0;
		this.turnScore = 0;
		this.roundScore = 0;
		this.gameScore = 0;
		this.numberChips = 50; // for now
		
		this.wantsToRoll = true;
	}

	public Player(String name, int startingChipsPerPlayer)
	{
		this();
		this.numberChips = startingChipsPerPlayer;
		this.name = name;
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
	// Change: add setter and getter for new wantsToRoll attribute
	public void setWantsToRoll(boolean wantsToQuit)
	{
		this.wantsToRoll = wantsToQuit;
	}

	public boolean getWantsToRoll()
	{
		return this.wantsToRoll;
	}
	
	// Refactoring @author Rhyan Foo Kune
	// Change: implement setter and getter for new name attribute
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
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
