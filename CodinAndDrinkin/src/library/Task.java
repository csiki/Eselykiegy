package library;

import java.io.Serializable;
import java.util.List;

public class Task implements Serializable {
	
	public final int priorTaskID; 
	public final String description;
	public final List<String> inputs;
	public final String validOutput;
	public final float mistakeAlcVol;
	public final float solvedAlcVol;
	
	Task(
			int priorTaskID,
			String description,
			List<String> inputs,
			String validOutput,
			float mistakeAlcVol,
			float solvedAlcVol
	) {
		this.priorTaskID = priorTaskID;
		this.description = description;
		this.inputs = inputs;
		this.validOutput = validOutput;
		this.mistakeAlcVol = mistakeAlcVol;
		this.solvedAlcVol = solvedAlcVol;
	}
}
