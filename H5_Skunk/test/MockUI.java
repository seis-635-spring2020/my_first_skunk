
public class MockUI extends SkunkUI implements UI {

	public String ActualMessage = "";
	private String[] answers;
	public int index = -1;
	
	public MockUI(String[] answers)
	{
		super();
		this.answers = answers;
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
