import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestMockUI {

	@Test
	void testPromptReadAndReturn() {
		MockUI blah = new MockUI(null, new String[] {"y", "n", "y", "n"});
		String expected = "Hello => y\n" +
				"Hello => n\n" +
				"Hello => y\n" +
				"Hello => n\n" +
				"Hello => y\n" +
				"Hello => n\n" +
				"Hello => y\n" +
				"Hello => n\n";
		for (int i = 0; i < 8; i++) {
			String prompt_expected = "y";
			if (i %2 == 1) prompt_expected = "n";
				
			String prompt_actual = blah.promptReadAndReturn("Hello");
			assert(i % 4 == blah.index);
			assert(prompt_actual == prompt_expected);
		}
		assert(expected.contentEquals(blah.ActualMessage));
	}

	@Test
	void testPrint() {
		MockUI blah = new MockUI(null, new String[] {"y", "n", "y", "n"});
		blah.print("Hello");
		assert(blah.ActualMessage.contentEquals("Hello"));
	}

	@Test
	void testPrintln() {
		MockUI blah = new MockUI(null, new String[] {"y", "n", "y", "n"});
		blah.println("Hello");
		assert(blah.ActualMessage.contentEquals("Hello\n"));
	}

}
