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
 * Used for: 
 * 		- Printing formated strings to console.
 * 		- Reading input obtained from console.
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

	
	/**
	 * @param formatString
	 * 		String to be printed is formatted using this parameter 
	 * @param args
	 * 		Parameters to be output 
	 * 
	 *  Used to print given parameters in the given format.
	 */
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

	/**
	 * @param String
	 * 		String to be printed  
	 * 
	 *  Used to print given string.
	 */
	public static void print(String s) {
		printf("%s", s);
	}

	/**
	 * @param userPrompt
	 * 		Read the data from this string 
	 * 
	 *  Used to read the data input by user at console. 
	 */

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
