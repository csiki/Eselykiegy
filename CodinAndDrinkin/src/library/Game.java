package library;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Game implements GameLogic {
	private Task currentTask;
	private long currentTaskStartTime;
	private List<Task> tasks = new ArrayList<Task>();
	private Player player;
	private List<Compiler> compilers = new ArrayList<Compiler>();
	private List<Solution> solutions = new ArrayList<Solution>();
	private CrateInterface crate;
	
	
	/**
	 * Calculates the volume of alcohol, that should be drunk, according to currentTask and sout.
	 * @param sout
	 * @return alcohol volume
	 */
	private float calculateAlcComsumeVol(SolutionOutcome sout) { // TODO
		float alcVol = 0.0F;
		return alcVol;
	}
	
	/**
	 * Opens the file as serialized Task java byte file.
	 * @param file
	 * @return task: the opened Task object, null if cannot find the file, or not valid
	 */
	private Task openFile(File file) { // TODO
		Task task = null;
		return task;
	}
	
	/**
	 * Check if the pretask of the loaded task solved, and the player has enough alcohol for compared to Task.solvedAlcVol.
	 * @param task previously loaded task
	 * @return tvout: enum of the various outcomes
	 */
	private TaskValidationOutcome taskValidation(Task task) { // TODO
		TaskValidationOutcome tvout = null;
		return tvout;
	}
	
	/**
	 * Calculate the blood volume of the player according to http://easycalculation.com/medical/blood-volume.php.
	 * @param height in cm
	 * @param weight in kg
	 * @return bvol: blood volume
	 */
	private float calculateBloodVol(int height, int weight) {
		float heightInM3 = (float) Math.pow((((float)height)/100), 3);
		if (player.getSex() == Sex.Male)
			return (float) (0.3669 * heightInM3 + 0.03219 * ((float)player.getWeight()) + 0.6041);
		else
			return (float) (0.3561 * heightInM3 + 0.03308 * ((float)player.getWeight()) + 0.1833);
	}
	
	/**
	 * Returns if the Task, given by the taskID, has been solved or not (returns true, whether the solution was valid or not).
	 * @param taskID
	 * @return
	 */
	private boolean isPassed(int taskID) { // TODO
		boolean passed = false;
		return passed;
	}
	
	/**
	 * Adds a new compiler.
	 * @param compiler
	 */
	public void addCompiler(Compiler compiler) {
		compilers.add(compiler);
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
	public int addBev(String name, float vol, int abv) {
		return player.addBev(name, vol, abv);
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
	public void bevToDrink(int bevID) { // TODO
	}
	
	@Override
	public void bevToPour(int bevID, float vol) { // TODO
	}
}
