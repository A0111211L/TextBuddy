package cs2103;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class TextBuddyTest {
	
	@Test
	public void testIsAdded() throws IOException {
		// normal text
		TextBuddy.executeCommand("add chicken wings");
		assertTrue(TextBuddy.isAdded("chicken wings"));
		
		// invalid command
		TextBuddy.executeCommand("addd KFC");
		assertFalse(TextBuddy.isAdded("KFC"));
		
		// add whitespace
		TextBuddy.executeCommand("add ");
		assertTrue(TextBuddy.isAdded(""));
		
		// add text with word add inside
		TextBuddy.executeCommand("add ADDadd add me");
		assertTrue(TextBuddy.isAdded("ADDadd add me"));
		
	}

}
