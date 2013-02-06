package library;

import java.io.File;


/**
 * Abstract parent class of the Compiler classes.
 * @author csiki
 *
 */
public abstract class Compiler implements CompilerInterface {
	
	protected final String langName;
	
	protected Compiler(String name) {
		this.langName = name;
	}
	
	/**
	 * Compiles the given code, and if there is no error, creates a compiled (byte) file.
	 * @param code programming code
	 * @param error reference to a boolean, it is set inside the method, if there was any errors during compilation
	 * @return the path of the file created
	 */
	abstract public File compile(String code, Boolean error);
	
	/**
	 * Run the given compiled (byte) file and provide the given inputs.
	 * @param compiledFile file containing the (byte) runnable code
	 * @param inputs the desired inputs in order, to provide during running
	 * @param output reference to a String, it is set inside the method; the output of the program
	 * @param error reference to a boolean, it is set inside the method, if there was any errors during running
	 * @return runtime, if error occurs 0
	 */
	abstract public long run(File compiledFile, String[] inputs, String output, Boolean error);
	
	@Override
	public final String toString() {
		return this.langName;
	}
}
