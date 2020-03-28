
public class MockUI implements UI {

	public String ActualMessage = "";
	private String[] answers;
	public int index = -1;
	
	public MockUI(SkunkDomain domain, String[] answers)
	{
		this.setDomain(domain);
		this.answers = answers;
	}
	
	public SkunkDomain skunkDomain;

	public void setDomain(SkunkDomain skunkDomain)
	{
		this.skunkDomain = skunkDomain;

	}
	
	@Override
	public String promptReadAndReturn(String question) {
		this.index += 1;
		this.index %= this.answers.length;
		this.ActualMessage += question + " => " + this.answers[this.index] + "\n";
		return this.answers[this.index];
	}

	@Override
	public void print(String msg) {
		this.ActualMessage = msg;
	}

	@Override
	public void println(String msg) {
		this.ActualMessage = msg + "\n";
	}

}
