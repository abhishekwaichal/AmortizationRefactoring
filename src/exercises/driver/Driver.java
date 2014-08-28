/**
 * 
 */
package exercises.driver;

import java.io.IOException;

import exercises.amortizationSchedule.AmortizationSchedule;
import exercises.amortizationSchedule.AmortizationStrategy;
import exercises.util.InputOutputUtils;

/**
 * @author Abhishek
 * Contains the main class.
 * 		- Accepts user inputs and instantiates the required class.
 * 	 	- Invokes the strategy method to calculate and compute the Amortization Schedule. 
 *
 */
public class Driver {

	
	public static void main(String[] args) {
		
		String[] userPrompts = {
				"\nPlease enter the amount you would like to borrow: ",
				"\nPlease enter the annual percentage rate used to repay the loan: ",
				"\nPlease enter the term, in years, over which the loan is repaid: " };
		
		String line = "";
		double amount = 0;
		double apr = 0;
		int years = 0;
		
		for (int i = 0; i < userPrompts.length;) {
			
			String userPrompt = userPrompts[i];

			try {
				line = InputOutputUtils.readLine(userPrompt);
			} catch (IOException e) {
				System.err.println("An IOException was encountered.Terminating program.");
				return;
			}

			boolean isValidValue = true;
			
			try {
				switch (i) {
				case 0:
					amount = Double.parseDouble(line);
					if (AmortizationSchedule.isValidBorrowAmount(amount) == false) {
						isValidValue = false;
						double range[] = AmortizationSchedule.getBorrowAmountRange();
						InputOutputUtils.print("ERROR: Enter a valid value for amount to be borrowed between "+ range[0] + " and " + range[1] + ". \n");
					}
					break;
				case 1:
					apr = Double.parseDouble(line);
					if (AmortizationSchedule.isValidAPRValue(apr) == false) {
						isValidValue = false;
						double range[] = AmortizationSchedule.getAprRange();
						InputOutputUtils.print("ERROR: Enter a valid for annual percentage rate value between "+ range[0] + " and " + range[1] + ". \n");
					}
					break;
				case 2:
					years = Integer.parseInt(line);
					if (AmortizationSchedule.isValidTerm(years) == false) {
						isValidValue = false;
						int range[] = AmortizationSchedule.getTermRange();
						InputOutputUtils.print("ERROR: Enter a valid value for term between "+ range[0] + " and " + range[1] + " years. \n");
					}
					break;
				}
			} catch (NumberFormatException e) {
				isValidValue = false;
			}

			if (isValidValue) {
				i++;
			} else {
				InputOutputUtils.print("An invalid value was entered.\n");
			}
		}
		
		try {
			// Program to Interface: Gives flexibility to the driver class to use any strategy/implementation at runtime.		
			AmortizationStrategy amortizationSchedule = new AmortizationSchedule(amount, apr, years);
			InputOutputUtils.print("\nAmortization Schedule for entered values:\n");
			amortizationSchedule.displayAmortizationSchedule();
			
		} catch (IllegalArgumentException e) {
			System.err.println("Unable to process the values entered. Terminating program.");
		}
		
	}
	
}
