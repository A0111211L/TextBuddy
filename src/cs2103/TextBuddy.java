package cs2103;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TextBuddy { 

	private static final String ERROR_FILE_NAME_NOT_SPECIFIED = "Error, file name not specified";
	private static final String DELETE_ERROR = "Invalid delete command";
	private static final String WELCOME_MESSAGE = "Welcome to TextBuddy. %s is ready for use";
	private static final String ADD_MESSAGE = "added to %s: \"%s\"";
	private static final String DELETE_MESSAGE = "deleted from %s: \"%s\"";
	private static final String CLEAR_MESSAGE = "all content deleted from %s";
	private static final String EMPTY_MESSAGE = "%s is empty";
	private static final String SORT_MESSAGE = "content sorted";
	private static Scanner sc = new Scanner(System.in);
	private static String fileName;
	private static ArrayList<String> storage = new ArrayList<String>();

	public static void main(String[] args) throws IOException {
		doInitialize(args);
		printWelcomeMessage();
		while(true) {
			System.out.print("command: ");
			String userCommand = sc.nextLine();
			String feedback = executeCommand(userCommand);
			System.out.println(feedback);
		}
	}

	private static void printWelcomeMessage() {
		System.out.println(String.format(WELCOME_MESSAGE, fileName));
	}

	/*
	 * Method checks for file name and initializes fileName variable
	 */
	private static void doInitialize(String[] Args) throws IOException {
		if (Args.length == 0) {
			System.out.println(ERROR_FILE_NAME_NOT_SPECIFIED);
			System.exit(0);
		} else {
			fileName = Args[0];	
		}
	}

	/*
	 * Method contains list of userCommands which user may input
	 */
	static String executeCommand(String userCommand) throws IOException {
		String command = getFirstWord(userCommand);
		String remaining = removeFirstWord(userCommand);
		switch(command) {
		case "add" :
			return doAdd(remaining);

		case "delete" :
			try {
				int num = Integer.parseInt(remaining.trim());
				return doDelete(num);
			}catch (Exception e) {
				return (DELETE_ERROR);
			}

		case "display" :
			doDisplay();
			break;

		case "clear" :
			return doClear();

		case "exit" :
			doExit();
			break;
			
		case "sort" :
			return doSort();

		default:
			return("Invalid Command");
		}
		return "";
	}
	
	// add input to Arraylist
	private static String doAdd(String input) {
		input = input.trim();
		storage.add(input);
		return (String.format(ADD_MESSAGE, fileName, input));
	}
	
	// deleted selected string from Arraylist
	private static String doDelete(int num) {
		String deleted = storage.get(num - 1);
		storage.remove(num - 1);
		return (String.format(DELETE_MESSAGE, fileName, deleted));
	}
	
	// print out all entries contained in Arraylist
	private static void doDisplay() {
		if (storage.size() == 0)
			System.out.print(String.format(EMPTY_MESSAGE, fileName));

		for (int i = 1; i < storage.size() + 1; i++) {
			System.out.println(i + ". " + storage.get(i-1));
		}
	}
	
	// clear Arraylist of all entries
	private static String doClear() {
		storage.clear();	
		return (String.format(CLEAR_MESSAGE, fileName));
	}
	
	// method copies strings stored in Arraylist into file and exits
	private static void doExit() throws IOException {
		File f = new File(fileName);
		FileWriter fw = new FileWriter(f);
		BufferedWriter bw = new BufferedWriter(fw);
		for (int i = 0; i < storage.size(); i++) {
			bw.write(storage.get(i));
			bw.newLine();
		}
		bw.close();
		System.exit(0);
	}
	
	// method sorts content stored in Arraylist
	private static String doSort() {
		Collections.sort(storage);
		return SORT_MESSAGE;
	}
	
	
	private static String removeFirstWord(String userCommand) {
		return userCommand.replaceFirst(getFirstWord(userCommand), "").trim();
	}

	private static String getFirstWord(String userCommand) {
		String userWords = userCommand.trim().split("\\s+")[0];
		return userWords;
	}

	static int getLineCount() {
		return storage.size();
	}

	// checks if last line added to Arraylist is indeed the latest string
	static boolean isAdded(String text) {
		if (storage.get(storage.size() - 1).equals(text)) {
			return true;
		}
		else
			return false;		
	}
}
