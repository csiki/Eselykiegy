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
	 * @param path ending with a slash, default: tasks/
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
	public void setID(int id);
	
	/**
	 * Sets task priorTaskID
	 * @param priorID if 0 no task required beforehand
	 */
	public void setPriorTaskID(int priorID);
	
	/**
	 * Sets task title.
	 * @param title
	 */
	public void setTitle(String title);
	
	/**
	 * Sets task description.
	 * @param description
	 */
	public void setDescription(String description);
	
	/**
	 * Adds a new one to task inputs.
	 * @param input
	 */
	public void addInput(String input);
	
	/**
	 * Sets task validOutput.
	 * @param output
	 */
	public void setValidOutput(String output);
	
	/**
	 * Sets task mistakeAlcVol.
	 * @param alcVol in dl
	 */
	public void setMistakeAlcVol(float alcVol);
	
	/**
	 * Sets task solvedAlcVol.
	 * @param alcVol in dl
	 */
	public void setSolvedAlcVol(float alcVol);
	
	/**
	 * Sets task attemptsAllowed.
	 * @param attempts
	 */
	public void setAttemptsAllowed(int attempts);
	
	/**
	 * Sets timeAllowed.
	 * @param time in millisecs
	 */
	public void setTimeAllowed(long time);
	
	/**
	 * Loads an existing .task file.
	 * @param filePath
	 */
	public String load(String filePath);
	
	/**
	 * Sets id to default.
	 */
	public void clearID();
	
	/**
	 * Sets attemptsAllowed to default.
	 */
	public void clearAttemptsAllowed();
	
	/**
	 * Sets mistakeAlcVol to default.
	 */
	public void clearMistakeAlcVol();
	
	/**
	 * Sets description to default.
	 */
	public void clearDescription();
	
	/**
	 * Sets timeAllowed to default.
	 */
	public void clearTimeAllowed();
	
	/**
	 * Sets solvedAlcVol to default.
	 */
	public void clearSolvedAlcVol();
	
	/**
	 * Sets validOutput to default.
	 */
	public void clearValidOutput();
	
	/**
	 * Sets priorTaskID to default.
	 */
	public void clearPriorTaskID();
	
	/**
	 * Sets title to default.
	 */
	public void clearTitle();
	
	/**
	 * Empty inputs.
	 */
	public void clearInputs();
}
