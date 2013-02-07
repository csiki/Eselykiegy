package compilers;

import java.io.File;
import java.util.List;

import library.Compiler;

/**
 * Compiles and runs Java code.
 * @author csiki
 *
 */
public final class JavaCompiler extends Compiler {

	public JavaCompiler(String name) {
		super(name);
	}


	@Override
	public File compile(String code, Boolean error) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long run(File compiledFile, List<String> inputs, String output,
			Boolean error) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	@Override
	public String getName() {
		return this.langName;
	}

}
