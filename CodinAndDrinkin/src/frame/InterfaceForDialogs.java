package frame;

public interface InterfaceForDialogs {

	/**
	 * NewGameDialog uses, when the OK button pressed.
	 */
	public void newGameDialogReady();
	
	/**
	 * Save a reference of NewGameDialog, to ask for it's attributes.
	 * @param ngd
	 */
	public void setNewGameDialog(NewGameDialog ngd);
}
