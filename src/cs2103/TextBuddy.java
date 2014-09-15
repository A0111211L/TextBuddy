package cs2103;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TextBuddy { 

	private static final String ERROR_FILE_NAME_NOT_SPECIFIED = "Error, file name not specified";
	private static final String DELETE_ERROR = "Invalid delete command";

	private static Scanner sc = new Scanner(System.in);
	private static String fileName;
	private static ArrayList<String> storage = new ArrayList<String>();

	public static void main(String[] args) throws IOException {
		doInitialize(args);
		printWelcomeMessage();
		while(true) {
			System.out.print("command: ");
			String userCommand = sc.next();
			executeCommand(userCommand);
		}
	}

	private static void printWelcomeMessage() {
		System.out.println("Welcome to TextBuddy. " + fileName + " is ready for use");
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
	static void executeCommand(String userCommand) throws IOException {
		switch(userCommand) {
		case "add" :
			doAdd();
			break;

		case "delete" :
			try {
				int num = Integer.parseInt(sc.nextLine().trim());
				doDelete(num);
			}catch (Exception e) {
				System.out.println(DELETE_ERROR);
			}
			break;

		case "display" :
			doDisplay();
			break;

		case "clear" :
			doClear();
			break;

		case "exit" :
			doExit();
			break;

		default:
			System.out.println("Invalid Command");
			sc.nextLine();
			break;
		}
	}

	private static void doAdd() {
		String sentence = sc.nextLine();
		sentence = sentence.trim();
		System.out.println("added to " + fileName + ": \"" + sentence + "\"");
		storage.add(sentence);
	}

	private static void doDelete(int num) {
		String deleted = storage.get(num - 1);
		storage.remove(num - 1);
		System.out.println("deleted from " + fileName + ": \"" + deleted + "\"");
	}

	private static void doDisplay() {
		if (storage.size() == 0)
			System.out.println(fileName + " is empty");

		for (int i = 1; i < storage.size() + 1; i++) {
			System.out.println(i + ". " + storage.get(i-1));
		}
	}

	private static void doClear() {
		storage.clear();	
		System.out.println("all content deleted from " + fileName);
	}

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

	static boolean isAdded(String text) {
		if (storage.get(storage.size() - 1).equals(text)) {
			return true;
		}
		else
			return false;		
	}
}
