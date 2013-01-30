package library;

import java.io.File;
import java.util.List;

public class Game implements GameLogic {
	private Task currentTask;
	private long currentTaskStartTime;
	private List<Task> tasks;
	private Player player;
	private List<Compiler> compilers;
	private List<Solution> solutions;
	private CrateInterface crate;

	private float calculateAlcComsumeVol(SolutionOutcome sout) { // TODO
		float retVal = 0.0F;
		return retVal;
	}
	
	private Task openFile(File file) { // TODO
		Task retVal = null;
		return retVal;
	}
	
	private TaskValidationOutcome taskValidation(Task task) { // TODO
		TaskValidationOutcome retVal = null;
		return retVal;
	}
	
	private float calculateBloodVol(int height, int weight) { // TODO
		float retVal = 0.0F;
		return retVal;
	}
	
	private boolean isPassed(int taskID) { // TODO
		boolean retVal = false;
		return retVal;
	}
	
	
	/*
	 * Implemented methods from interface GameLogic:
	 */
	@Override
	public CrateInterface savePlayer(String name, Sex sex, int height, int weight) { // TODO
		CrateInterface retVal = null;
		return retVal;
	}
	
	@Override
	public int addBev(String name, float vol, int ABV) { // TODO
		int bevID = 0;
		return bevID;
	}
	
	@Override
	public TaskValidationOutcome loadTask(String path) { // TODO
		TaskValidationOutcome retVal = null;
		return retVal;
	}
	
	@Override
	public SolutionOutcome sendSolution(int compilerID, String code) { // TODO
		SolutionOutcome retVal = null;
		return retVal;
	}
	
	@Override
	public float bevToDrink(int bevID) { // TODO
		float retVal = 0.0F;
		return retVal;
	}
	
	@Override
	public void bevToPour(int bevID, float vol) { // TODO
	}
}
