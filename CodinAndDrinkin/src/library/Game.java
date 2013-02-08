package library;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import compilers.CCompiler;
import compilers.CppCompiler;
import compilers.JavaCompiler;

import misc.Stopper;
import misc.Trigger;

/**
 * Runs the whole game.
 * @author csiki
 *
 */
public class Game implements GameLogic, Trigger {
	
	UserInterface ui;
	private Task currentTask = null;
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
	
	/**
	 * used to notice if outOfTime occurs during action
	 */
	private volatile boolean underAction = false;
	
	/**
	 * if outOfTime occurs when underAction is true, set true
	 */
	private volatile boolean outOfTimeDuringAction = false;
	
	/**
	 * used by GameLogic.bloodAlcContent() to determine where the drinking started approximately
	 * set in Game constructor
	 * in millisecs
	 */
	private long timeGameStarted;
	
	
	public Game() {
		this.timeGameStarted = System.currentTimeMillis();
		
		/// add new compilers here !
		this.compilers.add(new CCompiler("C"));
		this.compilers.add(new CppCompiler("C++"));
		this.compilers.add(new JavaCompiler("Java"));
	}
	
	/**
	 * Calculates the volume of alcohol, that should be drunk, according to currentTask and sout.
	 * @param sout
	 * @return alcohol volume, in dl
	 */
	private float calculateAlcComsumeVol(SolutionOutcome sout) {
		if (sout == SolutionOutcome.Solved)
			return this.currentTask.solvedAlcVol / 10; // from cl to dl
		else if (sout.code > 1 && sout.code < 7)
			return this.currentTask.mistakeAlcVol / 10; // from cl to dl
		
		return 0.0F;
	}
	
