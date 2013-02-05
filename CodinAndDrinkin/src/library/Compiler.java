package library;


/**
 * Abstract parent class of the Compiler classes.
 * @author csiki
 *
 */
public abstract class Compiler {
	
	protected String langName;
	
	/**
	 * Compiles the given code, and if there was not any error, creates a compiled (byte) file.
	 * @param fileName path of the file containing the programming code
	 * @param error reference to a boolean, it is set inside the method, if there was any errors during compilation
	 * @return the path of the file created
	 */
	abstract public String compile(String fileName, Boolean error);
	
	/**
	 * Run the given compiled (byte) file and provide the given inputs. 
	 * @param fileName path of the file containing the (byte) runnable code
	 * @param inputs the desired inputs in order, to provide during running
	 * @param output reference to a String, it is set inside the method; the output of the program
	 * @param error reference to a boolean, it is set inside the method, if there was any errors during running
	 * @return double: runtime, if error occurs, 0.0
	 */
	abstract public double run(String fileName, String[] inputs, String output, Boolean error);
	
	@Override
	public final String toString() {
		return this.langName;
	}
}
