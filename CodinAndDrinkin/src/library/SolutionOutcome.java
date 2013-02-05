package library;

/**
 * Enumeration for classifying solutions when evaluating one.
 * @author csiki
 *
 */
public enum SolutionOutcome {
	Unvalidated(0),
	Solved(1),
	CompileTimeError(2),
	RuntimeError(3),
	InvalidOutput(4),
	OutOfTime(5),
	OutOfAttemp(6),
	GivenUp(7);
	
	public final int code;
	
	SolutionOutcome(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
}
