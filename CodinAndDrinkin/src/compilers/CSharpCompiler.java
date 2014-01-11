package compilers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;

import library.Compiler;
import library.ProcessChecker;

public class CSharpCompiler extends Compiler
{
	private String cscPath = null;

	public CSharpCompiler(String name)
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
		// / make temp code file
		File codeFile = null;
		try
		{
			codeFile = File.createTempFile("code", ".cpp", new File("code"));
		}
		catch (IOException e)
		{
			return null;
		}

		// / fill temp code file
		FileWriter fw;
		BufferedWriter bw;
		try
		{
			fw = new FileWriter(codeFile);
			bw = new BufferedWriter(fw);
			bw.write(code);
			bw.close();
		}
		catch (IOException e1)
		{
			return null;
		}

		// / compile temp code file
		String command = "";
		if (cscPath != null)
		{
			command = cscPath + " /out:" + codeFile.getName() + ".exe " + "\"" +codeFile.getPath() + "\"";
		}
		else 
			command = "csc /out:" + codeFile.getName() + ".exe " + "\"" +codeFile.getPath() + "\"";
		
		try
		{
			Process pr = Runtime.getRuntime().exec(command);

			// / wait for execution
			Thread th = new Thread(new ProcessChecker(pr));
			th.start();
			int period = 0;
			int maxWaitTime = 60; // in tenth of a second
			while (period < maxWaitTime)
			{
				if (!th.isAlive())
					break;
				try
				{
					Thread.sleep(100);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				++period;
			}

			if (th.isAlive())
			{ // if still alive, the process still runs (for too long), so
				// destroy process (so the thread ends too) and return with
				// error
				pr.destroy();
				return null;
			}

			if (pr.exitValue() != 0)// = failure
				return null;

		}
		catch (IOException e)
		{
			return null;
		}
		File f = new File(codeFile.getName() + ".exe");
 	    f.renameTo(new File("runnable" + File.separatorChar + codeFile.getName() + ".exe"));
 	    
		return new File("runnable" + File.separatorChar + codeFile.getName() + ".exe");
	}

	@Override
	public String run(File compiledFile, List<String> inputs)
	{
		/// create command
		String command = compiledFile.getPath();
		
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
		String command = "csc -help";
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
		
		//try finding in C:\Windows\Microsoft.NET\
		String windowsPath = System.getenv().get("SystemRoot");

		if (checkFolderForCSC(windowsPath + "\\\\Microsoft.NET\\\\Framework64"))
			return true;
		if (checkFolderForCSC(windowsPath + "\\\\Microsoft.NET\\\\Framework"))
			return true;
		
		return false;
	}

	private boolean checkFolderForCSC(String path)
	{
		ArrayList<File> possibleDirs = new ArrayList<File>();
		File file = new File(path);
		if (file.exists())
		{
			for (String dirName : file.list())
			{
				File dir = new File(path + "\\\\" + dirName);
				File csc = new File(dir.getPath() + "\\\\csc.exe");
				if (dir.isDirectory() && csc.exists()
						&& dirName.matches("v(\\d\\.?)+")) // like v4.0
				{
					possibleDirs.add(dir);
				}
			}
			// sort to get the latest version
			Collections.sort(possibleDirs, new Comparator<File>() {
				public int compare(File f1, File f2)
				{
					String[] v1 = f1.getName().split("\\.");
					String[] v2 = f2.getName().split("\\.");

					for (int i = 0; i < v1.length && i < v2.length; i++)
					{
						int j = v1[i].compareTo(v2[i]);
						if (j != 0)
							return -j;
					}
					return 0;
				}
			});
			if (possibleDirs.size() > 0)
			{
				cscPath = possibleDirs.get(0).getAbsolutePath() + "\\csc.exe";
				return true;
			}
		}
		return false;
	}
}
