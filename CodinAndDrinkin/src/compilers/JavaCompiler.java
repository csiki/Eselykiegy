package compilers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
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
		/// create file
		File compiledFileDir = new File("runnable");
		compiledFileDir.mkdir();
		File compiledFile = null;
		try {
			compiledFile = File.createTempFile("runnable", ".class", compiledFileDir);
		} catch (IOException e1) {
			//e1.printStackTrace();
			error = true;
			return null;
		}
		
		/// compile
		String compileFileCommand = "javac " + compiledFile.getPath();
		try {
			Process compileProcess = Runtime.getRuntime().exec(compileFileCommand);

			String line = "";
			BufferedReader bri = new BufferedReader(
					new InputStreamReader(compileProcess.getInputStream()));
			BufferedReader bre = new BufferedReader(
					new InputStreamReader(compileProcess.getErrorStream()));
			while ((line = bri.readLine()) != null) {
				System.out.println(line); // TODO nem kell késõbb
			}
			
            bri.close();
            
            while ((line = bre.readLine()) != null) {
            	System.out.println(line);
            	error = true;
            	return null;
            }
            bre.close();
            compileProcess.waitFor();
		} catch (Exception e) {
            error = true;
            return null;
        }
        
		return compiledFile;
	}

	@Override
	public long run(File compiledFile, List<String> inputs, String output, Boolean error) {
		
		String runFileCommand = "java " + compiledFile.getPath().split(".java")[0];
		output = "";
        try {
        	Process runProcess = Runtime.getRuntime().exec(runFileCommand);

        	BufferedReader reader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
        	String line = reader.readLine();
        	System.out.println("line = " + line); // TODO töröld és kell ami adja bemenetet és szedi kimenetet
        	while (line != null) {
        		System.out.println(line);
        		line = reader.readLine();
            }
        } catch (Exception e) {
            error = true;
            return 0;
        }
		
		return 0;
	}
	
	
	@Override
	public String getName() {
		return this.langName;
	}

}
