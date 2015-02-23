package library;

import java.io.File;
import java.util.List;


/**
 * Abstract parent class of the Compiler classes.
 * @author csiki
 *
 */
public abstract class Compiler implements CompilerInterface {
	
	/**
	 * language name
	 */
	protected final String langName;
	
	protected Compiler(String name) {
		this.langName = name;
	}
	
	/**
	 * Compiles the given code. If there is no error, creates a code text file (under folder 'code') and a compiled (runnable) file (under folder 'runnable').
	 * @param code programming code
	 * @return the path of the file created; if null, error occurred
	 */
	abstract public File compile(String code);
	
	/**
	 * Run the given compiled (byte) file and provide the given inputs.
	 * @param compiledFile file containing the (byte) runnable code
	 * @param inputs the desired inputs in order, to provide during running
	 * @return output of the given program, if null error occurred
	 */
	abstract public String run(File compiledFile, List<String> inputs);
	
	/**
	 * Checks if the environment is proper for compiling and running in certain programming language.
	 * @return true if the environment meets the requirements, false otherwise
	 */
	abstract public boolean checkEnvironment();
	
	/**
	 * Return the initial code for starting the coding
	 * @return the code
	 */
	public String getInitialCode()
	{
		return "";
	}
	
	@Override
	public final String toString() {
		return this.langName;
	}
}
