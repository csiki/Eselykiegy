package main;

import library.Game;
import frame.MainFrame;

/**
 * Launch the application.
 */

public class Main {
	public static void main(String[] args) {
		
		/// initialise Game
		Game game = new Game();
		
		/// launch the main window
		MainFrame window = new MainFrame(game);
		
		/// sets user interface for game
		game.setUI(window);
		
		/// run window
		try {
			Thread th = new Thread(window);
			th.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
