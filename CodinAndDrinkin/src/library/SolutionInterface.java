package library;

/**
 * Solution realizes, UserInterface uses for saving ended solution data in the Solutions table.
 * @author csiki
 *
 */
public interface SolutionInterface {
	
	/**
	 * Return if the task is solved.
	 * @return Solution.sout == SolutionOutcome.Solved
	 */
	public boolean isSolved();
	
	/**
	 * Returns time spent solving the task.
	 * @return in millisecs
	 */
	public long getTimeUsed();
	
	/**
	 * Return the number of attempts.
	 * @return Solution.attempts
	 */
	public int getAttemptNum();
	
	/**
	 * Return the name of the language used.
	 * @return Solution.compiler.toString()
	 */
	public String getLang();
	
	/**
	 * Get this.sout.
	 * @return the current classification of the solution
	 */
	public SolutionOutcome getSout();
}
