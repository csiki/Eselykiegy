package library;

import java.io.File;

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
	public SolutionOutcome validator(Compiler compiler, String code, long timeElapsed) {
		this.attempts++;
		this.duration = timeElapsed;
		this.code = code;
		this.compiler = compiler;
		this.runtime = 0;
		
		if (timeElapsed > this.task.timeAllowed) {
			this.sout = SolutionOutcome.OutOfTime;
			return SolutionOutcome.OutOfTime;
		}
		
		if (this.attempts > this.task.attemptsAllowed) {
			this.sout = SolutionOutcome.OutOfAttemp;
			return SolutionOutcome.OutOfAttemp;
		}
		
		/// compiling
		Boolean error = new Boolean(Boolean.FALSE);
		File compiledFile = compiler.compile(this.code, error);
		
		if (error) {
			this.sout = SolutionOutcome.CompileTimeError;
			return SolutionOutcome.CompileTimeError;
		}
		
		/// running
		String output = new String("");
		this.runtime = compiler.run(compiledFile, this.task.inputs, output, error);
		
		if (error) {
			this.sout = SolutionOutcome.RuntimeError;
			return SolutionOutcome.RuntimeError;
		}
		
		/// validating output
		if (!this.task.validOutput.equals(output)) {
			this.sout = SolutionOutcome.InvalidOutput;
			return SolutionOutcome.InvalidOutput;
		}
		
		return SolutionOutcome.Solved;
	}
	
	
	/**
	 * Returns with task. Uses Game.isPassed(int).
	 * @return task
	 */
	public Task getTask() {
		return this.task;
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
		if (this.compiler == null)
			return "none";
		
		return this.compiler.toString();
	}
	
	@Override
	public SolutionOutcome getSout() {
		return this.sout;
	}
}
