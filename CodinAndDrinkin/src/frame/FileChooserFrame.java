package frame;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileChooserFrame extends JFrame {

	private static final long serialVersionUID = -5138263457848194239L;
	JFileChooser fc = new JFileChooser(".");
	
	FileChooserFrame() {
		setDefaultCloseOperation(1);
		this.setTitle("Choose a bot");
		
		fc.changeToParentDirectory();
		this.add(fc);
	}
	
	public void setSavedPath(File set) {
		fc = new JFileChooser(set);
	}
	
}