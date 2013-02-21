package frame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import library.GeneratorInterface;

/**
 * Console interface for generating tasks.
 * @author csiki
 *
 */
public class ConsoleFrame {
	
	GeneratorInterface generator;
	
	public ConsoleFrame(GeneratorInterface generator) {
		this.generator = generator;
		this.generator.init();
	}
	
	/**
	 * Starts reading lines from console.
	 */
	public void start() {
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String line;
		String msg;
		
		System.out.println("Console interface for creating tasks for Codin&Drinkin - csiki@coderteam\n");
		System.out.println("Type \"help\" and press return if you got any question..");
		while (true) {
			try {
				line = br.readLine();
				msg = this.interpreter(line);
				System.out.println(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Interpret the read line.
	 * @param line
	 * @return message
	 */
	private String interpreter(String line) {
		String commandName = line;
		String arg = "";
		
		int spacePos = line.indexOf(" ");
		if (spacePos > 0) {
			commandName = line.substring(0, spacePos);
			arg = line.substring(spacePos + 1, line.length());
		}
		
		if (commandName.equals("exit")) {
			this.exit();
			return "Quiting..";
		}
		if (commandName.equals("set")) {
			if (this.set(arg))
				return "Set.";
			else
				return "Failure during set!";
		}
		if (commandName.equals("add")) {
			if (this.add(arg))
				return "Added.";
			else
				return "Failure during add!";
		}
		if (commandName.equals("clear")) {
			return this.clear(arg);
		}
		if (commandName.equals("save")) {
			this.save(arg);
			return "Task serialized and saved.";
		}
		if (commandName.equals("status")) {
			return this.status();
		}
		if (commandName.equals("help")) {
			return this.help();
		}
		if (commandName.equals("load")) {
			return this.load(arg);
		}
		
		return "No command like \"" + commandName + "\" found.";
	}
	
	/**
	 * Terminates the JVM
	 */
	private void exit() {
		System.exit(0);
	}
	
	/**
	 * Saves a task variable to generator.
	 * @param arg
	 * @return error
	 */
	private boolean set(String arg) {
		String id;
		String value;
		int spacePos = arg.indexOf(" ");
		if (spacePos > 0) {
			id = (arg.substring(0, spacePos)).toLowerCase(); // +lowercase
			value = arg.substring(spacePos + 1, arg.length());
		} else
			return false;
		
		/// here add further task variables (lowercase)
		if (id.equals("id"))
			this.generator.setID(Integer.parseInt(value));
		else if (id.equals("attemptsallowed"))
			this.generator.setAttemptsAllowed(Integer.parseInt(value));
		else if (id.equals("mistakealcvol"))
			this.generator.setMistakeAlcVol(Float.parseFloat(value));
		else if (id.equals("description"))
			this.generator.setDescription(value);
		else if (id.equals("timeallowed"))
			this.generator.setTimeAllowed(Long.parseLong(value));
		else if (id.equals("solvedalcvol"))
			this.generator.setSolvedAlcVol(Float.parseFloat(value));
		else if (id.equals("validoutput"))
			this.generator.setValidOutput(value);
		else if (id.equals("priortaskid"))
			this.generator.setPriorTaskID(Integer.parseInt(value));
		else if (id.equals("title"))
			this.generator.setTitle(value);
		else
			return false;
			
		return true;
	}
	
	/**
	 * Adds a task variable to generator (probably a new taskInput).
	 * @param arg
	 * @return error
	 */
	private boolean add(String arg) {
		String id;
		String value;
		int spacePos = arg.indexOf(" ");
		if (spacePos > 0) {
			id = (arg.substring(0, spacePos)).toLowerCase(); // lowercase
			value = arg.substring(spacePos + 1, arg.length());
		} else
			return false;
		
		/// here add further task lists (lowercase)
		if (id.equals("inputs"))
			this.generator.addInput(value);
		else
			return false;
		
		return true;
	}
	
	/**
	 * Clear task vars.
	 */
	private String clear(String arg) {
		
		arg = arg.toLowerCase();
		
		if (arg.equals("")) {
			this.generator.clear();
			return "All cleared.";
		}
		else if (arg.equals("id")) {
			this.generator.clearID();
			return "id cleared.";
		}
		else if (arg.equals("attemptsallowed")) {
			this.generator.clearAttemptsAllowed();
			return "attemptsAllowed cleared.";
		}
		else if (arg.equals("mistakealcvol")) {
			this.generator.clearMistakeAlcVol();
			return "mistakeAlcVol cleared.";
		}
		else if (arg.equals("description")) {
			this.generator.clearDescription();
			return "description cleared.";
		}
		else if (arg.equals("timeallowed")) {
			this.generator.clearTimeAllowed();
			return "timeAllowed cleared.";
		}
		else if (arg.equals("solvedalcvol")) {
			this.generator.clearSolvedAlcVol();
			return "solvedAlcVol cleared.";
		}
		else if (arg.equals("validoutput")) {
			this.generator.clearValidOutput();
			return "validOutput cleared.";
		}
		else if (arg.equals("priortaskid")) {
			this.generator.clearPriorTaskID();
			return "priorTaskID cleared.";
		}
		else if (arg.equals("title")) {
			this.generator.clearTitle();
			return "title cleared.";
		}
		else if (arg.equals("inputs")) {
			this.generator.clearInputs();
			return "inputs cleared.";
		}
		
		return "Clear failed: unknown identifier!";
	}
	
	/**
	 * Serialize and save Task object.
	 * @param arg
	 */
	private void save(String arg) {
		this.generator.serialize(arg);
	}
	
	/**
	 * Loads an existing .task file.
	 * @param arg
	 * @return
	 */
	private String load(String arg) {
		return this.generator.load(arg);
	}
	
	/**
	 * Prints the task variables and its values to console.
	 * @return
	 */
	private String status() {
		String status = "\nStatus:\n=====================================================================\n";
		status += "Task variable name\t\tValue\n---------------------------------------------------------------------\n";
		
		Map<String,String> vars = this.generator.status();
		String value;
		for (String key : vars.keySet()) {
			value = vars.get(key);
			value = value.replaceAll("\n", "\n\t\t\t\t");
			
			if (key.length() < 8)
				status += key + "\t\t\t\t" + value + "\n";
			else if (key.length() < 16)
				status += key + "\t\t\t" + value + "\n";
			else
				status += key + "\t\t" + value + "\n";
		}
		status += "---------------------------------------------------------------------\n";
		
		return status;
	}
	
	/**
	 * Prints command descriptions to console.
	 * @return
	 */
	private String help() {
		String help = "";
		
		/// header
		help += "\nHelp: (args in use may be divided by a space in order)\n=====================================================================\n";
		help += "Command name\tArg(s)\t\t\tDescription\n---------------------------------------------------------------------\n";
		
		/// commands
		help += "set\t\tidentifier, value\tSets a task variable,\n\t\t\t\t\tidentified by identifier.\n\t\t\t\t\tUse \"\\n\" for newline.\n";
		help += "add\t\tidentifier, item\tAdds item to the identified\n\t\t\t\t\tlist (e.g. inputs).\n";
		help += "clear\t\t[identifier]\t\tSets value of a task variable\n\t\t\t\t\tto default. Empty all task\n\t\t\t\t\tvariables if no identifier\n\t\t\t\t\tspecified.\n";
		help += "status\t\t-\t\t\tLists task variables and\n\t\t\t\t\tits values.\n";
		help += "save\t\tpath(def: \"tasks/\")\tSerialize and save task\n\t\t\t\t\tto the given path.\n\t\t\t\t\tPath may end\n\t\t\t\t\twith a slash (\"/\").\n";
		help += "load\t\tpath\t\t\tLoads the values of an\n\t\t\t\t\texisting .task file.\n";
		help += "help\t\t-\t\t\tBrings up this table.\n";
		help += "exit\t\t-\t\t\tExits.\n";
		
		help += "---------------------------------------------------------------------\n";
		
		return help;
	}
}
