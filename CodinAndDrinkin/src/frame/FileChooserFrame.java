package frame;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * FileChooser window for opening a .task file.
 * @author csiki
 *
 */
public class FileChooserFrame extends JFrame {

	private static final long serialVersionUID = -5138263457848194239L;
	JFileChooser fc = new JFileChooser(".");
	
	FileChooserFrame() {
		setDefaultCloseOperation(1);
		this.setTitle("Choose a bot");
		
		fc.changeToParentDirectory();
		this.add(fc);
	}
	
	/**
	 * If opening file succeeds, the base path is set to the folder the opened file is in.
	 * @param set
	 */
	public void setSavedPath(File set) {
		fc = new JFileChooser(set);
	}
	
}