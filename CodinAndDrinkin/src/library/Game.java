package library;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import misc.Stopper;
import misc.Trigger;

/**
 * Runs the game.
 * @author csiki
 *
 */
public class Game implements GameLogic, Trigger {  // TODO send blood alc content information to MainFrame!!!
	
	UserInterface ui;
	private Task currentTask;
	private Player player;
	private List<Compiler> compilers = new ArrayList<Compiler>();
	private List<Solution> solutions = new ArrayList<Solution>();
	/**
	 * Player's beverages
	 */
	private CrateInterface crate;
	/**
	 * in dl
	 */
	private float alcToDrink = 0.0F;
	/**
	 * timer
	 */
	private Stopper stopper;
	
	
	public Game() {
	}
	
	/**
	 * Calculates the volume of alcohol, that should be drunk, according to currentTask and sout.
	 * @param sout
	 * @return alcohol volume
	 */
	private float calculateAlcComsumeVol(SolutionOutcome sout) {
		if (sout == SolutionOutcome.Solved)
			return this.currentTask.solvedAlcVol;
		else if (sout.code > 1 && sout.code < 6)
			return this.currentTask.mistakeAlcVol;

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
	 * @param sex
	 * @param height in cm
	 * @param weight in kg
	 * @return blood volume
	 */
	private float calculateBloodVol(Sex sex, int height, int weight) {
		float heightInM3 = (float) Math.pow((((float)height)/100), 3);
		if (sex == Sex.Male)
			return (float) (0.3669 * heightInM3 + 0.03219 * ((float)weight) + 0.6041);
		else
			return (float) (0.3561 * heightInM3 + 0.03308 * ((float)weight) + 0.1833);
	}
	
	/**
	 * Calculates how much of the given beverage should be consumed, considering alcVol alcohol volume.
	 * @param bevID as in crate
	 * @param alcLeft in dl, reference, set in method; >0 if volume of the beverage was not enough
	 * @return volume of the beverage should be consumed
	 */
	private float calculateBevConsumeVol(int bevID, Float alcLeft) {
		alcLeft = 0.0F;
		float bevAlcVol = crate.getBevVol(bevID) * crate.getBevABV(bevID);
		
		if (this.alcToDrink > bevAlcVol) {
			alcLeft = this.alcToDrink - bevAlcVol;
			return crate.getBevVol(bevID); // drink the whole of it
		}
		
		return this.alcToDrink / crate.getBevABV(bevID);
	}
	
	/**
	 * Check if the Task, given by the taskID, has been solved or not (returns true, whether the solution was valid or not).
	 * If the loaded task has been solved already, returns false.
	 * @param taskID
	 * @return
	 */
	private boolean isPassed(int taskID) {
		boolean pretaskSolved = false;
		
		for (Solution s : solutions) {
			if (s.getTask().id == taskID)
				pretaskSolved = true;
			if (s.getTask().id == taskID)
				return false;
		}
		
		return pretaskSolved;
	}
	
	/**
	 * Sets the user interface.
	 * @param ui user interface
	 */
	public void setUI(UserInterface ui) {
		this.ui = ui;
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
		this.player = new Player(name, sex, height, weight, calculateBloodVol(sex, height, weight));
		
		return player.getCrate();
	}
	
	@Override
	public int addBev(String name, float vol, int abv) {
		return this.player.addBev(name, vol, abv);
	}
	
	@Override
	public TaskValidationOutcome loadTask(String path) {
		TaskValidationOutcome tvout = null;
		
		Task task = openFile(new File(path));
		
		if (task == null)
			return TaskValidationOutcome.NoFileFound;
		
		tvout = taskValidation(task);
		
		if (tvout == TaskValidationOutcome.ValidLoad) {

			/// save current task
			this.currentTask = task;
			
			/// add a new Solution
			this.solutions.add(new Solution(this.currentTask));
			
			/// refresh UserInterface
			ui.startTask(this.currentTask);
			
			/// install Trigger
			this.stopper = new Stopper(this, System.currentTimeMillis(), System.currentTimeMillis() + this.currentTask.timeAllowed, 1000); // warn in every 1sec
			Thread th = new Thread(this.stopper);
			th.start();
		}
		
		return tvout;
	}
	
	@Override
	public SolutionOutcome sendSolution(int compilerID, String code) {
		
		SolutionOutcome sout = this.solutions.get(this.solutions.size() - 1).validator(this.compilers.get(compilerID), code);
		
		this.alcToDrink = this.calculateAlcComsumeVol(sout);
		
		/// terminate stopper if solved
		if (sout == SolutionOutcome.Solved)
			stopper.surrender();
		
		/// choose beverage
		if (this.alcToDrink > 0)
			ui.chooseBev(this.alcToDrink);
		
		return sout;
	}
	
	@Override
	public void giveUp() {
		stopper.surrender();
	}
	
	@Override
	public float bevToDrink(int bevID) {
		Float alcLeft = 0.0F;
		
		float bevToDrink = calculateBevConsumeVol(bevID, alcLeft);
		
		this.player.consume(bevID, bevToDrink, this.alcToDrink - alcLeft);
		this.alcToDrink = alcLeft;
		
		if (this.alcToDrink > 0)
			ui.chooseBev(this.alcToDrink);
		
		return this.alcToDrink;
	}
	
	@Override
	public void bevToPour(int bevID, float vol) {
		player.pour(bevID, vol);
	}
	
	
	/*
	 * Implemented method from interface Trigger:
	 */
	
	@Override
	public void warn(long elapsedTime) {
		int elapsedSeconds = (int) (elapsedTime / 1000);
		int secondsDisplay = elapsedSeconds % 60;
		int minutesDisplay = elapsedSeconds / 60;
		
		ui.refreshTime(minutesDisplay, secondsDisplay);
	}
	
	@Override
	public void shoot() {
		ui.endTask();
	}

}
