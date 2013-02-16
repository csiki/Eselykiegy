package compilers;

import java.io.File;
import java.util.List;

import library.Compiler;

/**
 * Compiles and runs C++ code.
 * @author csiki
 *
 */
public final class CppCompiler extends Compiler {

	public CppCompiler(String name) {
		super(name);
	}

	
	@Override
	public File compile(String code) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String run(File compiledFile, List<String> inputs) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public String getName() {
		return this.langName;
	}

}
