
public class MockUI_Yes implements UI {

	public String ActualMessage;
	
	@Override
	public String promptReadAndReturn(String question) {
		this.ActualMessage = question;
		return "y";
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
