package library;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Generator implements GeneratorInterface {
	
	private int taskID;
	private int taskPriorTaskID;
	private String taskDescription;
	private List<String> taskInputs;
	private String taskValidOutput;
	private float taskMistakeAlcVol;
	private float taskSolvedAlcVol;
	private int taskAttemptsAllowed;
	private long taskTimeAllowed;
	
	@Override
	public void init() {
		this.taskID = 0;
		this.taskPriorTaskID = 0;
		this.taskDescription = "";
		this.taskInputs = new ArrayList<String>();
		this.taskValidOutput = "";
		this.taskMistakeAlcVol = 0.0F;
		this.taskSolvedAlcVol = 0.0F;
		this.taskAttemptsAllowed = 0;
		this.taskTimeAllowed = 0;
	}
	
	@Override
	public void clear() {
		init();
	}
	
	@Override
	public void serialize(String path) {
		if (path == "")
			path = "tasks/";
		
		Task task = new Task(
				this.taskID,
				this.taskPriorTaskID,
				this.taskDescription,
				this.taskInputs,
				this.taskValidOutput,
				this.taskMistakeAlcVol,
				this.taskSolvedAlcVol,
				this.taskAttemptsAllowed,
				this.taskTimeAllowed);
		
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		
		try {
			fos = new FileOutputStream(path + Integer.toString(this.taskID) + "." + "task");
			out = new ObjectOutputStream(fos);
			out.writeObject(task);
			out.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Map<String,String> status() {
		Map<String,String> vars = new HashMap<String,String>();
		String inputs = "";
		for (String i : this.taskInputs)
			inputs += i + ";";
		
		vars.put("id", Integer.toString(this.taskID));
		vars.put("priorTaskID", Integer.toString(this.taskPriorTaskID));
		vars.put("description", this.taskDescription);
		vars.put("inputs", inputs);
		vars.put("validOutput", this.taskValidOutput);
		vars.put("mistakeAlcVol", Float.toString(this.taskMistakeAlcVol));
		vars.put("solvedAlcVol", Float.toString(this.taskSolvedAlcVol));
		vars.put("attemptsAllowed", Integer.toString(this.taskAttemptsAllowed));
		vars.put("timeAllowed", Long.toString(this.taskTimeAllowed));
		
		return vars;
	}
	
	/*
	 * Save methods
	 */
	
	@Override
	public void saveID(int id) {
		this.taskID = id;
	}

	@Override
	public void savePriorTaskID(int priorID) {
		this.taskPriorTaskID = priorID;
	}

	@Override
	public void saveDescription(String description) {
		this.taskDescription = description;
	}

	@Override
	public void addInput(String input) {
		this.taskInputs.add(input);
	}

	@Override
	public void saveValidOutput(String output) {
		this.taskValidOutput = output;
	}

	@Override
	public void saveMistakeAlcVol(float alcVol) {
		this.taskMistakeAlcVol = alcVol;
	}

	@Override
	public void saveSolvedAlcVol(float alcVol) {
		this.taskSolvedAlcVol = alcVol;
	}

	@Override
	public void saveAttemptsAllowed(int attempts) {
		this.taskAttemptsAllowed = attempts;
	}

	@Override
	public void saveTimeAllowed(long time) {
		this.taskTimeAllowed = time;
	}
}
