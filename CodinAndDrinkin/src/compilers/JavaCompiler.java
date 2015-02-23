package compilers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import library.Compiler;
import library.ProcessChecker;

public final class JavaCompiler extends Compiler {

	public JavaCompiler(String name) {
		super(name);
	}

	@Override
	public File compile(String code) {

		
		/// make temp code file
		File codeFile = null;
		try {
			codeFile = new File("code/Main.java");
			codeFile.createNewFile();
		} catch (IOException e) {
			return null;
		}
		
		/// fill temp code file
		FileWriter fw;
		BufferedWriter bw;
		try {
			fw = new FileWriter(codeFile);
			bw = new BufferedWriter(fw);
			bw.write(code);
			bw.close();
		} catch (IOException e1) {
			return null;
		}
		
		/// compile temp code file
		String command = "javac -d runnable " + codeFile.getPath();
		try {
			Process pr = Runtime.getRuntime().exec(command);
			
			/// wait for execution
			Thread th = new Thread(new ProcessChecker(pr));
			th.start();
			int period = 0;
			int maxWaitTime = 60; // in tenth of a second
			while (period < maxWaitTime) {
				if (!th.isAlive())
					break;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				++period;
			}
			
			if (th.isAlive()) { // if still alive, the process still runs (for too long), so destroy process (so the thread ends too)
				pr.destroy();
				return null;
			}
			
			if (pr.exitValue() != 0)// = failure
				return null;
			
		} catch (IOException e) {
			return null;
		}
		
		return new File("runnable" + File.separatorChar + "Main.class");
	}

	@Override
	public String run(File compiledFile, List<String> inputs) {

		/// create command
		// add folder of .class with -cp
		String compiledName = compiledFile.getName();
		String command = "java -cp " + compiledFile.getParent() + " " + compiledName.substring(0, compiledName.length() - 6);
		
		/// add inputs as command args
		for (String arg : inputs)
			command += " " + arg;
		
		/// run binary
		String output = "";
		try {
			Process pr = Runtime.getRuntime().exec(command);
			
			/// wait for execution
			Thread th = new Thread(new ProcessChecker(pr));
			th.start();
			int period = 0;
			int maxWaitTime = 60; // in tenth of a second
			while (period < maxWaitTime) {
				if (!th.isAlive())
					break;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				++period;
			}
			
			if (th.isAlive()) { // if still alive, the process still runs (for too long), so destroy process (so the thread ends too)
				pr.destroy();
				return null;
			}
			
			/// get output
			InputStreamReader isr = new InputStreamReader(pr.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null)
				output += line + '\n';
			
			/// cut off last \n
			if (output.length() > 0)
				output = output.substring(0, output.length() - 1);
			
		} catch (IOException e) {
			return null;
		}
		
		return output;
	}

	@Override
	public String getInitialCode() {
		return "public class Main\n{\n\t\n}";
	}

	@Override
	public String getName() {
		return this.langName;
	}

	@Override
	public boolean checkEnvironment() {
		String command = "javac -version";

		try {
			Process pr = Runtime.getRuntime().exec(command);

			// / get output to end process
			InputStreamReader isr = new InputStreamReader(pr.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			while ((br.readLine()) != null)
				;

			if (pr.exitValue() == 0)
				return true;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

}
