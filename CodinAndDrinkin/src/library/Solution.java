package library;

public class Solution {
	
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
	 * number of evaluating invalid solutions
	 */
	private int mistakes = 0;
	private String code;
	private Task task;
	private Compiler compiler;
	
	Solution(Task task) {
		this.task = task;
	}
	
	/**
	 * Validates itself according to code, using compiler. Updates mistakes, runtime, duration and sout (returns with the last).
	 * @return SolutionOutcome: outcome of the validation
	 */
	public SolutionOutcome validator(Compiler compiler, String code) { // TODO
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
}
