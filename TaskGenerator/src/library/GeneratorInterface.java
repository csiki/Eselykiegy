package library;

import java.util.Map;

public interface GeneratorInterface {
	
	/**
	 * Initialise task vars default, should be called before setting vars.
	 */
	public void init();
	
	/**
	 * Set vars to default.
	 */
	public void clear();
	
	/**
	 * Initialise a Task object, then serialize and save it to the given location.
	 * No filename needed, it is saved in [id].task form.
	 * @param path, ending with a slash, default: tasks/
	 */
	public void serialize(String path);
	
	/**
	 * Listing task vars and its values.
	 * @return text that includes the values of task vars
	 */
	public Map<String,String> status();
	
	
	/**
	 * Sets task id.
	 * @param id
	 */
	public void saveID(int id);
	
	/**
	 * Sets task priorTaskID
	 * @param priorID, if 0 no task required beforehand
	 */
	public void savePriorTaskID(int priorID);
	
	/**
	 * Sets task description.
	 * @param description
	 */
	public void saveDescription(String description);
	
	/**
	 * Adds a new one to task inputs.
	 * @param input
	 */
	public void addInput(String input);
	
	/**
	 * Sets task validOutput.
	 * @param output
	 */
	public void saveValidOutput(String output);
	
	/**
	 * Sets task mistakeAlcVol.
	 * @param alcVol in dl
	 */
	public void saveMistakeAlcVol(float alcVol);
	
	/**
	 * Sets task solvedAlcVol.
	 * @param alcVol in dl
	 */
	public void saveSolvedAlcVol(float alcVol);
	
	/**
	 * Sets task attemptsAllowed.
	 * @param attempts
	 */
	public void saveAttemptsAllowed(int attempts);
	
	/**
	 * Sets timeAllowed.
	 * @param time in millisecs
	 */
	public void saveTimeAllowed(long time);
}
