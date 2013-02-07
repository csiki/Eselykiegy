package main;

import library.Generator;
import frame.ConsoleFrame;

public class Main {
	public static void main(String[] args) {
		/// initialise generator
		Generator generator = new Generator();
		
		/// initialise user interface
		ConsoleFrame cf = new ConsoleFrame(generator);
		cf.start();
		
		/*SwingFrame window = new SwingFrame(generator);
		Thread th = new Thread(window);
		th.start();*/ // GUI finish later
	}
}