	/**
	 * Opens the file as serialized Task java byte file.
	 * @param file
	 * @return task: the opened Task object, null if cannot find the file, or not valid
	 */
	private Task openFile(File file) {
		
		if (!file.exists() || !file.canRead())
			return null;
		
		/// substring the extension
		String extension = "";
		int i = file.getPath().lastIndexOf('.');
		if (i > 0)
		    extension = file.getPath().substring(i+1);
		if (!extension.equals("task"))
			return null;
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Task task = null;
		try {
			task = (Task) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			ois.close();
			fis.close();
		} catch (IOException e) {
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
		
		if (this.isSolved(task.id))
			return TaskValidationOutcome.HasAlreadySolved;
		
		if (!this.isSolved(task.priorTaskID))
			return TaskValidationOutcome.PreTaskNotSolved;
		
		if ((task.solvedAlcVol / 10.0F) > sumAlcohol()) // cl to dl
			return TaskValidationOutcome.NotEnoughAlcohol;
		
		return TaskValidationOutcome.ValidLoad;
	}
	
	/**
	 * Sums the alcohol volume in crate.
	 * @return sum
	 */
	private float sumAlcohol() {
		float sum = 0.0F;
		
		for (int i=0; i<this.crate.getSize(); ++i)
			sum += this.crate.getBevVol(i) * this.crate.getBevABV(i);
		
		return sum;
	}
	
	/**
	 * Calculates how much of the given beverage should be consumed, considering alcVol alcohol volume.
	 * Sets this.accToDrink!
	 * @param bevID as in crate
	 * @return volume of the beverage should be consumed
	 */
	private float calculateBevConsumeVol(int bevID) {
		float bevAlcVol = crate.getBevVol(bevID) * crate.getBevABV(bevID);
		
		if (this.alcToDrink > bevAlcVol) {
			this.alcToDrink = this.alcToDrink - bevAlcVol;
			return crate.getBevVol(bevID); // drink the whole of it
		}
		
		float tempAlcToDrink = this.alcToDrink;
		this.alcToDrink = 0.0F;
		
		return tempAlcToDrink / crate.getBevABV(bevID);
	}
	
	/**
	 * Check if the Task, given by the taskID, has been solved or not (returns true, whether the solution was valid or not).
	 * If taskID == 0, no pretask required.
	 * @param taskID
	 * @return
	 */
	private boolean isSolved(int taskID) {
		boolean pretaskSolved = false;
		
		if (taskID == 0) // no pretask required
			return true;
		
		for (Solution s : solutions)
			if (s.getTask().id == taskID)
				pretaskSolved = true;
		
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
	 * Implemented methods from interface GameLogic
	 */
	
	@Override
	public CrateInterface savePlayer(String name, Sex sex, int height, int weight) {
		this.player = new Player(name, sex, height, weight);
		this.crate = player.getCrate();
		
		return this.crate;
	}
	
	@Override
	public void addBev(String name, float vol, int abv) {
		float rateABV = ((float) (abv)) / 100; // e.g. 20% to 0.2
		this.player.addBev(name, vol, rateABV);
	}
	
	@Override
	public TaskValidationOutcome loadTask(File taskFile) {
		TaskValidationOutcome tvout = null;
		
		Task task = openFile(taskFile);
		
		if (task == null)
			return TaskValidationOutcome.NoFileFound;
		
		tvout = taskValidation(task); // at the end returns with it if not valid
		
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
	public SolutionOutcome evaluateSolution(int compilerID, String code) {
		
		this.underAction = true;
		Solution solution = this.solutions.get(this.solutions.size() - 1);
		
		SolutionOutcome sout = solution.validator(this.compilers.get(compilerID), code, this.stopper.showTimeElapsed());
		this.underAction = false;
		
		/// refresh number of attempts on GUI
		this.ui.refreshAttemptNum(solution.getAttemptNum());
		
		/// calculate alcohol vol in dl
		this.alcToDrink = this.calculateAlcComsumeVol(sout);
		
		/// choose beverage
		if (this.alcToDrink > 0) {
			ui.chooseBev(this.alcToDrink);
			this.underAction = true;
		}
		/// if task ended
		if (sout == SolutionOutcome.Solved || sout == SolutionOutcome.OutOfAttemp || this.outOfTimeDuringAction) {
			this.outOfTimeDuringAction = false;
			this.stopper.surrender();
			this.currentTask = null; // sign, that there's no task to solve at the moment
			this.ui.endTask(solution);
		}
		
		return sout;
	}
	
	@Override
	public void giveUp() {
		Solution solution = this.solutions.get(this.solutions.size() - 1);
		
		solution.giveUp(this.stopper.surrender());
		this.currentTask = null; // sign, that there's no task to solve at the moment
		this.ui.endTask(solution);
	}
	
	@Override
	public float bevToDrink(int bevID) {
		float alcToDrinkOrig = this.alcToDrink;
		
		float bevToDrink = calculateBevConsumeVol(bevID);
		
		this.player.consume(bevID, bevToDrink, alcToDrinkOrig - this.alcToDrink);
		// this.alcToDrink already set in calculateBevConsumeVol()
		
		if (this.alcToDrink > 0) {
			ui.chooseBev(this.alcToDrink);
			this.underAction = true;
		}
		
		/// end task if outOfTime during drinking
		this.underAction = false;
		if (this.outOfTimeDuringAction) {
			Solution solution = this.solutions.get(solutions.size() - 1);
			solution.outOfTime();
			this.outOfTimeDuringAction = false;
			this.stopper.surrender();
			this.currentTask = null; // sign, that there's no task to solve at the moment
			this.ui.endTask(solution);
		}
		
		return bevToDrink;
	}
	
	@Override
	public void bevToPour(int bevID, float vol) {
		player.pour(bevID, vol);
	}
	
	@Override
	public boolean isAnyTaskLoaded() {
		if (this.currentTask == null)
			return false;
		return true;
	}
	
	@Override
	public long sumTimeLeft() {
		long sum = 0;
		
		for (Solution s : this.solutions)
			if (s.getSout() == SolutionOutcome.Solved)
				sum += s.getTask().timeAllowed - s.getTimeUsed();
		
		return sum;
	}

	@Override
	public int rateOfRightSolutions() {
		int attemptsNum = 0;
		int rightNum = 0;
		
		for (Solution s : solutions) {
			attemptsNum += s.getAttemptNum();
			if (s.getSout() == SolutionOutcome.Solved)
				++rightNum;
		}
		
		if (attemptsNum == 0)
			return 0;
		
		return (int) ((((float) (rightNum)) / ((float) (attemptsNum))) * 100);
	}

	@Override
	public float consumedAlcohol() {
		return player.getConsumedAlc();
	}
	
	@Override
	public float bloodAlcContent() { // TODO ez nem ezrelék !!!
		float alcConsumedInOz = this.player.getConsumedAlc() * 3.38140227F;
		float weightInPounds = this.player.getWeight() * 2.20462262F;
		float timeInHour = (float) ( ((double) (System.currentTimeMillis() - this.timeGameStarted)) / 3600000 );
		
		// TODO 9,7dl tiszta alkoholra 1ezreléket ír, nem jó; nézd át ezt -> http://www.elsosegely.hu/cikk.58.hogyan_szamoljuk_ki_a_veralkoholszintet
		
		float alcDistributionRatio;
		if (player.getSex() == Sex.Male)
			alcDistributionRatio = 0.73F;
		else
			alcDistributionRatio = 0.66F;
		
		return (float) ((alcConsumedInOz * (0.823 * 100 / 16) / weightInPounds * alcDistributionRatio) - 0.015F * timeInHour);
	}
	
	@Override
	public List<CompilerInterface> getCompilers() {
		List<CompilerInterface> clist = new ArrayList<CompilerInterface>();
		
		for (Compiler c : this.compilers)
			clist.add(c);
		
		return clist;
	}
	
	@Override
	public float getAlcToDrink() {
		return this.alcToDrink;
	}
	
	@Override
	public float getConsumedAlc() {
		return player.getConsumedAlc();
	}
	
	/*
	 * Implemented method from interface Trigger
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
		
		if (this.underAction) { // OutOfTime occurs during evaluation; evaluateSolution() ends task later
			this.outOfTimeDuringAction = true;
			return;
		}
		
		Solution solution = this.solutions.get(this.solutions.size() - 1);
		
		solution.outOfTime();
		this.currentTask = null;
		ui.endTask(solution);
	}
}
