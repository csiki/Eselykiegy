package library;

import java.io.File;
import java.util.List;

/**
 * Interface for functionalities UserInterface uses, Game realizes.
 * @author csiki
 *
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
	 */
	public void addBev(String name, float vol, int ABV);
	
	/**
	 * Loads a task, if it can be.
	 * @param taskFile
	 * @return the interface can load or not the task, and if not, the problem
	 */
	public TaskValidationOutcome loadTask(File taskFile);
	
	/**
	 * Validate the solution, calculates the consumable alcohol for the validation outcome, signal UserInterface if it's >0 about choosing beverage to drink.
	 * @param compilerID as in the list, compilers
	 * @param code the programming code
	 * @return outcome of the solution validation
	 */
	public SolutionOutcome evaluateSolution(int compilerID, String code);
	
	/**
	 * Stops the timer.
	 */
	public void giveUp();
	
	/**
	 * Receives the chosen beverage to drink. Calculates the volume that should be drunk of that beverage, than call player to drink it. Sets Game.alcToDrink.
	 * @param bevID as in the crate
	 * @return in dl, the volume of beverage to drink
	 */
	public float bevToDrink(int bevID);
	
	/**
	 * Pour extra volume, to a previously added beverage, by the help of the player.
	 * @param bevID as in the crate
	 * @param vol in dl
	 */
	public void bevToPour(int bevID, float vol);
	
	/**
	 * Determines if any task is loaded according to Game.currentTask.
	 * @return whether a task is loaded or not
	 */
	public boolean isAnyTaskLoaded();
	
	/**
	 * Sums the solved solutions left unused time.
	 * @return in millisecs
	 */
	public long sumTimeLeft();
	
	/**
	 * Sum of number of solved tasks divided by sum of mistakes.
	 * @return in %
	 */
	public int rateOfRightSolutions();
	
	/**
	 * Returns with the volume of consumed alcohol so far.
	 * @return in dl
	 */
	public float consumedAlcohol();
	
	/**
	 * Returns with blood alcohol content. Uses Widmark's Basic Formula.
	 * @return in thousandth
	 */
	public float bloodAlcContent();
	
	/**
	 * Returns a lists of loaded compilers, to fill combobox.
	 * @return compiler interfaces in list
	 */
	public List<CompilerInterface> getCompilers();
	
	/**
	 * Returns volume of alcohol that is left to drink.
	 * @return in dl
	 */
	public float getAlcToDrink();
	
	/**
	 * Returns volume of alcohol the player have drunk.
	 * @return in dl
	 */
	public float getConsumedAlc();
}
