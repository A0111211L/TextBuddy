package cs2103;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

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
	
	@Test
	public void testdoClear() throws IOException {
		// check if the "clear" command returns the right status message
		assertEquals("all content deleted from null", TextBuddy.executeCommand("clear"));
		//check if the file was actually cleared
		assertEquals(0, TextBuddy.getLineCount());
	
	}
	
	@Test
	public void testdoSort() throws IOException {
		ArrayList<String> check = new ArrayList<String>();
		check.add("1234");
		check.add("aaaaa");
		check.add("helloworld");
		check.add("meow");
		// add content into arraylist
		TextBuddy.executeCommand("add helloworld");
		TextBuddy.executeCommand("add aaaaa");
		TextBuddy.executeCommand("add 1234");
		TextBuddy.executeCommand("add    meow");
		// check if the "sort" command returns the right status message
		assertEquals("content sorted", TextBuddy.executeCommand("sort"));
		// check if the content is sorted accordingly
		assertEquals(check, TextBuddy.isSorted());
		
	}
	
	@Test
	public void testdoSearch() throws IOException {
		ArrayList<String> testSearch = new ArrayList<String>();
		testSearch.add("YO");
		testSearch.add("Hey yo yo");
		testSearch.add("yo this is me");
		// add content into arraylist
		TextBuddy.executeCommand("add yooooooooooooooo");
		TextBuddy.executeCommand("add mayo");
		TextBuddy.executeCommand("add yyoo and hey");
		TextBuddy.executeCommand("add y.o");
		TextBuddy.executeCommand("add YO");
		TextBuddy.executeCommand("add Hey yo yo");
		TextBuddy.executeCommand("add oy oy oy");
		TextBuddy.executeCommand("add yo this is me");
		// check if the word searched is found in the strings stored in containWord arraylist
		assertEquals(testSearch, TextBuddy.doSearch("yo"));
	
	} 

}
