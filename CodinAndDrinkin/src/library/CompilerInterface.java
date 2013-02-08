package library;

/**
 * Compiler realizes, UserInterface uses.
 * Can further be used for providing syntax highlighting for UserInterface.
 * @author csiki
 *
 */
public interface CompilerInterface {
	
	/**
	 * Returns with the name of the Compiler.
	 * @return programming language name
	 */
	public String getName();
	
}
