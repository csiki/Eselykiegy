package compilers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import library.Compiler;

/**
 * Compiles and runs Java code.
 * @author csiki
 *
 */
public final class JavaCompiler extends Compiler { // NOT USED !

	public JavaCompiler(String name) {
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
	
	/*@Override
	public File compile(String code, Boolean error) {
		
		// TODO headert, classnevet main fvényt ne kelljen neki írni, mer ugyanannak kell h a neve legyen a filenak... C-be és C++ba is
		
		/// create temp dir
		File codeFileDir = new File("code");
		codeFileDir.mkdir();
		File temp = null;
		try {
			temp = File.createTempFile("code", Long.toString(System.nanoTime()), codeFileDir);
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} // TODO

	    if(!(temp.delete())) {
			try {
				throw new IOException("Could not delete temp file: " + temp.getAbsolutePath());
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
	    }
			
	    if(!(temp.mkdir())) {
			try {
				throw new IOException("Could not create temp directory: " + temp.getAbsolutePath());
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
	    }
	    
		/// create code file
		File codeFile = null;
		try {
			codeFile = File.createTempFile("code", ".java", codeFileDir);
		} catch (IOException e1) {
			error = true;
			return null;
		}
		
		/// fill the file
		FileWriter fw;
		BufferedWriter bw;
		try {
			fw = new FileWriter(codeFile);
			bw = new BufferedWriter(fw);
			bw.write(code);
			bw.close();
		} catch (IOException e1) {
			error = true;
			return null;
		}
		
		/// compile
		new File("runnable").mkdir();
		String compileFileCommand = "javac -sourcepath code -classpath runnable " + codeFile.getPath();
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
        
		return temp; // TODO
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
	}*/
	
	
	@Override
	public String getName() {
		return this.langName;
	}

}
