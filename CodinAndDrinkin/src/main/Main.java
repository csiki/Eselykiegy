package main;

import frame.MainFrame;

/**
 * Launch the application.
 */

public class Main {
	public static void main(String[] args) {
		
		// launch the main window
		try {
			MainFrame window = new MainFrame();
			Thread th = new Thread(window);
			th.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
