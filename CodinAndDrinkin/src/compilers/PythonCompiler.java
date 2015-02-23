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

public class PythonCompiler extends Compiler
{
	private String cscPath = null;

	public PythonCompiler(String name)
	{
		super(name);
	}

	@Override
	public String getName()
	{
		return this.langName;
	}

	@Override
	public File compile(String code)
	{
		/// make temp code file
		File codeFile = null;
		try {
			codeFile = File.createTempFile("code", ".py", new File("code"));
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
		
		//python is an interpreted lang
		return codeFile;
	}

	@Override
	public String run(File compiledFile, List<String> inputs)
	{
		/// create command
		String command = "python " + compiledFile.getPath();
		
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
	public boolean checkEnvironment()
	{		
		//try command line
		String command = "python --version";
		try {
			Process pr = Runtime.getRuntime().exec(command);

			/// get output to end process
			InputStreamReader isr = new InputStreamReader(pr.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			while ((br.readLine()) != null);
			
			if (pr.exitValue() == 0)
				return true;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
