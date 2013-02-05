package library;

/**
 * Storing one solution's data and a reference to a Task. Able to validate itself.
 * @author csiki
 *
 */
public class Solution implements SolutionInterface {
	
	private SolutionOutcome sout = SolutionOutcome.Unvalidated;
	/**
	 * time taken solving the task, in millisecs
	 */
	private long duration = 0;
	/**
	 * time spent running the (solution) program
	 */
	private long runtime = 0;
	/**
	 * number of evaluating solutions
	 */
	private int attempts = 0;
	private String code;
	private Task task;
	private Compiler compiler;
	
	Solution(Task task) {
		this.task = task;
	}
	
	/**
	 * Validates itself according to code, using compiler. Updates attempts, runtime, duration, code, compiler and sout.
	 * If the solution has any error, and the number of attempts reached the max allowed, it returns SolutionOutcome.OutOfAttemp whatever.
	 * @return outcome of the validation
	 */
	public SolutionOutcome validator(Compiler compiler, String code, long timeElapsed) { // TODO
		SolutionOutcome retVal = null;
		return retVal;
	}
	
	/**
	 * Update code.
	 * @param code programming code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * Update compiler.
	 * @param cmp
	 */
	public void setCompiler(Compiler compiler) {
		this.compiler = compiler;
	}
	
	/**
	 * Returns with task. Uses Game.isPassed(int).
	 * @return
	 */
	public Task getTask() {
		return this.task;
	}
	
	/**
	 * Get this.sout.
	 * @return the current classification of the solution
	 */
	public SolutionOutcome getSout() {
		return this.sout;
	}
	
	/**
	 * Called by Game when allowedTime elapses.
	 */
	public void outOfTime() {
		this.sout = SolutionOutcome.OutOfTime;
		this.duration = this.task.timeAllowed;
		this.runtime = 0;
	}
	
	/**
	 * Called by Game if player gives up.
	 * @param timeElapsed in millisecs
	 */
	public void giveUp(long timeElapsed) {
		this.sout = SolutionOutcome.GivenUp;
		this.duration = timeElapsed;
		this.runtime = 0;
	}
	
	
	/*
	 * Implemented methods from interface GameLogic:
	 */

	@Override
	public boolean isSolved() {
		return this.sout == SolutionOutcome.Solved;
	}

	@Override
	public long getTimeUsed() {
		return this.duration;
	}

	@Override
	public int getAttemptNum() {
		return this.attempts;
	}

	@Override
	public String getLang() {
		return this.compiler.toString();
	}
}
