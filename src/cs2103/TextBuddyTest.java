package cs2103;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class TextBuddyTest {
	
	@Test
	public void testIsAdded() throws IOException {
		
		// normal text
		TextBuddy.executeCommand("add chicken wings");
		TextBuddy.executeCommand("exit");
		assertTrue(TextBuddy.isAdded("chicken wings"));
	}

}
