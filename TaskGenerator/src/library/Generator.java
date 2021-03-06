package library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Task generator for temporary storing a task, managing its values, serializing and saving.
 * @author csiki
 *
 */
public class Generator implements GeneratorInterface {
	
	private int taskID;
	private int taskPriorTaskID;
	private String taskTitle;
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
		this.taskTitle = "";
		this.taskDescription = "";
		this.taskInputs = new ArrayList<String>();
		this.taskValidOutput = "";
		this.taskMistakeAlcVol = 0.4F;
		this.taskSolvedAlcVol = 0.8F;
		this.taskAttemptsAllowed = 10;
		this.taskTimeAllowed = 300000;
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
				this.taskTitle,
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
			inputs += i + ",";
		if (inputs.length() > 0)
			inputs = inputs.substring(0, inputs.length()-1); // cut last comma
		
		vars.put("id", Integer.toString(this.taskID));
		vars.put("priorTaskID", Integer.toString(this.taskPriorTaskID));
		vars.put("description", this.taskDescription);
		vars.put("title", this.taskTitle);
		vars.put("inputs[]", "{ " + inputs + " }");
		vars.put("validOutput", this.taskValidOutput);
		vars.put("mistakeAlcVol(cl)", Float.toString(this.taskMistakeAlcVol));
		vars.put("solvedAlcVol(cl)", Float.toString(this.taskSolvedAlcVol));
		vars.put("attemptsAllowed", Integer.toString(this.taskAttemptsAllowed));
		vars.put("timeAllowed(msec)", Long.toString(this.taskTimeAllowed));
		
		return vars;
	}
	

	@Override
	public void setID(int id) {
		this.taskID = id;
	}

	@Override
	public void setPriorTaskID(int priorID) {
		this.taskPriorTaskID = priorID;
	}

	@Override
	public void setDescription(String description) {
		description = description.replaceAll("\\\\n", "\n");
		description = description.replaceAll("\\\\t", "\t");
		this.taskDescription = description;
	}

	@Override
	public void addInput(String input) {
		this.taskInputs.add(input);
	}

	@Override
	public void setValidOutput(String output) {
		output = output.replaceAll("\\\\n", "\n");
		output = output.replaceAll("\\\\t", "\t");
		this.taskValidOutput = output;
	}

	@Override
	public void setMistakeAlcVol(float alcVol) {
		this.taskMistakeAlcVol = alcVol;
	}

	@Override
	public void setSolvedAlcVol(float alcVol) {
		this.taskSolvedAlcVol = alcVol;
	}

	@Override
	public void setAttemptsAllowed(int attempts) {
		this.taskAttemptsAllowed = attempts;
	}

	@Override
	public void setTimeAllowed(long time) {
		this.taskTimeAllowed = time; // to ms
	}

	@Override
	public void setTitle(String title) {
		this.taskTitle = title;
	}

	@Override
	public String load(String filePath) {
		
		File file = new File(filePath);
		
		if (!file.exists() || !file.canRead())
			return "File not found!";
		
		/// substring the extension
		String extension = "";
		int i = file.getPath().lastIndexOf('.');
		if (i > 0)
		    extension = file.getPath().substring(i+1);
		if (!extension.equals("task"))
			return null;
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Task task = null;
		try {
			task = (Task) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			ois.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/// set task vars
		this.taskAttemptsAllowed = task.attemptsAllowed;
		this.taskDescription = task.description;
		this.taskID = task.id;
		this.taskInputs = task.inputs;
		this.taskMistakeAlcVol = task.mistakeAlcVol;
		this.taskPriorTaskID = task.priorTaskID;
		this.taskSolvedAlcVol = task.solvedAlcVol;
		this.taskTimeAllowed = task.timeAllowed;
		this.taskTitle = task.title;
		this.taskValidOutput = task.validOutput;
		
		return "Task loaded.";
	}

	@Override
	public void clearID() {
		this.taskID = 0;
	}

	@Override
	public void clearAttemptsAllowed() {
		this.taskAttemptsAllowed = 0;
	}

	@Override
	public void clearMistakeAlcVol() {
		this.taskMistakeAlcVol = 0.0F;
		
	}

	@Override
	public void clearDescription() {
		this.taskDescription = "";
		
	}

	@Override
	public void clearTimeAllowed() {
		this.taskTimeAllowed = 0;
		
	}

	@Override
	public void clearSolvedAlcVol() {
		this.taskSolvedAlcVol = 0.0F;
	}

	@Override
	public void clearValidOutput() {
		this.taskValidOutput = "";
	}

	@Override
	public void clearPriorTaskID() {
		this.taskPriorTaskID = 0;
	}

	@Override
	public void clearTitle() {
		this.taskTitle = "";
	}

	@Override
	public void clearInputs() {
		this.taskInputs.clear();
	}
}
