package library;

public class Solution {
	
	private SolutionOutcome sout;
	private float duration = 0.0F;
	private double runtime = 0.0;
	private int mistakes = 0;
	private String code;
	private Task task;
	private Compiler compiler;
	
	/**
	 * Validates itself according to code, using compiler. Updates mistakes, runtime, duration and sout (returns with the last).
	 * @return SolutionOutcome: outcome of the validation
	 */
	public SolutionOutcome validator() { // TODO
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
}
