package compilers;

import java.io.File;

import library.Compiler;


/**
 * Compiles and runs C code.
 * @author csiki
 *
 */
public final class CCompiler extends Compiler {

	public CCompiler(String name) {
		super(name);
	}
	

	@Override
	public File compile(String code, Boolean error) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long run(File compiledFile, String[] inputs, String output, Boolean error) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	@Override
	public String getName() {
		return this.langName;
	}
	
}
