package library;

/*
 * Realized by class Game
 */

public interface GameLogic {
	
	/**
	 * Save the player's data, after it is given at the start of the game.
	 * @param name
	 * @param sex
	 * @param height in cm
	 * @param weight in kg
	 * @return the crate that has been created after the player had.
	 */
	public CrateInterface savePlayer(String name, Sex sex, int height, int weight);
	
	/**
	 * Add a new beverage to the player (after that the beverage is added to the crate by the player).
	 * @param name
	 * @param vol in dl
	 * @param ABV like 40(%)
	 * @return the id of the beverage in the crate, for later use
	 */
	public int addBev(String name, float vol, int ABV);
	
	/**
	 * Loads a task, if it can be.
	 * @param path
	 * @return the interface can load or not the task, and if not, the problem
	 */
	public TaskValidationOutcome loadTask(String path);
	
	/**
	 * Validate the solution, calculates the consumable alcohol for the validation outcome, signal UserInterface if it's >0 about choosing beverage to drink.
	 * @param compilerID as in the list, compilers
	 * @param code the programming code
	 * @return outcome of the solution validation
	 */
	public SolutionOutcome sendSolution(int compilerID, String code);
	
	/**
	 * Stops the timer.
	 */
	public void giveUp();
	
	/**
	 * Receives the chosen beverage to drink. Calculates the volume that should be drunk of that beverage, than call player to drink it. Sets Game.alcToDrink.
	 * @param bevID as in the crate
	 * @return the volume of alcohol left, if there was not enough in the chosen beverage
	 */
	public float bevToDrink(int bevID);
	
	/**
	 * Pour extra volume, to a previously added beverage, by the help of the player.
	 * @param bevID as in the crate
	 * @param vol in dl
	 */
	public void bevToPour(int bevID, float vol);
	
}
