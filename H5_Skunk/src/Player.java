
public class Player
{
	/*Fix: Originally these variable is PUBLIC, however, this is not good practice since
	 * it can be accessed by other classes if these are left public. Therefore,
	 * these are changed to private which is Encapsulate Field Refactoring method.*/
	private int rollScore;
	private int turnScore;
	private int roundScore;
	//private int gameScore; // Fix: removed from the Player class
	private int numberChips;

	public Player()
	{
		this.rollScore = 0;
		this.turnScore = 0;
		this.roundScore = 0;
		//this.gameScore = 0; //Fix: removed from the Player class
		this.numberChips = 50; // for now
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
