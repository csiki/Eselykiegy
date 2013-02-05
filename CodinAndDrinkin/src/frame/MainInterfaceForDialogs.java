package frame;

import java.io.File;

/**
 * MainFrame realizes, dialogs uses.
 * @author csiki
 *
 */
public interface MainInterfaceForDialogs {

	/**
	 * NewGameDialog uses, when the OK button pressed.
	 */
	public void newGameDialogReady();
	
	/**
	 * Save a reference of NewGameDialog, to ask for it's attributes.
	 * @param ngd
	 */
	public void setNewGameDialog(NewGameDialog ngd);
	
	/**
	 * Save a reference of AddBevDialog, to ask for it's attributes.
	 * @param abgd
	 */
	public void setAddBevDialog(AddBevDialog abd);
	
	/**
	 * AddBevDialog uses, when the OK button pressed.
	 */
	public void addBevDialogReady();
	
	/**
	 * Called after FileChooserFrame returns with a valid File.
	 * @param taskFile
	 */
	public void loadTask(File taskFile);
}
