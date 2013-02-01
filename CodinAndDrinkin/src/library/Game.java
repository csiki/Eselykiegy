package library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import misc.FileClassLoader;

public class Game implements GameLogic {
	
	UserInterface ui;
	private Task currentTask;
	/**
	 * in milliseconds; System.currentTimeMillis()
	 */
	private long currentTaskStartTime;
	private List<Task> tasks = new ArrayList<Task>();
	private Player player;
	private List<Compiler> compilers = new ArrayList<Compiler>();
	private List<Solution> solutions = new ArrayList<Solution>();
	private CrateInterface crate;
	/**
	 * in dl
	 */
	private float alcToDrink = 0.0F;
	
	
	Game(UserInterface ui) {
		this.ui = ui;
	}
	
	/**
	 * Calculates the volume of alcohol, that should be drunk, according to currentTask and sout.
	 * @param sout
	 * @return alcohol volume
	 */
	private float calculateAlcComsumeVol(SolutionOutcome sout) {
		if (sout == SolutionOutcome.Solved)
			return currentTask.solvedAlcVol;
		else if (sout.code > 1 && sout.code < 6)
			return currentTask.mistakeAlcVol;

		return 0.0F;
	}
	
	/**
	 * Opens the file as serialized Task java byte file.
	 * @param file
	 * @return task: the opened Task object, null if cannot find the file, or not valid
	 */
	private Task openFile(File file) {
		
		if (!file.exists())
			return null;
			
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Task task = null;
		try {
			task = (Task) ois.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ois.close();
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return task;
	}
	
	/**
	 * Check if the pretask of the loaded task solved, and the player has enough alcohol for compared to Task.solvedAlcVol.
	 * @param task previously loaded task
	 * @return tvout: enum of the various outcomes
	 */
	private TaskValidationOutcome taskValidation(Task task) {
		
		if (!isPassed(task.priorTaskID))
			return TaskValidationOutcome.PreTaskNotSolved;
		
		if (task.solvedAlcVol > sumAlcohol())
			return TaskValidationOutcome.NotEnoughAlcohol;
		
		return TaskValidationOutcome.ValidLoad;
	}
	
	/**
	 * Sums the alcohol volume in crate.
	 * @return sum
	 */
	private float sumAlcohol() {
		float sum = 0.0F;
		
		for (int i=0; i<crate.getSize(); ++i)
			sum += crate.getBevVol(i) * crate.getBevABV(i);
		
		return sum;
	}
	
	/**
	 * Calculate the blood volume of the player according to http://easycalculation.com/medical/blood-volume.php.
	 * @param height in cm
	 * @param weight in kg
	 * @return blood volume
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
	private boolean isPassed(int taskID) {
		
		for (Solution s : solutions)
			if (s.getTask().id == taskID)
				return true;

		return false;
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
	public CrateInterface savePlayer(String name, Sex sex, int height, int weight) {
		Player player = new Player(name, sex, height, weight, calculateBloodVol(height, weight));
		
		return player.getCrate();
	}
	
	@Override
	public int addBev(String name, float vol, int abv) {
		return player.addBev(name, vol, abv);
	}
	
	@Override
	public TaskValidationOutcome loadTask(String path) {
		TaskValidationOutcome tvout = null;
		
		Task task = openFile(new File(path));
		
		if (task == null)
			return TaskValidationOutcome.NoFileFound;
		
		tvout = taskValidation(task);
		
		if (tvout == TaskValidationOutcome.ValidLoad) {
			// TODO if needed.. valid task load, currentTaskStartTime set, call UserInterface.refreshTask()
			/// save current task
			this.currentTask = task;
			
			/// add a new Solution
			this.solutions.add(new Solution(this.currentTask));
			
			/// refresh UserInterface
			ui.refreshTask(this.currentTask);
			currentTaskStartTime = System.currentTimeMillis();
			
			/// install Trigger
			// TODO !!!! initiate Trigger interface, and Stopper class + delete currentTaskStartTime if not necessary !!!!
		}
		
		return tvout;
	}
	
	@Override
	public SolutionOutcome sendSolution(int compilerID, String code) { // TODO
		SolutionOutcome retVal = null;
		return retVal;
	}
	
	@Override
	public float bevToDrink(int bevID) { // TODO
		return 0.0F;
	}
	
	@Override
	public void bevToPour(int bevID, float vol) {
		player.pour(bevID, vol);
	}
}
