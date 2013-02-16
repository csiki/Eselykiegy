package library;

/**
 * Interface for the main window. Game uses to communicate.
 * @author csiki
 *
 */
public interface UserInterface {
	
	/**
	 * Let the player select to drink from multiple beverages.
	 * @param alcVol in dl
	 */
	public void chooseBev(float alcVol);
	
	/**
	 * Updates the current task section on user interface.
	 * @param task
	 */
	public void startTask(Task task);
	
	/**
	 * Signals the user that time expired or mistakes reached its allowed maximum and makes impossible to evaluate more solutions.
	 */
	public void endTask(SolutionInterface solution);
	
	/**
	 * Refresh the elapsed time on screen.
	 * @param min
	 * @param sec
	 */
	public void refreshTime(int min, int sec);
	
	/**
	 * Refresh the number of attempts on screen.
	 * @param num
	 */
	public void refreshAttemptNum(int num);
	
	/**
	 * Adds a new element to MainFrame.langChoose.
	 * @param cname name of the compiler language
	 */
	public void addCompilerToComboBox(String cname);
	
	/**
	 * Before any message could be sent after evaluating the solution, it is called by Game to inform the user about the outcome.
	 * @param sout outcome
	 */
	public void informUserAboutSolutionOutcome(SolutionOutcome sout);
}
