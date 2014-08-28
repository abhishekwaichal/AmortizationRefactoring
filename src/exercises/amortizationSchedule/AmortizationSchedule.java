/*
 * Exercise Details:
 * Amortization schedule program using Java.
 * 
 * The program should prompt the user for the amount he or she is borrowing,
 * the annual percentage rate used to repay the loan, the term, in years, over which the loan is repaid. 
 * 
 * The output should include:
 * 		The first column identifies the payment number.
 * 		The second column contains the amount of the payment.
 * 		The third column shows the amount paid to interest.
 * 		The fourth column has the current balance. The total payment amount and the interest paid fields.
 * 
 * Use appropriate variable names and comments. You choose how to display the output (i.e. Web, console). 
 * Amortization Formula - This will get you your monthly payment. Will need to update to Java.
 * 				M = P * (J / (1 - (Math.pow(1/(1 + J), N))));
 * Where:
 * 	 P = Principal
 * 	 I = Interest
 * 	 J = Monthly Interest in decimal form: I / (12 * 100)
 * 	 N = Number of months of loan
 * 	 M = Monthly Payment Amount
 * 
 * To create the amortization table, create a loop in your program and follow these steps:
 * 1. Calculate H = P x J, this is your current monthly interest
 * 2. Calculate C = M - H, this is your monthly payment minus your monthly interest, so it is the amount of principal you pay for that month
 * 3. Calculate Q = P - C, this is the new balance of your principal of your loan.
 * 4. Set P equal to Q and go back to Step 1: You thusly loop around until the value Q (and hence P) goes to zero.
 *  
*/

package exercises.amortizationSchedule;

import exercises.util.InputOutputUtils;


/**
 * 
 *  The <code>AmortizationSchedule</code>  
 *  is an amortization schedule program using Java.
 *  This is one of the strategy that has been developed to calculate the Amortization schedule.
 *  
 */
public class AmortizationSchedule implements AmortizationStrategy{

	private long amountBorrowed = 0; 										// In cents
	private double apr = 0d;
	private int initialTermMonths = 0;
	private double monthlyInterest = 0d;
	private long monthlyPaymentAmount = 0; 									// In cents

	private final double monthlyInterestDivisor = 12d * 100d;
	
	private static final double[] BORROW_AMOUNT_RANGE = new double[] { 0.01d,1000000000000d };
	private static final double[] APR_RANGE = new double[] { 0.000001d, 100d };
	private static final int[] TERM_RANGE = new int[] { 1, 1000000 };

	/**
	 * Contructor
	 * Default Constructor 
	 * 
	 */
	public AmortizationSchedule() {
		super();
	}

	/**
	 * Contructor 
	 * Parameterized constructor
	 * 
	 * @param amountBorrowed
	 * 			The amount to be set
	 * @param apr
	 * 			The rate to be set
	 * @param initialTermMonths
	 * 			The term to be set
	 * @param monthlyInterest
	 * 			The monthly interest to be set
	 * @param monthlyPaymentAmount
	 * 			The monthly amount to be paid
	 */
	public AmortizationSchedule(long amountBorrowed, double apr,
			int initialTermMonths, double monthlyInterest,
			long monthlyPaymentAmount) {
		super();
		this.amountBorrowed = amountBorrowed;
		this.apr = apr;
		this.initialTermMonths = initialTermMonths;
		this.monthlyInterest = monthlyInterest;
		this.monthlyPaymentAmount = monthlyPaymentAmount;
	}

