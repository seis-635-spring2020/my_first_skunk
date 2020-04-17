public class SkunkApp
{
	public SkunkUI skunkUI;
	public SkunkDomain skunkDomain;
	
	// Change 2: there was duplication 
	// this set of code was also found in the skunkDomain so I commented it out
//	public int numberOfPlayers;
//	public String[] playerNames;

	// start the skunk application to call player
	public SkunkApp()
	{
		skunkUI = new SkunkUI();
		skunkDomain = new SkunkDomain(skunkUI);
		skunkUI.setDomain(skunkDomain);
//		this.numberOfPlayers = 0;
//		this.playerNames = new String[20];
		
		// change 1: 
		//I took it out of the run method and added it to the skunk method.
		// it look redundant 
		skunkDomain.run();
	}

	/**
	 * Runs the app within an event loop
	 * 
	 * @return
	 */
//	public boolean run()
//	{
//		return skunkDomain.run();
//	}

	public static void main(String[] args)
	{
		new SkunkApp(); //.run();
	}

}
