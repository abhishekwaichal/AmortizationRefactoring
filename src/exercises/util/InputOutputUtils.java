/**
 * 
 */
package exercises.util;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.IllegalFormatException;


/**
 * @author Abhishek
 *
 */
public class InputOutputUtils {

	private static Console console = System.console();
	
	/**
	 * @return the console
	 */
	public static Console getConsole() {
		return console;
	}

	/**
	 * @param console the console to set
	 */
	public static void setConsole(Console console) {
		InputOutputUtils.console = console;
	}

	
	public static void printf(String formatString, Object... args) {
		try {
			if (getConsole() != null) {
				getConsole().printf(formatString, args);
			} else {
				System.out.print(String.format(formatString, args));
			}
		} catch (IllegalFormatException e) {
			System.err.println("Error printing...");
		}
	}

	
	public static void print(String s) {
		printf("%s", s);
	}

	
	public static String readLine(String userPrompt) throws IOException {
		
		String line = "";
		
		if (getConsole() != null) {
			line = getConsole().readLine(userPrompt);
		} else {															
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(System.in));
			print(userPrompt);
			line = bufferedReader.readLine();
		}
		line.trim();
		return line;
	}

	
}
