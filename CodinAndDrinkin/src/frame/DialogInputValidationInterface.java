package frame;

/**
 * Dialogs realizes for validating their inputs.
 * @author csiki
 *
 */
public interface DialogInputValidationInterface {
	
	/**
	 * Every time a change occurs in input data, checkIfOK() should be called.
	 */
	public void checkIfOK();
	
	/**
	 * checkIfOK() uses, for validating inputs.
	 * @return
	 */
	public boolean inputValidation();
}
