package library;

public enum SolutionOutcome {
	Unvalidated(0),
	Solved(1),
	CompileTimeError(2),
	RuntimeError(3),
	InvalidOutput(4),
	OutOfTime(5),
	GivenUp(6);
	
	public final int code;
	
	SolutionOutcome(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
}
