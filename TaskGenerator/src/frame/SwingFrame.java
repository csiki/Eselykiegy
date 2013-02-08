package frame;

import javax.swing.JFrame;
import library.GeneratorInterface;

public class SwingFrame implements Runnable {

	private JFrame frame;
	private GeneratorInterface generator;

	/**
	 * Create the application.
	 */
	public SwingFrame(GeneratorInterface generator) {
		this.generator = generator;
	}

	@Override
	public void run() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
