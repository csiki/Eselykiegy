package library;

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
	public void refreshTask(Task task);
}
