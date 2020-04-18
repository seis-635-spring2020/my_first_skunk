public class SkunkApp
{
	// Refactoring @author Rhyan Foo Kune
	// Change: make public attribute private
	private SkunkUI skunkUI;
	private SkunkDomain skunkDomain;

	public SkunkApp()
	{
		skunkUI = new SkunkUI();
		skunkDomain = new SkunkDomain(skunkUI);
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
