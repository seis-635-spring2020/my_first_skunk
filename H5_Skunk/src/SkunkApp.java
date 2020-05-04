public class SkunkApp
{
	public SkunkUI skunkUI;
	public SkunkDomain skunkDomain;
	public int numberOfPlayers;
	public String[] playerNames;
	private int playNamesLength = 20;

	public SkunkApp()
	{
		skunkUI = new SkunkUI();
		skunkDomain = new SkunkDomain(skunkUI);
		skunkUI.setDomain(skunkDomain);
		this.numberOfPlayers = 0;
		//this.playerNames = new String[20];
		this.playerNames = new String[playNamesLength];

	}

	/**
	 * Runs the app within an event loop
	 * 
	 * @return
	 */
	public boolean run()
	{
		return skunkDomain.run();
	}

	public static void main(String[] args)
	{
		new SkunkApp().run();
	}

}
