package library;

public abstract class Compiler {
	protected String langName;
	
	abstract public String compile(String fileName, Boolean error);
	abstract public double run(String fileName, String[] inputs, String output, Boolean error);
	
}
