package library;

import java.io.Serializable;
import java.util.List;

public class Task implements Serializable {
	
	public final int id;
	/**
	 * id of task that should be solved beforehand
	 */
	public final int priorTaskID;
	/**
	 * directive for the task
	 */
	public final String description;
	/**
	 * in order, to input during the run of the (solution) program
	 */
	public final List<String> inputs;
	/**
	 * expected output of the (solution) program
	 */
	public final String validOutput;
	/**
	 * volume of alcohol prescribed for making a mistake
	 */
	public final float mistakeAlcVol;
	/**
	 * volume of alcohol prescribed for solving the task
	 */
	public final float solvedAlcVol;
	/**
	 * max number of mistakes allowed
	 */
	public final int mistakesAllowed;
	/**
	 * time allowed for solving the task, in millisecs
	 */
	public final long timeAllowed;
	
	Task(
			int id,
			int priorTaskID,
			String description,
			List<String> inputs,
			String validOutput,
			float mistakeAlcVol,
			float solvedAlcVol,
			int mistakesAllowed,
			long timeAllowed
	) {
		this.id = id;
		this.priorTaskID = priorTaskID;
		this.description = description;
		this.inputs = inputs;
		this.validOutput = validOutput;
		this.mistakeAlcVol = mistakeAlcVol;
		this.solvedAlcVol = solvedAlcVol;
		this.mistakesAllowed = mistakesAllowed;
		this.timeAllowed = timeAllowed;
	}
}