	/**
	 * Contructor
	 * Custom Parameterized constructor
	 * 
	 * @param amount
	 * 			The amount to be set
	 * @param interestRate
	 * 			The rate to be set
	 * @param years
	 * 			The term to be set
	 */
	public AmortizationSchedule(double amount, double interestRate, int years)
			throws IllegalArgumentException {
		
		if ((isValidBorrowAmount(amount) == false)
				|| (isValidAPRValue(interestRate) == false)
				|| (isValidTerm(years) == false)) {
			throw new IllegalArgumentException();
		}
		amountBorrowed = Math.round(amount * 100);
		apr = interestRate;
		initialTermMonths = years * 12;
		monthlyPaymentAmount = calculateMonthlyPayment();					// The following shouldn't happen with the available valid ranges
																			// for borrow amount, apr, and term; however, without range validation,
																			// monthlyPaymentAmount as calculated by calculateMonthlyPayment()
																			// may yield incorrect values with extreme input values
		if (monthlyPaymentAmount > amountBorrowed) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * @return the string representation of this instance
	 * Can be used for debuging purpose.
	 */
	@Override
	public String toString() {
		return "AmortizationSchedule [amountBorrowed=" + amountBorrowed
				+ ", apr=" + apr + ", initialTermMonths=" + initialTermMonths
				+ ", monthlyInterestDivisor=" + monthlyInterestDivisor
				+ ", monthlyInterest=" + monthlyInterest
				+ ", monthlyPaymentAmount=" + monthlyPaymentAmount + "]";
	}
	
	/**
	 * @return the amountBorrowed
	 */
	public long getAmountBorrowed() {
		return amountBorrowed;
	}

	/**
	 * @param amountBorrowed the amountBorrowed to set
	 */
	public void setAmountBorrowed(long amountBorrowed) {
		this.amountBorrowed = amountBorrowed;
	}

	/**
	 * @return the apr
	 */
	public double getApr() {
		return apr;
	}

	/**
	 * @param apr the apr to set
	 */
	public void setApr(double apr) {
		this.apr = apr;
	}

	/**
	 * @return the initialTermMonths
	 */
	public int getInitialTermMonths() {
		return initialTermMonths;
	}

	/**
	 * @param initialTermMonths the initialTermMonths to set
	 */
	public void setInitialTermMonths(int initialTermMonths) {
		this.initialTermMonths = initialTermMonths;
	}

	/**
	 * @return the monthlyInterest
	 */
	public double getMonthlyInterest() {
		return monthlyInterest;
	}

	/**
	 * @param monthlyInterest the monthlyInterest to set
	 */
	public void setMonthlyInterest(double monthlyInterest) {
		this.monthlyInterest = monthlyInterest;
	}

	/**
	 * @return the monthlyPaymentAmount
	 */
	public long getMonthlyPaymentAmount() {
		return monthlyPaymentAmount;
	}

	/**
	 * @param monthlyPaymentAmount the monthlyPaymentAmount to set
	 */
	public void setMonthlyPaymentAmount(long monthlyPaymentAmount) {
		this.monthlyPaymentAmount = monthlyPaymentAmount;
	}


	/**
	 * @return the monthlyInterestDivisor
	 */
	public double getMonthlyInterestDivisor() {
		return monthlyInterestDivisor;
	}

	/**
	 * @return the borrowAmountRange
	 */
	public static double[] getBorrowAmountRange() {
		return BORROW_AMOUNT_RANGE;
	}

	/**
	 * @return the aprRange
	 */
	public static double[] getAprRange() {
		return APR_RANGE;
	}

	/**
	 * @return the termRange
	 */
	public static int[] getTermRange() {
		return TERM_RANGE;
	}

	/**
	 * @param amount 
	 * 			The amount to be checked for validity
	 *  
	 * @return The result of validity check  
	 */
	public static boolean isValidBorrowAmount(double amount) {
		double range[] = getBorrowAmountRange();
		return ((range[0] <= amount) && (amount <= range[1]));
	}

	/**
	 * @param rate 
	 * 			The APR to be checked for validity
	 *  
	 * @return The result of validity check  
	 */
	public static boolean isValidAPRValue(double rate) {
		double range[] = getAprRange();
		return ((range[0] <= rate) && (rate <= range[1]));
	}

	/**
	 * @param years 
	 * 			The Term(No. of years) to be checked for validity
	 *  
	 * @return The result of validity check  
	 */
	public static boolean isValidTerm(int years) {
		int range[] = getTermRange();
		return ((range[0] <= years) && (years <= range[1]));
	}
	
	/**
	 *
	 *  	 M = P * (J / (1 - (Math.pow(1/(1 + J), N))));
	 *  	 Where:
	 *  		P = Principal
	 *  	 	I = Interest
	 *  		J = Monthly Interest in decimal form: I / (12 * 100)
	 *  		N = Number of months of loan
	 *  		M = Monthly Payment Amount
	 *  
	 * @return The result calculated monthly payment.  
	 */
	private long calculateMonthlyPayment() {

		this.setMonthlyInterest(getApr()/getMonthlyInterestDivisor());		// This is J = I / (12 * 100)	
		
		double tmp = Math.pow(1d + getMonthlyInterest(), -1);				// This is 1 / (1 + J)
		tmp = Math.pow(tmp, getInitialTermMonths());						// This is (1/(1 + J), N)
		tmp = Math.pow(1d - tmp, -1);										// This is 1 / (1 - (Math.pow(1/(1 + J), N))))
		
		double monthlyPayment = 
				getAmountBorrowed() * getMonthlyInterest() * tmp;			// M = P * (J / (1 - (Math.pow(1/(1 + J), N))))

		return Math.round(monthlyPayment);
	}

	
/**
 *	 The output should include:
 * 
 * 	 - The first column identifies the payment number.
 *   - The second column contains the amount of the payment.
 *   - The third column shows the amount paid to interest.
 *   - The fourth column has the current balance. The total payment amount and the interest paid fields.
 *   
 *   
 * 	 To create the amortization table, create a loop in your program and follow these steps:
 * 
 *   	1. Calculate H = P x J, this is your current monthly interest
 *   	2. Calculate C = M - H, this is your monthly payment minus your monthly interest, so it is the amount of principal you pay for that month
 *   	3. Calculate Q = P - C, this is the new balance of your principal of your loan.
 *   	4. Set P equal to Q and go back to Step 1: You thusly loop around until the value Q (and hence P) goes to zero.
*/
	
	@Override
	public void displayAmortizationSchedule() {
		
		String formatString = "%1$-20s%2$-20s%3$-20s%4$s,%5$s,%6$s\n";
		InputOutputUtils.printf(formatString, "PaymentNumber", "PaymentAmount",
				"PaymentInterest", "CurrentBalance", "TotalPayments",
				"TotalInterestPaid");
		long balance = getAmountBorrowed(); 								// Amount Borrowed;
		int paymentNumber = 0;
		long totalPayments = 0;
		long totalInterestPaid = 0; 										// Output in dollars
		
		formatString = "%1$-20d%2$-20.2f%3$-20.2f%4$.2f,%5$.2f,%6$.2f\n";
		
		InputOutputUtils.printf(formatString, paymentNumber++, 0d, 0d,
				((double) getAmountBorrowed()) / 100d,
				((double) totalPayments) / 100d,
				((double) totalInterestPaid) / 100d);
		
		final int maxNumberOfPayments = getInitialTermMonths() + 1;
		
		while ((balance > 0) && (paymentNumber <= maxNumberOfPayments)) {	// Calculate H = P x J, this is your current monthly interest.
			
			long curMonthlyInterest = 
					Math.round(((double) balance)* getMonthlyInterest());
																			
			long curPayoffAmount = balance + curMonthlyInterest;			// The amount required to payoff the loan
																			// The amount to payoff the remaining balance may be less than the
																			// calculated monthlyPaymentAmount.
			long curMonthlyPaymentAmount = 
					Math.min(getMonthlyPaymentAmount(),curPayoffAmount);	// It's possible that the calculated monthlyPaymentAmount is 0,
																			// or the monthly payment only covers the interest payment - i.e. no
																			// principal so the last payment needs to payoff the loan.
			if ((paymentNumber == maxNumberOfPayments)
					&& ((curMonthlyPaymentAmount == 0) 
							|| (curMonthlyPaymentAmount == curMonthlyInterest))) {
				curMonthlyPaymentAmount = curPayoffAmount;
														}					
			long curMonthlyPrincipalPaid = 
					curMonthlyPaymentAmount - curMonthlyInterest;			// Calculate C = M - H, this is your monthly payment minus your monthly 
																			// interest, so it is the amount of principal you pay for that month.
																			
			long curBalance = balance - curMonthlyPrincipalPaid;			// Calculate Q = P - C, this is the new balance of your principal of  
																			// your loan.
			
			totalPayments += curMonthlyPaymentAmount;
			totalInterestPaid += curMonthlyInterest;						

			InputOutputUtils.printf(formatString, paymentNumber++,					
					((double) curMonthlyPaymentAmount) / 100d,
					((double) curMonthlyInterest) / 100d,
					((double) curBalance) / 100d,
					((double) totalPayments) / 100d,
					((double) totalInterestPaid) / 100d);					// Output is in dollars
																			
			balance = curBalance;											// Set P equal to Q, 
																			// and go back to Step 1: 
																			// You thusly loop around until the value Q (and hence P) goes to zero.
		}
	}

}
