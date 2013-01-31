package library;

public enum TaskValidationOutcome {
	NotEnoughAlcohol(1),
	PreTaskNotSolved(2),
	NoFileFound(3),
	ValidLoad(0);
	
	public final int code;
	
	TaskValidationOutcome(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
}
