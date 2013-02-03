package library;

/**
 * Interface for the main window. Game uses to communicate.
 * @author csiki
 *
 */
public interface UserInterface {
	
	/**
	 * Let the player select to drink from only those beverages, that have the minAlcVol alcohol volume.
	 * @param minAlcVol in dl
	 */
	public void chooseBev(float minAlcVol);
	
	/**
	 * Updates the current task section on user interface.
	 * @param task
	 */
	public void startTask(Task task);
	
	/**
	 * Signals the user that time expired and makes impossible to evaluate more solutions.
	 */
	public void endTask();
	
	/**
	 * Refresh the elapsed time on screen.
	 * @param min
	 * @param sec
	 */
	public void refreshTime(int min, int sec);
}
